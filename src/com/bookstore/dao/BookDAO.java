package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.Book;

/**
 * Very basic implementation of a DAO that creates a single connection and uses that to retrieve data from the DB
 * Created purely for illustration. As such it is not using connection pooling and is not thread safe.
 * @author workpc
 *
 */

public class BookDAO {
	
	//private static final String MYSQL_DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookstore?useSSL=false";
	private static final String JDBC_USERID = "bookstore_app";
	private static final String JDBC_PASSWORD = "password";
	private static final BookDAO singleton = new BookDAO();
	private boolean initialized = false;
	private static final String ADD_BOOK_SQL = "insert into Book(Title,Author) values(?,?)"; 
	private static final String RETRIEVE_ALL_BOOKS_SQL = "select * from Book"; 
	private static final String RETRIEVE_BOOK_BY_ID_SQL = "select * from Book where id = ?"; 
	private static final String UPDATE_BOOKS_SQL = "update Book set TITLE= ?, author = ? where id = ?"; 
	private static final String DELETE_BOOK_SQL = "delete from Book where id = ?"; 
	private Connection connection = null;
	
	public static BookDAO getInstance() {
		return singleton;
	}
	
	private BookDAO() {
		
			initialize();
		
	}
	
	private void initialize() {
		try {
			//Class.forName(MYSQL_DB_DRIVER).newInstance();
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
	
	public boolean addBook(Book book) {
		boolean added = false;
		
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK_SQL);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			int rowsAdded = preparedStatement.executeUpdate();
			if (rowsAdded > 0) {
				added = true;
			}
		} catch (Exception ex) {
			System.out.println("Failed in method addBook(Book):" + ex.getMessage());
		}
		
		return added;
	}
	
	
	public List<Book> retrieveAllBooks() {
		 
		List<Book> books = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_ALL_BOOKS_SQL);
			ResultSet allBooksRS = preparedStatement.executeQuery();
			while (allBooksRS.next()) {
				int id = allBooksRS.getInt("ID");
				String title = allBooksRS.getString("Title");
				String author = allBooksRS.getString("Author");
				Book book = new Book(id,title,author);
				books.add(book);
			}
		} catch (Exception ex) {
			System.out.println("Failed in method retrieveAllBooks():" + ex.getMessage());
		}
		
		return books;
	}
	
	public boolean updateBook(Book book) {
		boolean updated = false;

		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_SQL);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setInt(3,book.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				updated = true;
			}
		} catch (Exception ex) {
			System.out.println("Failed in method updateBook(Book):" + ex.getMessage());
		}

		return updated;
	}
	
	public boolean deleteBook(int bookId) {
		boolean deleted = false;
		
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_SQL);
			preparedStatement.setInt(1,bookId);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
			}
		} catch (Exception ex) {
			System.out.println("Failed in method deleteBook(bookId):" + ex.getMessage());
		}
		
		return deleted;
		
	}	
	
	public static void main (String args[]) {
		BookDAO bookDAO = BookDAO.getInstance();
		
		System.out.println("Adding books");
		int numOfBooks = 10;
		for (int i = 0;i<numOfBooks;i++) {
			Book newBook = new Book("Book Title " + i,"Author " + i);
			bookDAO.addBook(newBook);
		}
		System.out.println("Added " + numOfBooks + " books");
		
		System.out.println("Retrieving all books in DB after adding them");
		List<Book> allBooksInDB = bookDAO.retrieveAllBooks();
		System.out.println("Retrieved " + allBooksInDB.size() + " books");
		for (Book book:allBooksInDB) {
			System.out.println(book);
		}
		
		/*
		System.out.println("Updating all books");
		System.out.println("Updating " + allBooksInDB.size() + " books");
		for (Book book:allBooksInDB) {
			book.setTitle("Updated Title:" + book.getTitle());
			bookDAO.updateBook(book);
		}

		System.out.println("Retrieving all books in DB after updating them");
		allBooksInDB = bookDAO.retrieveAllBooks();
		System.out.println("Retrieved " + allBooksInDB.size() + " books");
		for (Book book:allBooksInDB) {
			System.out.println(book);
		}

		System.out.println("Deleting all books in DB");
		for (Book book:allBooksInDB) {
			bookDAO.deleteBook(book);
		}

		System.out.println("Checking if any books are left in DB after deleting them all");
		allBooksInDB = bookDAO.retrieveAllBooks();
		System.out.println("Retrieved " + allBooksInDB.size() + " books");
		for (Book book:allBooksInDB) {
			System.out.println(book);
		}
		*/
	}

}
