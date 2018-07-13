package com.bookstore.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;

public class ViewAllBooksAction extends ControllerAction {

	@Override
	public void action(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> books = BookDAO.getInstance().retrieveAllBooks();
		System.out.println("List of all books:"+ books);
		HttpSession session = request.getSession();
		session.setAttribute("books", books);
		request.getRequestDispatcher("/splash_page.jsp").forward(request, response);
		
	}

	public String getActionName() {
		return "viewAllBooks";
	}

}
