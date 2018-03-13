package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beans.TextBean;
import com.util.DatabaseUtil;

public class TextDao {
	private Connection con;
	
	public TextDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public int addText(TextBean text) {
		int status = 0;		
		try {
			String sql = "INSERT INTO `filter_texts`(`filter_text_id`, `filter_text`, `filter_categories_category_id`, `created_date`) VALUES (NULL,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, text.getText());
			psmt.setInt(2, text.getCategoryId());
			
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			psmt.setString(3,dateFormat.format(currentDate));
			
			status = psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return status;		
	}
	
	public int updateText(TextBean text) {
		int status = 0;
		try {
			String sql = "UPDATE `filter_texts` SET `filter_text`= ?,`filter_categories_category_id`= ? WHERE filter_text_id = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, text.getText());
			psmt.setInt(2, text.getCategoryId());
			psmt.setInt(3, text.getTextId());
			status = psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			System.out.println("In Edit");
			e.printStackTrace();
		}		
		return status;		
	}
	
	public List<TextBean> getAllTexts() {
		List<TextBean> textList = new ArrayList<TextBean>();
		ResultSet rs;
		try {
			Statement smt = con.createStatement();
			rs = smt.executeQuery("SELECT `filter_text_id`, `filter_text`,  `category_id`, `category_name`, `created_date` FROM `filter_texts` t LEFT JOIN  `filter_categories` c ON t.`filter_categories_category_id` = c.`category_id`");
			while(rs.next()) {
				TextBean text = new TextBean();
				text.setCategoryId(rs.getInt("category_id"));
				text.setCategoryName(rs.getString("category_name"));
				text.setTextId(rs.getInt("filter_text_id"));
				text.setText(rs.getString("filter_text"));
				text.setCreatedDate(rs.getDate("created_date"));
				textList.add(text);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return textList;
	}
	
	public TextBean getTextById(int textId) {	
		TextBean text = new TextBean() ;
		try {
			PreparedStatement psmt = con.prepareStatement("SELECT `filter_text_id`, `filter_text`, `filter_categories_category_id` FROM `filter_texts` WHERE `filter_text_id` = ?");
			psmt.setInt(1, textId);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				text.setCategoryId(rs.getInt("filter_categories_category_id"));
				text.setTextId(rs.getInt("filter_text_id"));
				text.setText(rs.getString("filter_text"));
			}
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return text;
	}
}
