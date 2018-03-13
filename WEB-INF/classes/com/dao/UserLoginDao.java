package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.UserSession;
import com.util.DatabaseUtil;

public class UserLoginDao {
	private Connection con;
	
	public UserLoginDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public UserSession autheticateUser(String userName, String userPassword) {
		UserSession user = new UserSession();
		try {
			String sql = "SELECT `user_id`, `username`, `first_name`, `last_name` FROM `user` WHERE `is_admin` = 0 AND (`username` = ? AND `password` = ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setString(2, userPassword);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setFullName((rs.getString("first_name")+" "+rs.getString("last_name")).trim());
				user.setLoggedIn(true);				
			} else {
				user.setLoggedIn(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	public long updateAccount(int userId)
	{
		long diffDays=0;

		try
		{
			java.util.Date toDay= new java.util.Date();
	
			String sql1="SELECT ATTEMPTS,day FROM BLOCKING WHERE USERID=?";

			PreparedStatement psmt1 = con.prepareStatement(sql1);
				
			psmt1.setInt(1,userId);

			ResultSet res=psmt1.executeQuery();
				
			int attempts=0;
			
			java.sql.Date sqlDate=null;

			while(res.next())
			{
				attempts=res.getInt(1);
				sqlDate=res.getDate(2);
			}
			
			java.util.Date attemptUtilDate=new java.util.Date(sqlDate.getTime());

			long diff = toDay.getTime()-attemptUtilDate.getTime();
 
			diffDays = diff / (24 * 60 * 60 * 1000);
 
			if((attempts>=3))
			{
				if(diffDays>=15)
				{
					String sql = "UPDATE BLOCKING SET ATTEMPTS=?,day=? WHERE USERID=?";

					PreparedStatement psmt = con.prepareStatement(sql);
					
					psmt.setInt(1,0);

					java.sql.Date sqlDate1 = new java.sql.Date(toDay.getTime());
			   
					psmt.setDate(2,sqlDate1);
					
					psmt.setInt(3,userId);

					if(psmt.executeUpdate()==1)
					{
						return -1;
					}
				}
			}
			else
			{
				return -2;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return diffDays;
	}
}
