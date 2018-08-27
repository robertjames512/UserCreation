package edu.touro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.touro.main.EncryptDecrypt;

public abstract class ConnectToDB {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectToDB.class);
	static ResourceBundle prop = ResourceBundle.getBundle("userCreation");
	
	public Connection makeDBconnection(String ENCRYPTED_PASSPHRASE, String DB_DRIVER, String DB_CONNECTION, String DB_USER) {
		Connection dbConnection = null;
		dbConnection = getDBConnection(ENCRYPTED_PASSPHRASE, DB_DRIVER, DB_CONNECTION, DB_USER);
		return dbConnection;
	}
	
private static Connection getDBConnection(String ENCRYPTED_PASSPHRASE, String DB_DRIVER, String DB_CONNECTION, String DB_USER) {
		
		String initVector = prop.getString("INIT_VECTOR");
		String encryptKey = prop.getString("PASSPHRASE_KEY");
		EncryptDecrypt decryptPass = new EncryptDecrypt();
		String ORACLE_PASSWORD = "";
		
		ORACLE_PASSWORD = decryptPass.decrypt(encryptKey, initVector, ENCRYPTED_PASSPHRASE);

		Connection dbConnection = null;
		try {
			logger.info("Connecting to DB");
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Error 1 " + e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					ORACLE_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println("Error 2 " + e.getMessage());
		}
		return dbConnection;
	}

}
