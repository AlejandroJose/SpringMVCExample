package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.service.ErsService;

public class ViewEmployeesServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8900412587387880301L;
	final static Logger log = Logger.getLogger(ManagerServlet.class);
	private ErsService es = new ErsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doGet");
		
		HttpSession session = req.getSession();
		ArrayList<User> employees = es.getEmployees();
		
		session.setAttribute("ersEmployees", employees);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doPost");
	}
	


	
}
