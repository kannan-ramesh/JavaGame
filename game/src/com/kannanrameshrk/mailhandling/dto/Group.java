package com.kannanrameshrk.mailhandling.dto;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private String groupName;
	private String email;
	private String password;
	private String description;
	private List<User> members=new ArrayList<>();
	
	
	public Group(String groupName, String email, String description) {
		this.groupName=groupName;
		this.email=email;
		this.description=description;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public void addMember(User user) {
		if(!members.contains(user))
			members.add(user);
	}
	
	
}
