package com.revature.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.service.ErsService;

public class AuthFilter implements Filter{


	final static Logger log = Logger.getLogger(AuthFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		boolean logged_in = false;

		if(session != null){
			User user = (User) session.getAttribute("user");
			if(user != null){
				logged_in = true;
			}
		} 

		if (request.getRequestURI().contains("login")){

			String username = request.getParameter("inputUsername");
			String password = request.getParameter("inputPassword");

			// Get the ERS Service to access the DAO
			ErsService es = new ErsService(); 

			User user = es.loginUser(username, password);	


			if (user.getUsername() != null){

				// Populating the session value

				session.setAttribute("user", user);
				session.setAttribute("username", username);
				session.setAttribute("password", password);

				System.out.println("RoleId: "+user.getRoleId());
				if (user.getRoleId() == 1){
					response.sendRedirect("employeehome.jsp");
				} else if (user.getRoleId() == 2){
					response.sendRedirect("managerhome.jsp");
				}
				logged_in = true;
			} 


			//Auth code
			log.info("Authenticating");

			// Continue to where you were going
			// chain.doFilter(req, resp);

		}
		

		if(!logged_in){
			//response.sendRedirect("login");
			log.info("Forwarding to login");
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		} else if (!request.getRequestURI().contains("login")){
			chain.doFilter(req, resp);
		}
		
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
