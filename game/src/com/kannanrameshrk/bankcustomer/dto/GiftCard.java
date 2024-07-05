package com.kannanrameshrk.bankcustomer.dto;

import java.util.ArrayList;
import java.util.List;

public class GiftCard {
	private String cardNumber;
	private String pin;
	private double balance;
	private boolean block;
	private List<Transaction> transactions=new ArrayList<>();
	
	public GiftCard(String cardNumber, String pin, int balance, boolean block) {
		this.cardNumber=cardNumber;
		this.pin=pin;
		this.balance=balance;
		this.block=block;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
