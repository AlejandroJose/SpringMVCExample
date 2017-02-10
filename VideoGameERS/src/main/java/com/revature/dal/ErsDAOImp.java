package com.revature.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.beans.ExpenseReimbursement;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
import com.revature.web.LoginServlet;

/**
 * Implementation of the ErsDAO
 */
public class ErsDAOImp implements ErsDAO{

	final static Logger log = Logger.getLogger(LoginServlet.class);


	public User getUser(String username, String password) {
		System.out.println("DAO getUser");
		User user = new User();

		String sqlSelect = "SELECT u_id, u_username,u_password,u_firstname,u_lastname,u_email, ur_id "        
				+ "FROM ers_user WHERE u_username = ? AND u_password = ?";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlSelect);	) {
			//System.out.println("DAO2: username: "+username+" password:"+password);

			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			//System.out.println("DAO3");
			while(rs.next()){
				//System.out.println("DAO3.1");
				int uId = rs.getInt("u_id");
				//System.out.println("DAO3.2");
				String uUsername = rs.getString("u_username");
				String uPassword = rs.getString("u_password");
				String firstname = rs.getString("u_firstname");
				String lastname = rs.getString("u_lastname");
				String email = rs.getString("u_email");
				int urId = rs.getInt("ur_id");
				user = new User(uId, uUsername, uPassword,firstname,lastname,email,urId);
				//log.debug("ErsDao: "+ uId + " "+ uUsername+ " ******** "+ uPassword+ " "+firstname+ " "+lastname+ " "+email+ " "+urId);
			}
			//System.out.println("DAO4");
			//return user;
		} catch (SQLException e) {
			log.error("Unable to retrieve user from db.");
			e.printStackTrace();
		}

		return user;
	}


	@Override
	public boolean updateUserInfo(int userId, String firstname, String lastname, String email) {
		System.out.println("DAO updateUserInfo");

		String sqlStoreProcedure = "call update_user_info(?,?,?,?)";

		try (Connection conn = ConnectionUtil.getConnection();
				CallableStatement cstmt = conn.prepareCall(sqlStoreProcedure);	) {

			cstmt.setInt(1, userId);
			cstmt.setString(2, firstname);
			cstmt.setString(3, lastname);
			cstmt.setString(4, email);
			cstmt.executeQuery();

		} catch (SQLException e) {
			log.error("Unable to retrieve user from db.");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean updateUserPassword(int userId, String password) {
		System.out.println("DAO updateUserPassword");

		String sqlStoreProcedure = "call update_user_pass(?,?)";

		try (Connection conn = ConnectionUtil.getConnection();
				CallableStatement cstmt = conn.prepareCall(sqlStoreProcedure);	) {

			cstmt.setInt(1, userId);
			cstmt.setString(2, password);
			cstmt.executeQuery();

		} catch (SQLException e) {
			log.error("Unable to retrieve user from db.");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public ArrayList<ExpenseReimbursement> getEmployeeRequests(int authorId) {

		StringBuilder sqlInnerJoinSelect = new StringBuilder("SELECT r_id, r_amount, r_description, r_receipt, r_submitted, "
				+ "r_resolved,ers_u_a.u_username as \"author\", u_id_resolver as \"resolver\", ers_t.rt_type, rs_status "
				+ "FROM ers_reimbursements ers_r "
				+ "INNER JOIN ers_user ers_u_a "
				+ "ON ers_r.u_id_author = ers_u_a.u_id "
				+ "INNER JOIN ers_reimbursement_type ers_t "
				+ "ON ers_r.rt_type = ers_t.rt_id "
				+ "INNER JOIN ers_reimbursement_status ers_s "
				+ "ON ers_r.rt_status = ers_s.rs_id ");

		sqlInnerJoinSelect.append("WHERE ers_r.u_id_author = ?");


		ArrayList<ExpenseReimbursement> expReims = new ArrayList<ExpenseReimbursement>();

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlInnerJoinSelect.toString());	) {


			pstmt.setInt(1, authorId);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("DAO3");
			while(rs.next()){
				int rId = rs.getInt("r_id");
				double rAmount = rs.getDouble("r_amount");
				String description = rs.getString("r_description");
				byte[] receipt = rs.getBytes("r_receipt");
				Timestamp submitted = rs.getTimestamp("r_submitted");
				Timestamp resolved = rs.getTimestamp("r_resolved");
				String idAuthor = rs.getString("author");
				int idResolver = rs.getInt("resolver");
				String rtType = rs.getString("rt_type");
				String rtStatus = rs.getString("rs_status");
				ExpenseReimbursement expReim = new ExpenseReimbursement(rId, rAmount, description, receipt, submitted,
						resolved, idAuthor, idResolver, rtType, rtStatus);
				expReims.add(expReim);
			}

		} catch (SQLException e) {
			log.error("Unable to retrieve expense reimburstments from db.");
			e.printStackTrace();
		}

		return expReims;
	}


	@Override
	public String getUsernameById(int userId) {
		String username = "";

		String sqlSelect = "SELECT u_username FROM ers_user WHERE u_id = ?";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlSelect);	) {

			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				username = rs.getString("u_username");
			}

		} catch (SQLException e) {
			log.error("Unable to retrieve a username from db.");
			e.printStackTrace();
		}

		return username;
	}


	@Override
	public boolean insertReimbursementRequest(int userId, double amount, String description, Timestamp datetime, int rType) {

		String sqlInsert = "INSERT INTO ers_reimbursements (r_amount, r_description, r_submitted,"
				+ "u_id_author, rt_type, rt_status)"
				+ "VALUES (?,?,?,?,?, 2)";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlInsert);	) {

			pstmt.setDouble(1, amount);
			pstmt.setString(2,description);
			pstmt.setTimestamp(3, datetime);
			pstmt.setInt(4, userId);
			pstmt.setInt(5, rType);
			ResultSet rs = pstmt.executeQuery();

		} catch (SQLException e) {
			log.error("Unable to insert a new reimbursement request into the db.");
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean insertReimbursementRequest(int userId, double amount, String description, byte[] receipt,Timestamp datetime, int rType) {

		String sqlInsert = "INSERT INTO ers_reimbursements (r_amount, r_description, r_receipt, r_submitted,"
				+ "u_id_author, rt_type, rt_status)"
				+ "VALUES (?,?,?,?,?,?, 1)";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlInsert);	) {

			pstmt.setDouble(1, amount);
			pstmt.setString(2,description);
			pstmt.setBytes(3, receipt);	
			pstmt.setTimestamp(4, datetime);
			pstmt.setInt(5, userId);
			pstmt.setInt(6, rType);
			ResultSet rs = pstmt.executeQuery();

		} catch (SQLException e) {
			log.error("Unable to insert a new reimbursement request into the db.");
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public ArrayList<ExpenseReimbursement> getAllRequests() {

		StringBuilder sqlInnerJoinSelect = new StringBuilder("SELECT r_id, r_amount, r_description, r_receipt, r_submitted, "
				+ "r_resolved,ers_u_a.u_username as \"author\", u_id_resolver as \"resolver\", ers_t.rt_type, rs_status "
				+ "FROM ers_reimbursements ers_r "
				+ "INNER JOIN ers_user ers_u_a "
				+ "ON ers_r.u_id_author = ers_u_a.u_id "
				+ "INNER JOIN ers_reimbursement_type ers_t "
				+ "ON ers_r.rt_type = ers_t.rt_id "
				+ "INNER JOIN ers_reimbursement_status ers_s "
				+ "ON ers_r.rt_status = ers_s.rs_id ");


		ArrayList<ExpenseReimbursement> expReims = new ArrayList<ExpenseReimbursement>();

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlInnerJoinSelect.toString());	) {

			ResultSet rs = pstmt.executeQuery();

			System.out.println("DAO3");
			while(rs.next()){
				int rId = rs.getInt("r_id");
				double rAmount = rs.getDouble("r_amount");
				String description = rs.getString("r_description");
				byte[] receipt = rs.getBytes("r_receipt");
				Timestamp submitted = rs.getTimestamp("r_submitted");
				Timestamp resolved = rs.getTimestamp("r_resolved");
				String idAuthor = rs.getString("author");
				int idResolver = rs.getInt("resolver");
				String rtType = rs.getString("rt_type");
				String rtStatus = rs.getString("rs_status");
				ExpenseReimbursement expReim = new ExpenseReimbursement(rId, rAmount, description, receipt, submitted,
						resolved, idAuthor, idResolver, rtType, rtStatus);
				expReims.add(expReim);
				System.out.println("rId: "+ rId + " "+ rAmount+ " description "+ description+ " "+idAuthor+ " "+ idResolver+ " "+rtType+ " "+rtStatus);
			}

		} catch (SQLException e) {
			log.error("Unable to retrieve expense reimburstments from db.");
			e.printStackTrace();
		}

		return expReims;
	}


	@Override
	public boolean updateReimbursementRequest(int requestId, Timestamp resolvedDate, int resolverId, int status) {

		String sqlUpdate = "UPDATE ers_reimbursements SET u_id_resolver = ?, rt_status = ?, r_resolved = ? WHERE r_id = ?";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);	) {

			pstmt.setInt(1, resolverId);
			pstmt.setInt(2, status);
			pstmt.setTimestamp(3, resolvedDate);
			pstmt.setInt(4, requestId);
			ResultSet rs = pstmt.executeQuery();

		} catch (SQLException e) {
			log.error("Unable to update reimbursement request");
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public ArrayList<User> getUsers(int typeId) {
		System.out.println("DAO getAllRequests");

		String sqlSelect = "SELECT * FROM ers_user WHERE ur_id = ?";

		ArrayList<User> employees = new ArrayList<User>();

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlSelect);	) {

			pstmt.setInt(1, typeId);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("DAO3");
			while(rs.next()){
				int empId = rs.getInt("u_id");
				String empUsername = rs.getString("u_username");
				String empFirstName = rs.getString("u_firstname");
				String empLastName = rs.getString("u_lastname");
				String empEmail = rs.getString("u_email");
				int empRole = rs.getInt("ur_id");
				User emp = new User(empId, empUsername,"******", empFirstName, empLastName, empEmail,empRole);
				emp.setRoleName("Employee");
				employees.add(emp);   
			}

		} catch (SQLException e) {
			log.error("Unable to retrieve employees from the db.");
			e.printStackTrace();
		}

		return employees;
	}

}
