package com.kannanrameshrk.atm;

import java.util.Scanner;

import com.kannanrameshrk.atm.dto.Atm;
import com.kannanrameshrk.atm.dto.Customer;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		
		System.out.println("\t\t ATM APP");
		System.out.println("\t\t*********");
		
		Atm atm=new Atm();
		atm.addCustomer(new Customer("123456789","3598",10000));
		atm.addCustomer(new Customer ("123456788","3598",20000));
		
		System.out.println("Enter Account Number:");
		String accNo=input.next();
		System.out.println("Enter Pin Number:");
		String pin=input.next();
		
		Customer customer=atm.checkCustomer(accNo,pin);
		
		if(customer==null) {
			System.out.println("Invalid account Number And Password..");
			return;
		}
			while(true) {
				System.out.println("1.Check Balance\n 2.WithDraw Ammount\n 3.Transfer Money\n 4.Mini Statement\n 5.Exit");
				System.out.println("Choose Option:");
				int choice=input.nextInt();
				
				switch(choice) {
				case 1:{
					System.out.println("\t\t Balance");
					System.out.println("\t\t*********");
					atm.checkBalance(customer);
					break;
				}
				case 2:{
					System.out.println("\t\t Amount Withdraw");
					System.out.println("\t\t*****************");
					
					System.out.println("Enter Amount to withdraw:");
					double amount=input.nextDouble();
					atm.withDrawAmount(customer,amount);
					break;
				}
				case 3:{
					System.out.println("\t\tTransfer Money");
					System.out.println("\t\t***************");
					break;
				}
				case 4:{
					System.out.println("\t\tMini Statement");
					System.out.println("\t\t***************");
					atm.miniStatement(customer);
					break;
				}
				case 5:{
					return;
				}
				default:{
					System.out.println("Invalid Choice...");
					break;
				}
				}
			}
	}

}
