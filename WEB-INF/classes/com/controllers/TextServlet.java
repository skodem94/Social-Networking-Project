package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CategoryBean;
import com.beans.TextBean;
import com.dao.CategoryDao;
import com.dao.TextDao;

/**
 * Servlet implementation class TextServlet
 */
@WebServlet("/word")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forwardViewFile = "words.jsp";
		String action = "list";
		CategoryDao categoryDao = new CategoryDao();
		TextDao textDao = new TextDao();
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		
		if(action.equalsIgnoreCase("add")) {
			List<CategoryBean> list = categoryDao.getAllCategories();
			request.setAttribute("categories",list);
			forwardViewFile = "addword.jsp";			
		} else if(action.equalsIgnoreCase("edit")) {
			int textId = Integer.parseInt(request.getParameter("id"));
			List<CategoryBean> list = categoryDao.getAllCategories();
			request.setAttribute("categories",list);
			TextBean text = textDao.getTextById(textId);
			request.setAttribute("text",text);
			forwardViewFile = "editword.jsp";
		} else {
			request.setAttribute("words",textDao.getAllTexts());
			forwardViewFile = "words.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardViewFile = "words.jsp";
		String action = "list";
		CategoryDao categoryDao = new CategoryDao();
		TextDao textDao = new TextDao();
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		TextBean text = new TextBean();
		
		if(action.equalsIgnoreCase("add")) {
			
			text.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			text.setText(request.getParameter("wordText"));
			int statusMsg = textDao.addText(text);
			request.setAttribute("statusMsg", statusMsg);
			
			List<CategoryBean> list = categoryDao.getAllCategories();
			request.setAttribute("categories",list);
			forwardViewFile = "addword.jsp";			
		} else if(action.equalsIgnoreCase("edit")) {
			
			text.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			text.setTextId(Integer.parseInt(request.getParameter("id")));
			text.setText(request.getParameter("wordText"));
			int statusMsg = textDao.updateText(text);
			request.setAttribute("statusMsg", statusMsg);
			
			int textId = Integer.parseInt(request.getParameter("id"));
			List<CategoryBean> list = categoryDao.getAllCategories();
			request.setAttribute("categories",list);
			TextBean textBean = textDao.getTextById(textId);
			request.setAttribute("text",textBean);		
			
			forwardViewFile = "editword.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);		
	}

}
