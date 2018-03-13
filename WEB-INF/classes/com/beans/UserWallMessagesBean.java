package com.beans;

public class UserWallMessagesBean extends FriendsBean{
	
	private int postId,postedById;
	private String post,postedTime;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getPostedById() {
		return postedById;
	}
	public void setPostedById(int postedById) {
		this.postedById = postedById;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	

}
