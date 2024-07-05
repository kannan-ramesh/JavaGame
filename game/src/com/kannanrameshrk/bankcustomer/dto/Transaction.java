package com.kannanrameshrk.bankcustomer.dto;

import java.util.Date;

public class Transaction {
	private String type;
	private double amount;
	private Date date;
	
	
	public Transaction(String type, double amount, Date date) {
		this.type=type;
		this.amount=amount;
		this.date=date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
