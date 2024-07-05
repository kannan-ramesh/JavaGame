package com.kannanrameshrk.bankcustomer.dto;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String id;
	private String password;
	private double balance;
	private List<GiftCard> giftcards=new ArrayList<>();
	
	public Customer(String id, String pwd, int amount) {
		this.id=id;
		this.password=pwd;
		this.balance=amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<GiftCard> getGiftcards() {
		return giftcards;
	}
	public void setGiftcards(List<GiftCard> giftcards) {
		this.giftcards = giftcards;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
