package com.kannanrameshrk.atm.dto;

public class Customer {
	private String accountNumber;
	private String pin;
	private double amount;
	
	public Customer(String accountNumber, String pin, int amount) {
		this.accountNumber=accountNumber;
		this.pin=pin;
		this.amount=amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double d) {
		this.amount = d;
	}
	
	public String toString() {
		return "ACCOUNT NUMBER ->"+accountNumber+" PIN-> "+pin;
	}
}
