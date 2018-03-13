package com.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.AdminUser;
import com.dao.AdminLoginDao;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLoginAut")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("admin-login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName,userPassword;
		userName = request.getParameter("username");
		userPassword = request.getParameter("userpass");
		
		RequestDispatcher rd = request.getRequestDispatcher("admin-login.jsp");
		
		
		if (userName.trim() == "" || userPassword.trim() == "") {
			request.setAttribute("statusMsg", "Please enter username and password");
			rd.forward(request, response);
		} else {
			AdminLoginDao adminDao = new AdminLoginDao();
			AdminUser adminUser = adminDao.autheticateAdminUser(userName, userPassword);
			HttpSession session = request.getSession();
			session.setAttribute("adminUser", adminUser);
			if (adminUser.isLoggedIn()) {
				response.sendRedirect("word?action=list");
			} else {
				request.setAttribute("statusMsg", "Invalid login details, try again");
				rd.forward(request, response);				
			}
		}
	}

}
