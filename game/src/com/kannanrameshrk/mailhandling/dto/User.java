package com.kannanrameshrk.mailhandling.dto;

public class User {
	private String userName;
	private String email;
	private String password;
	
	
	public User(String userName, String mail, String password) {
		this.userName=userName;
		this.email=mail;
		this.password=password;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
