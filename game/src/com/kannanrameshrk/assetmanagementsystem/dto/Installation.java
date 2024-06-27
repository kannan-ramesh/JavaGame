package com.kannanrameshrk.assetmanagementsystem.dto;

import java.util.Date;

public class Installation {
	private Software software;
	private Date installationDate;
	
	
	public Installation(Software soft, Date installationDate) {
		this.software=soft;
		this.installationDate=installationDate;
	}
	public Installation() {
		// TODO Auto-generated constructor stub
	}
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	public Date getInstallationDate() {
		return installationDate;
	}
	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}
}
