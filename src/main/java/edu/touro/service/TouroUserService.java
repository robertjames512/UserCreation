package edu.touro.service;

import edu.touro.bean.TouroUser;

public interface TouroUserService {
	
	public void addUserUDCID(TouroUser user);
	public TouroUser getUser(String udcid);

}
