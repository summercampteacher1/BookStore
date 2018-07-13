package com.bookstore.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ControllerAction {
	
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param request HttpServletResponse
	 * @return String the name of the JSP/HTML page to forward the request to
	 */
	public abstract void action(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException;
	
	public abstract String getActionName();

}
