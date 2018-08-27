package edu.touro.constants;

public class UserCreationConstants {
	
	/* Database Queries */
	public static final String ORACLE_DB_QUERY = "select a.GOBUMAP_UDC_ID,c.GOBTPAC_EXTERNAL_USER,b.SPRIDEN_ID,b.SPRIDEN_PIDM,b.SPRIDEN_FIRST_NAME,b.SPRIDEN_LAST_NAME "
			+ "from gobumap a, spriden b, gobtpac c where b.SPRIDEN_PIDM = a.GOBUMAP_PIDM "
			+ "AND b.SPRIDEN_PIDM = c.GOBTPAC_PIDM AND c.GOBTPAC_PIDM = a.GOBUMAP_PIDM "
			+ "AND b.SPRIDEN_ID = ?";
	public static final String MSSQL_DB_QUERY = "select SPRIDEN_ID, TOURO_EMAIL, DOB, SSN from [banner_staging].[dbo].EMPLOYEE where  SPRIDEN_ID = ?";
	public static final String ORACLE_TOUCH = "update spbpers set SPBPERS_ACTIVITY_DATE = sysdate, SPBPERS_USER_ID = 'IDM' where spbpers_pidm = f_get_pidm(?)";
	public static final String ORACLE_ADD_UDCID = "select f_add_udc(?) from dual";
	
	/* Database Attributes */
	public static final String SPRIDEN_PIDM = "SPRIDEN_PIDM";
	public static final String SPRIDEN_FIRST_NAME = "SPRIDEN_FIRST_NAME";
	public static final String SPRIDEN_LAST_NAME = "SPRIDEN_LAST_NAME";
	public static final String GOBUMAP_UDC_ID = "GOBUMAP_UDC_ID";
	public static final String GOBTPAC_EXTERNAL_USER = "GOBTPAC_EXTERNAL_USER";
	public static final String TOURO_EMAIL = "TOURO_EMAIL";
	public static final String DOB = "DOB";
	public static final String SSN = "SSN";
	
	/* Character Strings */
	public static final String NEWLINE_CHAR = "<br>";
	
	/* Email Constants */
	public static final String EMAIL_MESSAGE="In order to perform any testing within the Touro TEST environment you will need credentials.<br><br>" + 
			"Below you will find the instructions on how to claim your account in the TEST environment:<br><br>" + 
			"Go to: https://testfederationservices.touro.edu/cas/login <br><br>" + 
			"Within the icon menu at the bottom of the screen, click &#39;First Time User&#39; <br><br>" + 
			"Enter the following information:<br><br>";
	
	public static final String FIRST_NAME = "First Name: ";
	public static final String LAST_NAME = "Last Name: ";
	public static final String TOUROONE_ID = "TouroOne ID: ";
	public static final String MONTH_OF_BIRTH = "Month of Birth: ";
	public static final String DAY_OF_BIRTH = "Date of Birth: ";
	public static final String LAST_SSN = "Last 4 SSN: ";
	
	public static final String EMAIL_FOOTER = "(As a security precaution, DOB and SSN data was 'scrambled' in TEST. Do not be alarmed if this data does not match your actual DOB & SSN)<br><br>"
			+ "Please reach out to Robert James, robert.james4@touro.edu, if you experience any issues with claiming your account.<br><br>"
			+ "Kind Regards, <br><br>"
			+ "Touro IDM Team";
}
