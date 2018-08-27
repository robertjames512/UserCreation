package edu.touro.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.touro.bean.TouroUser;
import edu.touro.dao.ConnectToMSSQL;
import edu.touro.dao.ConnectToOracleDB;

public class DataCollection {

	private static final Logger logger = LoggerFactory.getLogger(DataCollection.class);

	ResourceBundle prop = ResourceBundle.getBundle("userCreation");

	ConnectToOracleDB getConnectionORACLE = new ConnectToOracleDB();
	ConnectToMSSQL getConnectionMSSQL = new ConnectToMSSQL();
	Connection dbConnectORACLE = getConnectionORACLE.makeDBconnection();
	Connection dbConnectMSSQL = getConnectionMSSQL.makeDBconnection();

	/*
	 * public static void main(String args[]) { ReadCSV getIDinfo = new ReadCSV();
	 * getIDinfo.collectID(); }
	 */

	public StringBuffer collectID(String filename, String filePath) {

		String line = "";
		String id = "";
		StringBuffer statusMessage = new StringBuffer();
		StringBuffer emailMessage = new StringBuffer();
		HashMap<String, TouroUser> userMap = new HashMap<>();
		ConnectToOracleDB getOracleDBinfo = new ConnectToOracleDB();
		ConnectToMSSQL getMssqlDBinfo = new ConnectToMSSQL();
		SendEmail sendMessage = new SendEmail();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath + filename))) {

			while ((line = br.readLine()) != null) {
				TouroUser userBean = new TouroUser();
				id = line;
				userBean.setTnumber(id);
				// Check if we've already processed this user
				if (userMap.containsKey(id)) {
					continue;
				}
				// Pass connecting to DB and collect user information from the Oracle DB and then the MSSQL DB
				getOracleDBinfo.getUserData(userBean, dbConnectORACLE, statusMessage);
				
				/* 1.) write UDCID to the view 
				 * 2.) Touch the Account, then check for info in MSSQL DB 
				 * */
				getOracleDBinfo.addUDCID(userBean, dbConnectORACLE, statusMessage);
				getOracleDBinfo.touchAccount(userBean, dbConnectORACLE, statusMessage);
				
				getMssqlDBinfo.getUserData(userBean, dbConnectMSSQL, statusMessage);
				
				/* Build out email message 
				if(userBean.isTouchSuccessful() && userBean.isInMSSQLDB()) {
				emailMessage = sendMessage.messageBuilder(userBean);
				sendMessage.send("TouroIDM@Touro.edu", "Claim Your TEST Account", emailMessage, userBean);
				}
				else {
					logger.info("User: " + userBean.getFirst_name() + " " + userBean.getLast_name() + " will not receive an email because touching the account was not successful");
				} */
				
				
				if(userBean.isInMSSQLDB() && userBean.isInOracleDB()) 
				{
					userMap.put(id, userBean);
				}
				else {
					logger.info(userBean.getTnumber() + " is not in one of the DB systems, not adding to MAP");
				}
			}

		} catch (IOException e) {
			logger.error("An error occurred while trying to get data for user: " + id + "Error that occurred: " + e);
		}

		return statusMessage;
	}

}
