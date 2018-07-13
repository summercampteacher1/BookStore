package com.bookstore.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.model.Book;
import com.bookstore.services.BookService;

public class AddBookAction extends ControllerAction {

	@Override
	public void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		Book book = new Book(title, author);
		BookService.addBook(book);
		request.getRequestDispatcher("/controller?action=viewAllBooks").forward(request, response);

	}

	@Override
	public String getActionName() {
		// TODO Auto-generated method stub
		return "addABook";
	}

}
