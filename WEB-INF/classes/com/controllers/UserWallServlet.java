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

import com.beans.RegistrationBean;
import com.beans.UserSession;
import com.beans.UserWallFilterBean;
import com.beans.UserWallMessagesBean;
import com.dao.RegistrationDao;
import com.dao.UserWallDao;

/**
 * Servlet implementation class UserWallServlet
 */
@WebServlet("/wall")
public class UserWallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserWallServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardViewFile = "wall.jsp";
		
		HttpSession session = request.getSession();		
		UserSession user = (UserSession)session.getAttribute("user");
		int wallOf = user.getUserId();
		
		request.setAttribute("sidebarFile", "includes/user/sidebar.jsp");		
		
		if(request.getParameter("id") != null) {//if others wall
			wallOf = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("sidebarFile", "includes/user/wall-sidebar.jsp");
		}
		
		UserWallDao userWallMeg = new UserWallDao();
		
		List<UserWallMessagesBean> messageList = userWallMeg.getWallMessages(wallOf);
		
		RegistrationDao regDao = new RegistrationDao();
		RegistrationBean regBean = regDao.getUserById(wallOf);		
		request.setAttribute("userData", regBean);
		
		request.setAttribute("messageList",messageList);	
		
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		UserSession user = (UserSession)session.getAttribute("user");
		int wallOf = user.getUserId();
		
		//request.setAttribute("sidebarFile", "includes/user/sidebar.jsp");		
		
		if(request.getParameter("id") != null) {//if others wall
			wallOf = Integer.parseInt(request.getParameter("id"));
			//request.setAttribute("sidebarFile", "includes/user/wall-sidebar.jsp");
		}		
		
		Map<String, String> messages = new HashMap<String, String>();
		session.setAttribute("wmessages", messages);
		
		String postMessage = request.getParameter("postMessage").trim();
		session.setAttribute("postMessage", postMessage);
		if (postMessage == null || postMessage.trim().isEmpty()) {
			messages.put("postMesg", "Please enter your message");			
		}
		
		Map<String, Integer> filterMessages = new HashMap<String, Integer>();
		session.setAttribute("filterMessages", filterMessages);
		String filterCat = null;

		UserWallDao userWallMeg = new UserWallDao();
		
		if(messages.isEmpty()) {
	
			List<UserWallFilterBean> filterValues = userWallMeg.getUserFiltertedValues(wallOf);
		
				for (UserWallFilterBean fbean : filterValues) {
					
					if (filterCat == null || !filterCat.equals(fbean.getFilterCategory())) {
						filterCat = fbean.getFilterCategory();
						filterMessages.put(filterCat, 0);
					}
					if(postMessage.matches("(?i).*\\b"+fbean.getFilterText()+"\\b.*")) {

						filterMessages.put(fbean.getFilterCategory(), filterMessages.get(filterCat)+1);
					
						 int result=userWallMeg.updateUserBlocking(user.getUserId());

						if(result==2)
						{
							session.setAttribute("status","blocked");
						}
						else
						{
							messages.put("filteredStatusMessage", "Your message can't be posted because it was filtered !");
						}
					}
					
				}
			}

			if(messages.isEmpty()) {
				UserWallMessagesBean wallMesg = new UserWallMessagesBean();
				wallMesg.setPost(postMessage);
				wallMesg.setPostedById(user.getUserId());		
				session.setAttribute("statusMsg", userWallMeg.addPostMessage(wallMesg, wallOf));
			}


			if(wallOf != user.getUserId())
				response.sendRedirect("wall?id="+wallOf);
			else
				response.sendRedirect("wall");
      }
}
