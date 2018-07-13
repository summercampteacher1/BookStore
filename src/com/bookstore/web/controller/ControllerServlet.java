package com.bookstore.web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.web.action.AddBookAction;
import com.bookstore.web.action.ControllerAction;
import com.bookstore.web.action.DeleteBooksAction;
import com.bookstore.web.action.LoginAction;
import com.bookstore.web.action.UpdateBooksAction;
import com.bookstore.web.action.ViewAllBooksAction;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	
	private static boolean logParameters = true;
	
	private Map<String,ControllerAction> MAP_ACTIONS = new HashMap<>();
	
	private enum SUPPORTED_ACTIONS {
		LOGIN("login"),
		VIEW_ALL_BOOKS("viewAllBooks"),
		UPDATE_BOOKS("updateBooks"),
		DELETE_BOOKS("deleteBooks"),
		ADD_A_BOOK("addABook");

		private String action;
		
		SUPPORTED_ACTIONS(String action) {
			this.action = action;
		}
		
		public static boolean isValidAction(String action) {
			if (action == null) {
				return false;
			}
			for (SUPPORTED_ACTIONS act:SUPPORTED_ACTIONS.values()) {
				if (action.equalsIgnoreCase(act.action)) {
					return true;
				}
			}
			return false;
		}
		
	}
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	ControllerAction loginAction = new LoginAction();
    	MAP_ACTIONS.put(loginAction.getActionName(),loginAction);

    	ControllerAction addBookAction = new AddBookAction();
    	MAP_ACTIONS.put(addBookAction.getActionName(),addBookAction);

    	ControllerAction deleteBookAction = new DeleteBooksAction();
    	MAP_ACTIONS.put(deleteBookAction.getActionName(),deleteBookAction);

    	ControllerAction updateBooksAction = new UpdateBooksAction();
    	MAP_ACTIONS.put(updateBooksAction.getActionName(),updateBooksAction);

    	ControllerAction viewAllBooksAction = new ViewAllBooksAction();
    	MAP_ACTIONS.put(viewAllBooksAction.getActionName(),viewAllBooksAction);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logParameters) {
			logParameters(request);
		}
		
		String action = request.getParameter("action");
		
		if ( SUPPORTED_ACTIONS.isValidAction(action)) {
			ControllerAction controllerAction = MAP_ACTIONS.get(action);
			controllerAction.action(request, response);
		} else {
			response.getWriter().append("Invalid action requested:").append(action).append("Served at: ").append(request.getContextPath());
		}
		
		
	}
	
	private void logParameters(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			System.out.println("Parameter name:" + parameterName + ",Parameter value:" + parameterValue);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
