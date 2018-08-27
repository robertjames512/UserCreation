/*package edu.touro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.touro.bean.TouroUser;
import edu.touro.constants.UserCreationConstants;
import edu.touro.main.EncryptDecrypt;


public class ConnectToOracleDB extends ConnectToDB {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectToOracleDB.class);
	static ResourceBundle prop = ResourceBundle.getBundle("userCreation");

	private static final String DB_DRIVER = prop.getString("ORACLE_DB_DRIVER");
	private static final String DB_CONNECTION = prop.getString("ORACLE_DB_CONNECTION");
	private static final String DB_USER = prop.getString("ORACLE_USER");
	private static final String ENCRYPTED_PASSPHRASE = prop.getString("ORACLE_PASSWORD");
	
	
	public static void main(String[] argv) {
		try {
			selectRecordsFromDbUserTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection makeDBconnection() {
		Connection dbConnection = null;
		dbConnection = getDBConnection();
		return dbConnection;
	}
	
	private static void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * from spriden";
		String query = "select a.GOBUMAP_UDC_ID,c.GOBTPAC_EXTERNAL_USER,b.SPRIDEN_ID,b.SPRIDEN_FIRST_NAME,b.SPRIDEN_LAST_NAME "
				+ "from gobumap a, spriden b, gobtpac c where b.SPRIDEN_PIDM = a.GOBUMAP_PIDM "
				+ "AND b.SPRIDEN_PIDM = c.GOBTPAC_PIDM AND c.GOBTPAC_PIDM = a.GOBUMAP_PIDM "
				+ "AND b.SPRIDEN_ID = 'T0023456'";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			//System.out.println(selectTableSQL);
			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				String userid = rs.getString("SPRIDEN_FIRST_NAME");
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
	}
	
	
	private static Connection getDBConnection() {
		
		String initVector = prop.getString("INIT_VECTOR");
		String encryptKey = prop.getString("PASSPHRASE_KEY");
		EncryptDecrypt decryptPass = new EncryptDecrypt();
		String ORACLE_PASSWORD = "";
		
		ORACLE_PASSWORD = decryptPass.decrypt(encryptKey, initVector, ENCRYPTED_PASSPHRASE);

		Connection dbConnection = null;
		try {
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
	
	public void getUserData(TouroUser user, Connection conn, StringBuffer message) {
		ResultSet rs = null;
		String query = UserCreationConstants.ORACLE_DB_QUERY;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getTnumber());
			rs = statement.executeQuery();
			//Let the user know that the selected user does not return any info from Banner
			if(!rs.isBeforeFirst()) {
				logger.warn("User: " + user.getTnumber() + " does not exist within Banner, please have their identity copied over from PROD and try again");
				message.append("User, " + user.getTnumber() + ", does not exist within Banner. Please have their identity copied over from PROD and try again");
				message.append(UserCreationConstants.NEWLINE_CHAR);
			}
			else {
			while(rs.next()) {
				
				user.setPidm(rs.getString(UserCreationConstants.SPRIDEN_PIDM));
				user.setFirst_name(rs.getString(UserCreationConstants.SPRIDEN_FIRST_NAME));
				user.setLast_name(rs.getString(UserCreationConstants.SPRIDEN_LAST_NAME));
				user.setUdcID(rs.getString(UserCreationConstants.GOBUMAP_UDC_ID));
				user.setUsername(rs.getString(UserCreationConstants.GOBTPAC_EXTERNAL_USER));
				
				logger.info("Successfully retrieved info for " + user.getTnumber());
				
			}
			}
		} catch (SQLException e) {
			logger.error("Something went wrong retrieving user data from the DB: " + e);
		}
		
				
	}
	

}
*/