package com.beans;

import java.util.Date;

public class TextBean {
	private int categoryId;
	private String categoryName;
	private int textId;
	private String text;
	private Date createdDate;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getTextId() {
		return textId;
	}
	public void setTextId(int textId) {
		this.textId = textId;
	}
	
	@Override
	public String toString() {
		return "TextBean [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", textId=" + textId + ", text=" + text
				+ ", createdDate=" + createdDate + "]";
	}
}
