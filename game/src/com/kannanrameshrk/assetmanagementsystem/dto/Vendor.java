package com.kannanrameshrk.assetmanagementsystem.dto;

public class Vendor {
	private String vendorId;
	private String name;
	
	
	public Vendor(String vendorID, String name) {
		this.vendorId=vendorID;
		this.name=name;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
