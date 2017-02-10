package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogoutServlet extends HttpServlet{

	final static Logger log = Logger.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = -7596501926087719933L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("LogoutServlet - doGet");
		
		 HttpSession session=req.getSession();  
         session.invalidate();  
		
         resp.sendRedirect("login.jsp");
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("LogoutServlet - doPot");
		
		 HttpSession session=req.getSession();  
         session.invalidate();  
		
         resp.sendRedirect("login.jsp");
	}
	
}
