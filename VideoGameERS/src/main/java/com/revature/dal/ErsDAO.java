package com.revature.dal;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;

public interface ErsDAO {

	/**
	 * Retrieves a user from the ERS database whose username and password match.
	 * 
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUser(String username, String password);
	
	/**
	 * Update a user's info to the ERS database
	 * 
	 * @param userId
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return Boolean
	 */
	public boolean updateUserInfo(int userId, String firstname, String lastname, String email);
	
	/**
	 * Insert a user's password to the ERS database
	 * 
	 * @param userId
	 * @param password
	 * @return Boolean
	 */
	public boolean updateUserPassword(int userId, String password);
	
	/**
	 * Retrieves a list of expense reimbursement request associated with an author id and the status 
	 * of request. If erStatus is equal to 0 the it should return all expense reimbursements
	 * 
	 * @param authorId
	 * @param erStatus
	 * @return ArrayList<ExpenseReimbursement>
	 */
	public ArrayList<ExpenseReimbursement> getEmployeeRequests(int authorId);
	
	/**
	 * Retrieves a list of expense reimbursement request
	 * 
	 * @param authorId
	 * @param erStatus
	 * @return ArrayList<ExpenseReimbursement>
	 */
	public ArrayList<ExpenseReimbursement> getAllRequests();
	
	
	/**
	 * Returns a username using a user id
	 * 
	 * @param userId
	 * @return ArrayList<ExpenseReimbursement>
	 */
	public String getUsernameById(int userId) ;
	
	/**
	 * Inserts a new employee reimbursement request
	 * 
	 * @param amount
	 * @param description
	 * @param date
	 * @param rType
	 * @return boolean
	 */
	public boolean insertReimbursementRequest(int userId, double amount, String description, Timestamp date, int rType);
	
	/**
	 * Inserts a new employee reimbursement request
	 * 
	 * @param amount
	 * @param description
	 * @param date
	 * @param rType
	 * @return boolean
	 */
	public boolean insertReimbursementRequest(int userId, double amount, String description, byte[] receipt, Timestamp date, int rType);
	
	/**
	 * Updates a reimbursement requests status and sets the date and resolver
	 * 
	 * @param requestId
	 * @param resolverId
	 * @return boolean
	 */
	public boolean updateReimbursementRequest(int requestId, Timestamp resolvedDate, int resolverId, int status);

	/**
	 * Retrieves a list of employees
	 * 
	 * @param authorId
	 * @param erStatus
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getUsers(int typeId);
}
