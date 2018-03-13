package com.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.UserSession;
import com.dao.UserLoginDao;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userLoginAut")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("user-login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName,userPassword;
		userName = request.getParameter("username");
		userPassword = request.getParameter("userpass");
		
		RequestDispatcher rd = request.getRequestDispatcher("user-login.jsp");
		
		
		if (userName.trim() == "" || userPassword.trim() == "") {
			request.setAttribute("statusMsg", "Please enter username and password");
			rd.forward(request, response);
		} else {
			UserLoginDao userDao = new UserLoginDao();
			UserSession user = userDao.autheticateUser(userName, userPassword);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			if (user.isLoggedIn()) {

				long status=userDao.updateAccount(user.getUserId());

				if(status==-2)
				{
					System.out.println("no problem");
				}
				else
				{
					if(status==-1)
					{
						System.out.println("account is updated rightnow");
					}
					else
					{
						System.out.println("user still blocked");

						session.invalidate();
					}
				}

				response.sendRedirect("wall");

			} else {
				request.setAttribute("statusMsg", "Invalid login details, try again");
				rd.forward(request, response);				
			}
		}
	}

}
