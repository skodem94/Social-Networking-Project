package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.FriendsBean;
import com.util.DatabaseUtil;

public class FriendsDao {
	private Connection con;
	
	public FriendsDao() {
		con = DatabaseUtil.getConnection();
	}
	
	public List<FriendsBean> getFriends(int userId) {
		List<FriendsBean> friendList = new ArrayList<FriendsBean>();
		ResultSet rs;
		try {
			
			PreparedStatement psmt = con.prepareStatement("SELECT `user_id`, `first_name`, `last_name`, `gender`, `profession`, `location` FROM `user` WHERE `is_admin` = '0' AND `user_id` IN (SELECT `friend` AS friendids FROM `friends` WHERE `friend_of_fk` = ? UNION SELECT `friend_of_fk` AS friendids FROM `friends` WHERE `friend` = ? )");
			psmt.setInt(1, userId);
			psmt.setInt(2, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				FriendsBean friend = new FriendsBean();
				friend.setFirstName(rs.getString("first_name"));
				friend.setLastName(rs.getString("last_name"));
				friend.setGender(rs.getString("gender"));
				friend.setProfession(rs.getString("profession"));
				friend.setLocation(rs.getString("location"));
				friend.setUserid(rs.getInt("user_id"));

				friendList.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendList;
	}
	
	public List<FriendsBean> getNonFriends(int userId) {
		List<FriendsBean> friendList = new ArrayList<FriendsBean>();
		ResultSet rs;
		try {
			
			PreparedStatement psmt = con.prepareStatement("SELECT `user_id`, `first_name`, `last_name`, `gender`, `profession`, `location` FROM `user` WHERE `is_admin` = '0' AND `user_id` != ? AND `user_id` NOT IN (SELECT `friend` AS friendids FROM `friends` WHERE `friend_of_fk` = ? UNION SELECT `friend_of_fk` AS friendids FROM `friends` WHERE `friend` = ? )");
			psmt.setInt(1, userId);
			psmt.setInt(2, userId);
			psmt.setInt(3, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				FriendsBean friend = new FriendsBean();
				friend.setFirstName(rs.getString("first_name"));
				friend.setLastName(rs.getString("last_name"));
				friend.setGender(rs.getString("gender"));
				friend.setProfession(rs.getString("profession"));
				friend.setLocation(rs.getString("location"));
				friend.setUserid(rs.getInt("user_id"));

				friendList.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendList;
	}
	
	public int addNewFriend(int friendOf, int friend) {
		
		int status = 0;
		try {
			String sql = "INSERT INTO `friends`(`friend_id`, `friend_of_fk`, `friend`, `role`) VALUES (NULL,?,?,'ADDED')";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, friendOf);
			psmt.setInt(2, friend);			
			status = psmt.executeUpdate();
			psmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	
}
