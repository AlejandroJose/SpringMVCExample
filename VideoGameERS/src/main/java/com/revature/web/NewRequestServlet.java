package com.revature.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.service.ErsService;

@MultipartConfig(maxFileSize = 16177215) 
public class NewRequestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406844997616812384L;
	final static Logger log = Logger.getLogger(NewRequestServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doGet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("- doPost");

		// Get the ERS Service to access the DAO
		ErsService es = new ErsService(); 

		HttpSession session = req.getSession();

		byte[] r_receipt = null;
		double r_amount = 0.0;
		String r_description = "";
		int r_type_id = 0;
		
		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						r_receipt = item.get();
					} else if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						System.out.println(name + " " + value);
						if (name.equals("amount"))
							r_amount = Double.parseDouble(value);
						if (name.equals("description"))
							r_description = value;
						if (name.equals("type"))
							r_type_id = Integer.parseInt(value);
					}
				}
				// File uploaded successfully
				req.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				req.setAttribute("message", "File Upload Failed due to " + ex);
			}
		} else {
			req.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		log.info(req.getAttribute("message"));

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		User user = (User) session.getAttribute("user");

		if (es.submitReimbursementRequest(user.getUserId(), r_amount, r_description, r_receipt, currentTimestamp, r_type_id)){
			//session.setAttribute("user", user);
			String location = "employeehome.jsp";
			
			RequestDispatcher rd = req.getRequestDispatcher(location);
			//rd.forward(req, resp);
		} else {
			log.error("Unable to submit request");
		}
		//resp.sendRedirect("employee");
	}
}


