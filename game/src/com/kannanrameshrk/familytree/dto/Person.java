package com.kannanrameshrk.familytree.dto;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String gender;
	private String fatherName;
	private String motherName;
	
	
	

	public Person(String name, String gender, String father, String mother) {
		this.name=name;
		this.gender=gender;
		this.fatherName=father;
		this.motherName=mother;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	
	
	public String toString() {
		return name+"   "+gender+"   "+fatherName+"   "+motherName;
	}
}
