package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;
import com.revature.service.ErsService;

public class ProfileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2778633925725651818L;
	final static Logger log = Logger.getLogger(ProfileServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("ProfileServlet - doGet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("ProfileServlet - doPost");	
		
		// Get the ERS Service to access the DAO
		ErsService es = new ErsService(); 
	
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		user.setFirstName(req.getParameter("firstname"));
		user.setLastName(req.getParameter("lastname"));
		user.setEmail(req.getParameter("email"));

		if (es.updateUser(user)){
			session.setAttribute("user", user);
			System.out.println("User updated successfully");
		} else {
			System.out.println("Unable to update user.");
		}

		

	}

}
