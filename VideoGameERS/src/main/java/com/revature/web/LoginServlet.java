package com.revature.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.service.ErsService;

public class LoginServlet extends HttpServlet{

	final static Logger log = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("LoginServlet - doGet");
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("LoginServlet - doPost");
		
		// Get the session from the request - Pulls from the J_session_id_Cookie
		HttpSession session = req.getSession();
		
		// Getting user input from the Request
		String username = req.getParameter("inputUsername");
		String password = req.getParameter("inputPassword");

		//Check if the credentials were specified
		if(username == (null) || username.equals("") || password == (null) || password.equals("")  ){
			log.debug("Username or password is null or empty");
			resp.sendRedirect("login.jsp");		
		} 
		
		// Get the ERS Service to access the DAO
		ErsService es = new ErsService(); 
		
		User user = es.loginUser(username, password);
		
		if (user.getUsername() != null){
			
			// Populating the session value
			session.setAttribute("user", user);
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			
			if (user.getRoleId() == 1){
				resp.sendRedirect("employeehome.jsp");
			} else if (user.getRoleId() == 2){
				resp.sendRedirect("managerhome.jsp");
			}
			
		} 
		log.debug("user returned null");
		if (user.getUsername() == null){
			System.out.println("user == null");
		}
	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet - init()");
		super.init();
	}
	
}
