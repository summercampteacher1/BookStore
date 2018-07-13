package com.bookstore.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.BookService;

public class DeleteBooksAction extends ControllerAction {

	@Override
	public void action(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		
		BookService.deleteBook(Integer.parseInt(id));
		request.getRequestDispatcher("/controller?action=viewAllBooks").forward(request, response);

	}

	public String getActionName() {
		return "deleteBooks";
	}

}
