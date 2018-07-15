package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.Book;
import com.bookstore.model.User;

/**
 * Very basic implementation of a DAO that creates a single connection and uses that to retrieve data from the DB
 * Created purely for illustration. As such it is not using connection pooling and is not thread safe.
 * @author workpc
 *
 */

public class LoginDAO {
	
	private static final String MYSQL_DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://db:3306/bookstore";
	private static final String JDBC_USERID = "bookstore_app";
	private static final String JDBC_PASSWORD = "password";
	private static final LoginDAO singleton = new LoginDAO();
	private boolean initialized = false;
	private static final String RETRIEVE_USER_SQL = "select * from User where userid = ?"; 
	private Connection connection = null;
	
	public static LoginDAO getInstance() {
		return singleton;
	}
	
	private LoginDAO() {
		
			initialize();
		
	}
	
	private void initialize() {
		try {
			Class.forName(MYSQL_DB_DRIVER).newInstance();
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USERID, JDBC_PASSWORD);
			initialized = true;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		
	}
	
	private Connection getConnection() {
		if (!initialized) {
			initialize();
		} else {
			try {
				if (connection == null || connection.isClosed()) {
					initialized = false;
					initialize();
				}
			} catch (Exception ex) {
				initialized = false;
				System.out.println("Failed in method getConnection():" + ex.getMessage());
			}
		}
		return connection;
	}
	
	
	public User retrieveUser(String userId) {
		 
		User user = null;
		
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_USER_SQL);
			preparedStatement.setString(1, userId);
			ResultSet allBooksRS = preparedStatement.executeQuery();
			while (allBooksRS.next()) {
				String userid = allBooksRS.getString("UserId");
				String password = allBooksRS.getString("Password");
				String firstName = allBooksRS.getString("FirstName");
				String lastName = allBooksRS.getString("LastName");
				int age = allBooksRS.getInt("Age");
				user = new User(userId,password,firstName,lastName,age);
			}
		} catch (Exception ex) {
			System.out.println("Failed in method retrieveAllBooks():" + ex.getMessage());
		}
		
		return user;
	}
	

	
	public static void main (String args[]) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		
		System.out.println("Retrieving user from DB");
		User user = loginDAO.retrieveUser("u");
		System.out.println("Retrieved user :" + user);

	}

}
