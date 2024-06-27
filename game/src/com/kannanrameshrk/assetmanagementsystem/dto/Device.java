package com.kannanrameshrk.assetmanagementsystem.dto;

import java.util.List;

public class Device {
	private String deviceID;
	private Employee owner;
	private List<Installation> installations;
	
	
	
	public Device(String deviceID, Employee employee) {
		this.deviceID=deviceID;
		this.owner=employee;
	}
	public Device() {
		// TODO Auto-generated constructor stub
	}
	public Employee getOwner() {
		return owner;
	}
	public void setOwner(Employee owner) {
		this.owner = owner;
	}
	public List<Installation> getInstallations() {
		return installations;
	}
	public void setInstallations(List<Installation> installations) {
		this.installations = installations;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public void addInstallation(Installation installation) {
		installations.add(installation);
	}
	
}
