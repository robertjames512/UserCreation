package edu.touro.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spriden")
public class TouroUser {
	
	@Id
	@Column(name="spriden_pidm")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	protected String tNumber = "";
	protected String udcID = "";
	protected String first_name = "";
	protected String last_name = "";
	protected String dob = "";
	protected String ssn = "";
	protected String pidm = "";
	protected String username = "";
	protected String emailAddress = "";
	protected boolean isInMSSQLDB = false;
	protected boolean isInOracleDB = false;
	protected boolean touchSuccessful = false;
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getTnumber() {
		return tNumber;
	}
	public void setTnumber(String tNumber) {
		this.tNumber = tNumber;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getUdcID() {
		return udcID;
	}
	public void setUdcID(String udcID) {
		this.udcID = udcID;
	}
	public String getPidm() {
		return pidm;
	}
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isInMSSQLDB() {
		return isInMSSQLDB;
	}
	public void setInMSSQLDB(boolean isInMSSQLDB) {
		this.isInMSSQLDB = isInMSSQLDB;
	}
	public boolean isInOracleDB() {
		return isInOracleDB;
	}
	public void setInOracleDB(boolean isInOracleDB) {
		this.isInOracleDB = isInOracleDB;
	}
	public boolean isTouchSuccessful() {
		return touchSuccessful;
	}
	public void setTouchSuccessful(boolean touchSuccessful) {
		this.touchSuccessful = touchSuccessful;
	}

}
