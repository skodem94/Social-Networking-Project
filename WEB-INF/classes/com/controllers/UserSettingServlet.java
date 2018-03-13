package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.beans.CategoryBean;
import com.beans.RegistrationBean;
import com.beans.UserSession;
import com.dao.CategoryDao;
import com.dao.RegistrationDao;

/**
 * Servlet implementation class UserSettingServlet
 */
@WebServlet("/settings")
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardViewFile = "settings.jsp";
		/*String action = "list";
		boolean isForward = true;
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");*/
		
		HttpSession session = request.getSession();		
		UserSession user = (UserSession)session.getAttribute("user");
		
		RegistrationDao regDao = new RegistrationDao();
		RegistrationBean regBean = regDao.getUserById(user.getUserId());
		
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryBean> list = categoryDao.getAllCategories();
		request.setAttribute("categories",list);
		
		request.setAttribute("userData", regBean);
		
		String[] fcategories = StringUtils.split(regBean.getFilterCategories(), ',');
		request.setAttribute("fcategories", fcategories);
		
		request.setAttribute("sidebarFile", "includes/user/sidebar.jsp");
		forwardViewFile = "settings.jsp";		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardViewFile = "settings.jsp";
		
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		
		String firstName, lastName, dob, profession, location, emailAddress, mobileNo;
		
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
		
		
		String[] stringP = request.getParameterValues("filterCategories");
		
		String filterCategories = StringUtils.join(stringP, ',');
		if(filterCategories == null) {
			filterCategories = "0";
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
			regBean.setGender(request.getParameter("gender"));
			
			HttpSession session = request.getSession();		
			UserSession user = (UserSession)session.getAttribute("user");
			
			regBean.setUserId(user.getUserId());
			regBean.setFilterCategories(filterCategories);
			
			RegistrationDao regDao = new RegistrationDao();
			request.setAttribute("statusMsg", regDao.updateUser(regBean));			
			
		}
		
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryBean> list = categoryDao.getAllCategories();
		request.setAttribute("categories",list);
		
		String[] fcategories = StringUtils.split(filterCategories, ',');
		request.setAttribute("fcategories", fcategories);
		
		request.setAttribute("sidebarFile", "includes/user/sidebar.jsp");
		forwardViewFile = "settings.jsp";		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);

	}

}
