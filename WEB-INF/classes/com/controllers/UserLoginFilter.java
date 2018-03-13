package com.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.UserSession;

/**
 * Servlet Filter implementation class UserLoginFilter
 */
public class UserLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		String status=(String)session.getAttribute("status");

		if(status!=null && status.equals("blocked"))
		{
			res.sendRedirect("user-login.jsp?message=your account is currently blocked try after 15 days");
		}
		else
		{
			if(session.getAttribute("user") != null)
			{
				UserSession user = (UserSession)session.getAttribute("user");
				if(user.isLoggedIn())
				{
					chain.doFilter(request, response);
				}
				else
				{
					res.sendRedirect("user-login.jsp");
				}
			}
			else
			{
				res.sendRedirect("user-login.jsp");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
