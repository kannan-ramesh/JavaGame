package com.kannanrameshrk.atm.dto;

import java.util.ArrayList;
import java.util.List;

public class Atm {
	private List<Customer> customers;
	
	public Atm() {
		customers=new ArrayList<>();
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer checkCustomer(String accNo, String pin) {
		for(Customer c:customers) {
			if(c.getAccountNumber().equals(accNo) && c.getPin().equals(pin)) {
				return c;
			}
		}
		return null;
	}

	public void checkBalance(Customer customer) {
		System.out.println("Your Balance is "+customer.getAmount());
	}

	public void withDrawAmount(Customer customer, double amount) {
		 double balance = customer.getAmount();

	        if (amount > 5000) {
	            if (balance - amount >= 500) {
	                int num1000Notes = 3; // 3 x 1000 Rs notes
	                int num100Notes = 10; // 10 x 100 Rs notes

	                double totalNotesAmount = (num1000Notes * 1000) + (num100Notes * 100);
	                if (amount >= totalNotesAmount) {
	                    balance -= amount;
	                    customer.setAmount(balance);
	                    System.out.println("Withdrawal successful. Your new balance is: " + balance);
	                    System.out.println("Dispensed: " + num1000Notes + " x 1000 Rs notes and " + num100Notes + " x 100 Rs notes");
	                } else {
	                    System.out.println("Unable to dispense the requested amount with the specified denominations.");
	                }
	            } else {
	                System.out.println("Insufficient funds. Minimum balance of 500 must be maintained.");
	            }
	        } else {
	            int num1000Notes = (int) (amount / 1000);
	            if (num1000Notes > 0 && amount <= num1000Notes * 1000) {
	                balance -= amount;
	                customer.setAmount(balance);
	                System.out.println("Withdrawal successful. Your new balance is: " + balance);
	                System.out.println("Dispensed: " + num1000Notes + " x 1000 Rs notes");
	            } else {
	                System.out.println("Unable to dispense the requested amount with the specified denominations.");
	            }
	        }
	}

	public void miniStatement(Customer customer) {
		System.out.println(customer.toString());
	}

}
