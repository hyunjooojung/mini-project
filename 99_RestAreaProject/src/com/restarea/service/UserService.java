package com.restarea.service;

import static com.restarea.common.JDBCTemplate.openConnection;

import java.sql.Connection;

import com.restarea.dao.UserDao;
import com.restarea.model.vo.User;

public class UserService {

	private UserDao userDao = new UserDao();
	private Connection conn = null;

	public UserService() {
		conn = openConnection();
	}
	
	public int login(String userId,String userpw) {
		return userDao.login(conn, userId, userpw);
	}
	
	public User getInfo(String userId,String userpw) {
		return userDao.selectOne(conn, userId, userpw);
	}

}
