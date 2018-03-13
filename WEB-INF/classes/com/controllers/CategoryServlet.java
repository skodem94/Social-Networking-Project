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
import com.dao.CategoryDao;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forwardViewFile = "categories.jsp";		
		String action = "list";
		
		if(request.getParameter("action") != null && request.getParameter("action").trim() != "")
			action = request.getParameter("action");
		
		//CategoryBean category = new CategoryBean();
		CategoryDao categoryDao = new CategoryDao();
		
		if(action.equalsIgnoreCase("add")) {
			forwardViewFile = "addcategory.jsp";
		} else if (action.equalsIgnoreCase("edit")) {
			if(request.getParameter("id") != null) {
				CategoryBean category = categoryDao.getCategoryById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("category",category);
			}
			forwardViewFile = "editcategory.jsp";
		} else{
			List<CategoryBean> list = categoryDao.getAllCategories();
			request.setAttribute("categories",list);
			forwardViewFile = "categories.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardViewFile);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forwardViewFile = "categories.jsp";		
		String action = "list";
		
		if(request.getParameter("action") != null && request.getParameter("action").trim() != "")
			action = request.getParameter("action");
		
		CategoryBean category = new CategoryBean();
		CategoryDao categoryDao = new CategoryDao();
		
		if (action.equalsIgnoreCase("edit")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			category.setCategoryId(categoryId);
			category.setCategoryName(request.getParameter("categoryname"));
			
			request.setAttribute("statusMsg",categoryDao.updateCategory(category));
			
			request.setAttribute("category",categoryDao.getCategoryById(categoryId));
			
			forwardViewFile = "editcategory.jsp";
		} else {
			category.setCategoryName(request.getParameter("categoryname"));
			request.setAttribute("statusMsg",categoryDao.addCategory(category));
			forwardViewFile = "addcategory.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forwardViewFile);
		view.forward(request, response);
		
	}

}
