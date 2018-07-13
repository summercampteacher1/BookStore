package com.bookstore.services;

import com.bookstore.dao.LoginDAO;
import com.bookstore.model.User;

public class LoginService {
	
	public static User retrieveUser(String userId) {
		User user = LoginDAO.getInstance().retrieveUser(userId);
		return user;
	}
	
}
