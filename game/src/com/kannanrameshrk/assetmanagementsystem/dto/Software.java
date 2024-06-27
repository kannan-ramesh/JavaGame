package com.kannanrameshrk.assetmanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

public class Software implements Serializable {
	private static final long serialVersionUID=1;
	
	private String name;
	private Vendor vendor;
	private double costPerDevice;
	private Date expiryDate;
	
	
	public Software(String name, Vendor vendor, double costPerDevice, Date expiryDate) {
		this.name=name;
		this.vendor=vendor;
		this.costPerDevice=costPerDevice;
		this.expiryDate=expiryDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public double getCostPerDevice() {
		return costPerDevice;
	}
	public void setCostPerDevice(double costPerDevice) {
		this.costPerDevice = costPerDevice;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
