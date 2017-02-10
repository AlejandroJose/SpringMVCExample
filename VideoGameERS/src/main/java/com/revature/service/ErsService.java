package com.revature.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;
import com.revature.dal.ErsDAOImp;
import com.revature.web.LoginServlet;

public class ErsService {
	
	final static Logger log = Logger.getLogger(LoginServlet.class);
	private ErsDAOImp dao = new ErsDAOImp();
	
	/**
	 * Retrieves the user with the correct username and password from the db.
	 * 
	 * @param username
	 * @param password
	 * @return User
	 */
	public User loginUser(String username, String password){
		
		User user = dao.getUser(username, password);	
		return user;
	}
	
	/**
	 * Updates the current user's info in the database
	 * 
	 * @param User
	 * @return boolean
	 */
	public boolean updateUser(User user){
		return dao.updateUserInfo(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail());
	}

	/**
	 * Updates the current user's password in the database
	 * 
	 * @param User
	 * @return boolean
	 */
	public boolean updateUserPassword(User user){
		return dao.updateUserPassword(user.getUserId(), user.getPassword());
	}
	
	/**
	 * Retrieves an arraylist of employee reimbursements and 
	 * 
	 * @param authorId
	 * @param erStatus
	 * @return ArrayList<ExpenseReimbursement>
	 */
	public ArrayList<ExpenseReimbursement> getEmployeeRequests(int authorId){
		
		ArrayList<ExpenseReimbursement> empReim = dao.getEmployeeRequests(authorId);
		
		// Check if each request has a resolverid and set the username of that resolver
		for (int i=0; i < empReim.size(); i++){
			if (empReim.get(i).getResolverId() == 0){
				empReim.get(i).setResolverUsername("");
			} else {
				empReim.get(i).setResolverUsername(dao.getUsernameById(empReim.get(i).getResolverId()));
			}
			
		}
		
		return empReim;
	}
	
	/**
	 * Retrieves an arraylist of all reimbursements and 
	 * 
	 * @param authorId
	 * @param erStatus
	 * @return ArrayList<ExpenseReimbursement>
	 */
	public ArrayList<ExpenseReimbursement> getAllRequests(){
		
		ArrayList<ExpenseReimbursement> empReim = dao.getAllRequests();
		
		// Check if each request has a resolverid and set the username of that resolver
		for (int i=0; i < empReim.size(); i++){
			if (empReim.get(i).getResolverId() == 0){
				empReim.get(i).setResolverUsername("");
			} else {
				empReim.get(i).setResolverUsername(dao.getUsernameById(empReim.get(i).getResolverId()));
			}
			
		}
		
		return empReim;
	}
	
	/**
	 * Submits a new reimbursement request
	 * 
	 * @param amount
	 * @param description
	 * @param date
	 * @param rType
	 * @return
	 */
	public boolean submitReimbursementRequest(int userId, double amount, String description, Timestamp date, int rType) {	
		return dao.insertReimbursementRequest(userId, amount, description, date, rType);
	}
	
	/**
	 * Submits a new reimbursement request
	 * 
	 * @param amount
	 * @param description
	 * @param date
	 * @param rType
	 * @return
	 */
	public boolean submitReimbursementRequest(int userId, double amount, String description, byte[] receipt, Timestamp date, int rType) {	
		return dao.insertReimbursementRequest(userId, amount, description, receipt, date, rType);
	}
	
	/**
	 * Submits a new reimbursement request
	 * 
	 * @param amount
	 * @param description
	 * @param date
	 * @param rType
	 * @return
	 */
	public boolean updateReimbursementRequest(int requestId, Timestamp date, int resolverId, int status) {	
		return dao.updateReimbursementRequest(requestId, date, resolverId, status);
	}
	
	/**
	 * Return a list of employees
	 * 
	 * @return
	 */
	public ArrayList<User> getEmployees(){
		return dao.getUsers(1); // one for employees
	}
}
