package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.beans.RegistrationBean;
import com.util.DatabaseUtil;

public class RegistrationDao {
	private Connection con ;
	
	public RegistrationDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public int createUser(RegistrationBean regBean) {
	
		try {
			String sql = "INSERT INTO `user`"
					+ "(`user_id`, `username`, `password`, `first_name`, `last_name`, `gender`, `dateofbirth`, `profession`, "
					+ "`location`, `email_id`, `mobile_no`, `registered_on`, `filter_categories`, `is_admin`) "
					+ "VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,0,0)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, regBean.getUserName());
			psmt.setString(2, regBean.getPassword());
			psmt.setString(3, regBean.getFirstName());
			psmt.setString(4, regBean.getLastName());
			psmt.setString(5, regBean.getGender());			
			psmt.setString(6, regBean.getDob());
			psmt.setString(7, regBean.getProfession());
			psmt.setString(8, regBean.getLocation());
			psmt.setString(9, regBean.getEmailAddress());
			psmt.setString(10, regBean.getMobileNo());
			
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			psmt.setString(11,dateFormat.format(currentDate));
			
			int status1 = psmt.executeUpdate();
			psmt.close();

			sql = "INSERT INTO `blocking`"
					+ "(`userid`, `attempts`, `day`) "
					+ "VALUES (?,?,?)";
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1,0);
			psmt.setInt(2,0);
			
			java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
       
			psmt.setDate(3,sqlDate);
			
			int status2 = psmt.executeUpdate();
			
			psmt.close();

			if(status1==1 && status2==1)
			{
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public RegistrationBean getUserById(int userId) {
		RegistrationBean regBean = new RegistrationBean();
		try {
			String sql = "SELECT `user_id`, `first_name`, `last_name`, `gender`, `dateofbirth`, `profession`, "
					+ "`location`, `email_id`, `mobile_no`, `registered_on`, `filter_categories` FROM `user` WHERE `user_id` = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, userId);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				regBean.setUserId(rs.getInt("user_id"));
				regBean.setFirstName(rs.getString("first_name"));
				regBean.setLastName(rs.getString("last_name"));
				regBean.setGender(rs.getString("gender"));
				regBean.setDob(rs.getString("dateofbirth"));
				regBean.setProfession(rs.getString("profession"));
				regBean.setLocation(rs.getString("location"));
				regBean.setEmailAddress(rs.getString("email_id"));
				regBean.setMobileNo(rs.getString("mobile_no"));
				regBean.setFilterCategories(rs.getString("filter_categories"));
			}
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return regBean;
	}
	
	public int updateUser(RegistrationBean regBean) {
		int status = 0;
		try {
			String sql = "UPDATE `user` SET `first_name`=?,`last_name`=?,`gender`=?,`dateofbirth`=?,`profession`=?,"
					+ "`location`=?,`email_id`=?,`mobile_no`=?,`filter_categories`=? WHERE `user_id` = ?";
			
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, regBean.getFirstName());
			psmt.setString(2, regBean.getLastName());
			psmt.setString(3, regBean.getGender());			
			psmt.setString(4, regBean.getDob());
			psmt.setString(5, regBean.getProfession());
			psmt.setString(6, regBean.getLocation());
			psmt.setString(7, regBean.getEmailAddress());
			psmt.setString(8, regBean.getMobileNo());
			psmt.setString(9, regBean.getFilterCategories());
			psmt.setInt(10,regBean.getUserId());
			
			status = psmt.executeUpdate();
			psmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
