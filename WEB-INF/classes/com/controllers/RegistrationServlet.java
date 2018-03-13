package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.RegistrationBean;
import com.dao.RegistrationDao;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		
		String firstName, lastName, dob, profession, location, emailAddress, mobileNo, userName, password;
		
		firstName = request.getParameter("firstname");
		if(firstName == null || firstName.trim().isEmpty()) {
			messages.put("firstname", "Please enter firstname");
		}
		
		lastName = request.getParameter("lastname");
		if(lastName == null || lastName.trim().isEmpty()) {
			messages.put("lastname", "Please enter lastname");
		}
		
		dob = request.getParameter("dob");
		if(dob == null || dob.trim().isEmpty()) {
			messages.put("dob", "Please enter Date of Birth");
		}
		
		profession = request.getParameter("profession");
		if(profession == null || profession.trim().isEmpty()) {
			messages.put("profession", "Please enter Profession");
		}
		
		location = request.getParameter("location");
		if (location == null || location.trim().isEmpty()) {
			messages.put("location", "Please enter Location");			
		}
		
		emailAddress = request.getParameter("email");
		if (emailAddress == null || emailAddress.trim().isEmpty()) {
			messages.put("email", "Please enter Email Address");			
		}
		
		mobileNo = request.getParameter("mobileno");
		if (mobileNo == null || mobileNo.trim().isEmpty()) {
			messages.put("mobileno", "Please enter Mobile No.");			
		} else if (!mobileNo.matches("\\d+")) {
            messages.put("mobileno", "Please enter digits only");
        }
		
		userName = request.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("username", "Please enter UserName");			
		}
		
		password = request.getParameter("password");
		if (password == null || password.trim().isEmpty()) {
			messages.put("password", "Please enter Password");
		}
		
		
		if (messages.isEmpty()) {
			//messages.put("success", "all fields are valid successfully");
			RegistrationBean regBean = new RegistrationBean();
			
			regBean.setFirstName(firstName);
			regBean.setLastName(lastName);
			regBean.setDob(dob);
			regBean.setProfession(profession);
			regBean.setLocation(location);
			regBean.setEmailAddress(emailAddress);
			regBean.setMobileNo(mobileNo);
			regBean.setUserName(userName);
			regBean.setPassword(password);
			regBean.setGender(request.getParameter("gender"));
			
			RegistrationDao regDao = new RegistrationDao();
			request.setAttribute("statusMsg", regDao.createUser(regBean));			
			
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);		
		
		
	}

}
