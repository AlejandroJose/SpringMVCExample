package com.revature.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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

public class ManagerServlet extends HttpServlet{

	private static final long serialVersionUID = -2523059497272923808L;
	final static Logger log = Logger.getLogger(ManagerServlet.class);
	private ErsService es = new ErsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doGet");
		
		HttpSession session = req.getSession();	
		ArrayList<ExpenseReimbursement> erRequests =  es.getAllRequests();
		
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
		
		String data = req.getParameter("erId");
		int comma = req.getParameter("erId").indexOf(",");
		int requestId = Integer.parseInt(data.substring(0, comma));
		int approved = Integer.parseInt(data.substring(comma+1));

		User user = (User) req.getSession().getAttribute("user");
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		
		boolean success = true;
		if (approved >0)
			success = es.updateReimbursementRequest(requestId, currentTimestamp, user.getUserId(), 3);
		else {
			success = es.updateReimbursementRequest(requestId, currentTimestamp, user.getUserId(), 4);
		}
		
		resp.sendRedirect("/managerhome.jsp");
	}
	
	
	

}
