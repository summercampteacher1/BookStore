package com.bookstore.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;
import com.bookstore.model.User;
import com.bookstore.services.BookService;
import com.bookstore.services.LoginService;

public class LoginAction extends ControllerAction {

	@Override
	public void action(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		User user = LoginService.retrieveUser(userId);
		System.out.println("User retrieved is "+user);
		
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("Creating session after succesful login by user:"+ user);
			user.setPassword(null);
			HttpSession session = request.getSession(true);
			session.setAttribute("user",user);
			request.getRequestDispatcher("/controller?action=viewAllBooks").forward(request, response);
		} else {
			response.getWriter().append("Failed to login with user:").append(userId).append(" Served at: ").append(request.getContextPath());
		}
		
		
	}

	public String getActionName() {
		return "login";
	}

}
