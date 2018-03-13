package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.FriendsBean;
import com.beans.UserSession;
import com.dao.FriendsDao;


/**
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/friends")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardViewFile = "friends.jsp";
		String action = "list";
		boolean isForward = true;
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		HttpSession session = request.getSession();		
		UserSession user = (UserSession)session.getAttribute("user");
		int userId = user.getUserId();
		FriendsDao friendsDao = new FriendsDao();
		
				
		if(action.equalsIgnoreCase("find")) {
			List<FriendsBean> friendsList = friendsDao.getNonFriends(userId);
			request.setAttribute("friendsList",friendsList);
			request.setAttribute("pageType", 1);
		} else if (action.equalsIgnoreCase("add")) {
			
			friendsDao.addNewFriend(userId, Integer.parseInt(request.getParameter("id")));
			session.setAttribute("successMsg", "You have added new friend successfully !");
			response.sendRedirect("friends");
			isForward = false;
		}		
		else {
			List<FriendsBean> friendsList = friendsDao.getFriends(userId);
			request.setAttribute("friendsList",friendsList);
			request.setAttribute("pageType", 0);
		}
		if(isForward) {
			
			if(session.getAttribute("successMsg") != null) {
				request.setAttribute("successMsg", session.getAttribute("successMsg"));
				session.setAttribute("successMsg", null);
			}
			
			request.setAttribute("sidebarFile", "includes/user/sidebar.jsp");
			forwardViewFile = "friends.jsp";		
			RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
