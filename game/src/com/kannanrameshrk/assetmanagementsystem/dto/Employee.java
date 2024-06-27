package com.kannanrameshrk.assetmanagementsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String employeeId;
	private String name;
	private List<Device> devices;
	
	public Employee(String employeeId, String name) {
		this.employeeId=employeeId;
		this.name=name;
		this.devices=new ArrayList<>();
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void addDevice(Device device) {
		devices.add(device);
		
	}
}
