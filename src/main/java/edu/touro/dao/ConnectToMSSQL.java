package edu.touro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.touro.bean.TouroUser;
import edu.touro.constants.UserCreationConstants;
import edu.touro.main.EncryptDecrypt;

@SuppressWarnings("unused")
public class ConnectToMSSQL {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectToMSSQL.class);
	static ResourceBundle prop = ResourceBundle.getBundle("userCreation");

	private static final String DB_DRIVER = prop.getString("MSSQL_DB_DRIVER");
	private static final String DB_CONNECTION = prop.getString("MSSQL_DB_CONNECTION");
	private static final String DB_USER = prop.getString("MSSQL_USER");
	private static final String ENCRYPTED_PASSPHRASE = prop.getString("MSSQL_PASSWORD");
	
	/*public static void main(String args[]) {
		
		try {
			doQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void doQuery() throws SQLException {
		ResultSet rs = null;
		Statement statement = null;
		String query = "select * from [banner_staging].[dbo].EMPLOYEE where  SPRIDEN_ID = 'T00011023'";
		Connection dbConnection = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String userid = rs.getString("FIRST_NAME");
				String username = rs.getString("SPRIDEN_ID");

				System.out.println("userid : " + userid);
				System.out.println("username : " + username);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
	}*/
	
	public Connection makeDBconnection() {
		Connection dbConnection = null;
		dbConnection = getDBConnection();
		return dbConnection;
	}
	
	private static Connection getDBConnection() {
		
		String initVector = prop.getString("INIT_VECTOR");
		String encryptKey = prop.getString("PASSPHRASE_KEY");
		EncryptDecrypt decryptPass = new EncryptDecrypt();
		String MSSQL_PASSWORD = "";
		
		MSSQL_PASSWORD = decryptPass.decrypt(encryptKey, initVector, ENCRYPTED_PASSPHRASE);

		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error: " + e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					MSSQL_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println("MSSQL Connection error: " + e.getMessage());
		}
		return dbConnection;
	}
	
	public void getUserData(TouroUser user, Connection conn, StringBuffer message) {
		ResultSet rs = null;
		String query = UserCreationConstants.MSSQL_DB_QUERY;
		String email = "";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getTnumber());
			rs = statement.executeQuery();
			//Let the user know that the selected user does not return any info from Banner
			if(!rs.isBeforeFirst()) {
				logger.warn("User: " + user.getTnumber() + " does not exist within Banner_Staging, please have their identity touched and try again");
				message.append("User: " + user.getTnumber() + ", does not exist within Banner_Staging. Please have their identity touched and try again");
				message.append(UserCreationConstants.NEWLINE_CHAR);
			}
			else {
			while(rs.next()) {
				
				user.setInMSSQLDB(true);
				user.setDob(rs.getString(UserCreationConstants.DOB));
				user.setSsn(rs.getString(UserCreationConstants.SSN));
				email = rs.getString(UserCreationConstants.TOURO_EMAIL);
				if(email.length() < 2) {
					logger.info(user.getTnumber() + " Does not have an email address in the DB, will need to find out user's email address");
					message.append(user.getTnumber() + " Does not have an email address in the DB, will need to find out user's email address");
				}
				else {
					user.setEmailAddress(rs.getString(UserCreationConstants.TOURO_EMAIL));
				}
				logger.info("Successfully retrieved info from the MSSQL DB for " + user.getTnumber());
				
			}
			}
		} catch (SQLException e) {
			logger.error("Something went wrong retrieving user data from the MSSQL DB: " + e);
		}
		
				
	}

}
