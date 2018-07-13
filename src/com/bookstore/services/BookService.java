package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;

public class BookService {
	
	public static List<Book> retrieveAllBooks() {
		List<Book> books = BookDAO.getInstance().retrieveAllBooks();
		return books;
	}
	
	public static boolean addBook(Book book) {
		return BookDAO.getInstance().addBook(book);
		
	}

	public static boolean updateBook(Book book) {
		return BookDAO.getInstance().updateBook(book);
	}
	
	public static boolean deleteBook(int id) {
		return BookDAO.getInstance().deleteBook(id);
	}

}
