package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beans.UserWallFilterBean;
import com.beans.UserWallMessagesBean;
import com.util.DatabaseUtil;

public class UserWallDao {
	private Connection con;
	
	public UserWallDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public List<UserWallMessagesBean> getWallMessages(int wallOf) {
		
		List<UserWallMessagesBean> messageList = new ArrayList<UserWallMessagesBean>();
		ResultSet rs;
		try {
			
			PreparedStatement psmt = con.prepareStatement("SELECT `user_id`, `first_name`, `last_name`, "
					+ "`post_id`, `post_text`, `post_by_fk`, `post_time` FROM "
					+ "`posts` AS p LEFT JOIN user AS u ON u.`user_id` = p.`post_by_fk` WHERE `wall_of_fk` = ? ORDER BY `post_time` DESC ");
			psmt.setInt(1, wallOf);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				UserWallMessagesBean message = new UserWallMessagesBean();
				message.setFirstName(rs.getString("first_name"));
				message.setLastName(rs.getString("last_name"));
				message.setUserid(rs.getInt("user_id"));
				message.setPost(rs.getString("post_text"));
				message.setPostedTime(rs.getString("post_time"));
				message.setPostId(rs.getInt("post_id"));
				message.setPostedById(rs.getInt("post_by_fk"));
				messageList.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	public int addPostMessage(UserWallMessagesBean message, int wallOf) {
		int status = 0;		
		try {
			String sql = "INSERT INTO `posts`(`post_id`, `post_text`, `wall_of_fk`, `post_by_fk`, `post_time`) "
					+ "VALUES (NULL,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, message.getPost());
			psmt.setInt(2, wallOf);
			psmt.setInt(3, message.getPostedById());
			
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			psmt.setString(4,dateFormat.format(currentDate));
			
			status = psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return status;		
	}
	
	public List<UserWallFilterBean> getUserFiltertedValues(int wallOf) {
		
		List<UserWallFilterBean> filterValues = new ArrayList<UserWallFilterBean>();
		
		try {
			String sql = "SELECT `filter_categories` FROM `user` WHERE `user_id` = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, wallOf);
			ResultSet rs = psmt.executeQuery();
			String filterCategories = "0";
			while (rs.next()) {
				filterCategories = rs.getString("filter_categories");
			}			
			psmt.close();
			
			String sql2 = "SELECT * FROM  `filter_categories` AS c LEFT JOIN  `filter_texts` ON  `filter_categories_category_id` = c.`category_id` WHERE c.`category_id` IN ( "+filterCategories+" ) ORDER BY  `c`.`category_name` ASC ";
			psmt = con.prepareStatement(sql2);
			//psmt.setString(1, filterCategories);	

			ResultSet rs2 = psmt.executeQuery();
			while(rs2.next()) {
				UserWallFilterBean fBean = new UserWallFilterBean();
				fBean.setFilterCategory(rs2.getString("category_name"));
				fBean.setFilterText(rs2.getString("filter_text"));
				filterValues.add(fBean);
			}
			
			psmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filterValues;
		
		
	}

	public int updateUserBlocking(int userId)
	{
		try {
			
			String sql1="SELECT ATTEMPTS FROM BLOCKING WHERE USERID=?";

			PreparedStatement psmt1 = con.prepareStatement(sql1);
			
			psmt1.setInt(1,userId);

			ResultSet res=psmt1.executeQuery();
			
			int attempts=0;

			while(res.next())
			{
				attempts=res.getInt(1);
			}
            
			if(attempts==3)
			{
				return 2;
			}
			
			String sql = "UPDATE BLOCKING SET ATTEMPTS=?,day=? WHERE USERID=?";

			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setInt(1,++attempts);

			java.util.Date utilDate = new java.util.Date();
            
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
       
			psmt.setDate(2,sqlDate);
			
			psmt.setInt(3,userId);

			return psmt.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();

		}	
		return 0;
	}
	
}
