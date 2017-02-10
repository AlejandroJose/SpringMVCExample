package com.revature.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;
import com.revature.dal.ErsDAO;
import com.revature.dal.ErsDAOImp;
import com.revature.service.ErsService;

public class ErsDAOTest {

	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// Setup
	ErsDAO dao = new ErsDAOImp();
	ErsService es = new ErsService();
	
	/**
	 * Test the to see if you can receive a user from the database
	 */
	@Test
	public void getUserTest() {
		
		User mario = new User();
		mario.setUserId(1);
		User user = es.loginUser("mario", "1");
		
		assertTrue(user.getUserId() == 1 && user.getUsername().equals("mario"));
	}


	/**
	 * Test if you can retrieve the request
	 */
	@Test
	public void getRequestTest() {
		
		ArrayList<ExpenseReimbursement> reimburstments = es.getAllRequests();
		
		boolean allRequestHaveInfo = true;
		
		for (int i = 0; i < reimburstments.size(); i++){
			if (reimburstments.get(i).getId() == 0){
				allRequestHaveInfo = false;
			} else {
				System.out.println("id: " + reimburstments.get(i).getId());
			}
		}
		
		assertTrue(allRequestHaveInfo);
	}
	
	/**
	 * Test if you can submit a request
	 */
	/*@Test
	public void submitRequestTest() {
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		
		boolean submitSuccess = es.submitReimbursementRequest(1, 0.0, "FromJunit", currentTimestamp, 3);

		assertTrue(submitSuccess);
	}*/
	
	/**
	 * Test if you can update a user account
	 */
	@Test
	public void updateUserTest() {
		
		User user = es.loginUser("mario", "1");
		user.setFirstName("mario1");
		es.updateUser(user);
		User user2 = es.loginUser("mario", "1");
		
		assertTrue(user2.getFirstName().equals("mario1"));
	}
	
	/**
	 * Test if you can approve or deny a user
	 */
	@Test
	public void updateRequestTest() {
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		
		boolean updateSuccess = es.updateReimbursementRequest(46, currentTimestamp, 2, 4);
		
		assertTrue(updateSuccess);
	}
	
	
}
