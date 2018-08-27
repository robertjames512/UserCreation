package edu.touro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.touro.bean.TouroUser;


public class UserDAOImpl implements UserDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUserUDCID(TouroUser user) {
	
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		
		
	}

	@Override
	public TouroUser getUser(String udcid) {
		Session session = this.sessionFactory.getCurrentSession();
		TouroUser user = (TouroUser) session.load(TouroUser.class, new String(udcid));
		logger.info("User retrieved successfully " + user);
		return user;
	}
	
	public void getUserInfoFromSpriden(HashMap<String, TouroUser> userMap, Connection conn) throws SQLException {
		PreparedStatement sql = null;
		TouroUser userBean = new TouroUser();
		logger.info("About to retrieve user information from the DB");
		for(String key : userMap.keySet()) {
			System.out.println("Key value is: " + key);
			userBean = userMap.get(key);
			sql = conn.prepareStatement("Select * from spriden where SPRIDEN_ID = ?");
			sql.setString(1, key);
			ResultSet rs = sql.executeQuery();
			userBean.setFirst_name(rs.getString("SPRIDEN_FIRST_NAME"));
			userBean.setLast_name(rs.getString("SPRIDEN_LAST_NAME"));
			userBean.setPidm(rs.getString("SPRIDEN_PIDM"));
			userBean.setTnumber(rs.getString("SPRIDEN_ID"));
		}	
		
	}

}
