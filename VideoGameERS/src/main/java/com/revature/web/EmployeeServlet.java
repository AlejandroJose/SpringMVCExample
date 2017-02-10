package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;
import com.revature.service.ErsService;

public class EmployeeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5881692309588681902L;
	final static Logger log = Logger.getLogger(EmployeeServlet.class);
	private ErsService es = new ErsService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doGet");
		
		HttpSession session = req.getSession();	
		User user = (User) session.getAttribute("user");
		ArrayList<ExpenseReimbursement> erRequests =  es.getEmployeeRequests(user.getUserId());
		
		for (int i = 0; i <= erRequests.size()-1; i++){
			String encodeBase64 = new String (Base64.encodeBase64(
					erRequests.get(i).getReceipt()));
			erRequests.get(i).setBase64Image(encodeBase64);
		}
		
		session.setAttribute("erRequests", erRequests);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doPost");
	}

	
	
}
