package com.bookstore.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.model.Book;
import com.bookstore.services.BookService;

public class UpdateBooksAction extends ControllerAction {

	@Override
	public void action(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		Book book = new Book(id, title, author);
		BookService.updateBook(book);
		request.getRequestDispatcher("/controller?action=viewAllBooks").forward(request, response);
		
	}

	public String getActionName() {
		return "updateBooks";
	}

}
