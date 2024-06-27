package com.kannanrameshrk.todo.dto;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	private static final long serialVersionUID=1L;
	
	private String title;
	private String category;
	private String data;
	private Date date;
	private boolean isComplete;
	
	
	public Task(String title, String category,String data, Date date, boolean b) {
		this.title=title;
		this.category=category;
		this.data=data;
		this.date=date;
		this.isComplete=b;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return "Task[Title="+title+",Category="+category+", Data="+data+",date="+date+",isComplete="+isComplete+"]";
	}
}
