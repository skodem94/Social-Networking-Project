package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.CategoryBean;
import com.util.DatabaseUtil;

public class CategoryDao {
	private Connection con;
	
	public CategoryDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public int addCategory(CategoryBean category) {
		int status;
		try {
			PreparedStatement psmt = con.prepareStatement("INSERT INTO `filter_categories`(`category_name`) VALUES (?)");			
			psmt.setString(1,category.getCategoryName());			
			status = psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			status = 0;
		}		
		return status;		
	}
	
	public int updateCategory(CategoryBean category) {
		int status;
		try {
			PreparedStatement psmt = con.prepareStatement("UPDATE `filter_categories` SET `category_name`= ? WHERE `category_id` = ?");			
			psmt.setString(1,category.getCategoryName());
			psmt.setLong(2,category.getCategoryId());	
			status = psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			status = 0;
		}		
		return status;		
	}
	
	
	public List<CategoryBean> getAllCategories() {
		
		List<CategoryBean> catList = new ArrayList<CategoryBean>();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT category_id,category_name FROM filter_categories");
			while (rs.next()) {
				CategoryBean category = new CategoryBean();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				catList.add(category);			
			}
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return catList;
		
	}
	
	public CategoryBean getCategoryById(int categoryId) {
		CategoryBean category = new CategoryBean();
		try {
			PreparedStatement psmt = con.prepareStatement("SELECT category_id,category_name FROM filter_categories WHERE category_id = ?");
			psmt.setLong(1, categoryId);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return category;
		
	}
	
	
	

}
