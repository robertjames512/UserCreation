package edu.touro.service;

import org.springframework.transaction.annotation.Transactional;

import edu.touro.bean.TouroUser;
import edu.touro.dao.UserDAO;

public class TouroUserServiceImpl implements TouroUserService {
	
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUserUDCID(TouroUser user) {
		
		this.userDAO.addUserUDCID(user);
				
	}

	@Override
	public TouroUser getUser(String udcid) {
		return this.userDAO.getUser(udcid);
	}

}
