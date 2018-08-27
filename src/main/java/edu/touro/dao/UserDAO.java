package edu.touro.dao;

import edu.touro.bean.TouroUser;

public interface UserDAO {
	
	public void addUserUDCID(TouroUser user);
	public TouroUser getUser(String udcid);

}
