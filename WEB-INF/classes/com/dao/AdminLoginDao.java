package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.AdminUser;
import com.util.DatabaseUtil;

public class AdminLoginDao {
	
	private Connection con;
	
	public AdminLoginDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public AdminUser autheticateAdminUser(String userName, String userPassword) {
		AdminUser adminUser = new AdminUser();
		try {
			String sql = "SELECT `user_id`, `username`, `first_name`, `last_name` FROM `user` WHERE `is_admin` = 1 AND (`username` = ? AND `password` = ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setString(2, userPassword);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				adminUser.setUserId(rs.getInt("user_id"));
				adminUser.setUserName(rs.getString("username"));
				adminUser.setFullName((rs.getString("first_name")+" "+rs.getString("last_name")).trim());
				adminUser.setLoggedIn(true);				
			} else {
				adminUser.setLoggedIn(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminUser;
		
	}

}
