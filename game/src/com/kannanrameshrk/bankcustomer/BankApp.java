package com.kannanrameshrk.bankcustomer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.kannanrameshrk.bankcustomer.dto.Customer;
import com.kannanrameshrk.bankcustomer.dto.GiftCard;
import com.kannanrameshrk.bankcustomer.dto.Transaction;

public class BankApp {
	Scanner input=new Scanner(System.in);
	 Random random = new Random();
	Map<String,Customer> customers=new HashMap<>();
	
	public BankApp() {
		Customer cus = new Customer("12345", encryptPassword("nrkannan"), 500);
	    Customer cus1 = new Customer("12346", encryptPassword("15410198"), 500);
	    Customer cus2 = new Customer("12347", encryptPassword("ramesh"), 500);
		customers.put("12345", cus);
		customers.put("12346", cus1);
		customers.put("12347", cus2);
	}
	public void startApplication() {
	
		while(true) {
			System.out.println("1. Account Login");
			System.out.println("2. Purchase");
			System.out.println("Enter Your Choice:");
			int choice=input.nextInt();
			input.nextLine();
			
			if(choice==1) {
				accountLogin();
			}else if(choice==2) {
				giftCardLogin();
			}else {
				System.out.println("Invalid Choice..");
			}
		}
		
	}

	private void giftCardLogin() {
		System.out.println("Enter GiftCard Number:");
		String giftCardNumber=input.nextLine();
		System.out.println("Enter GiftCardPin :");
		String pin=input.nextLine();
		
		GiftCard card=findGiftCardNumber(giftCardNumber);
		
		if(card!=null && card.getPin().equals(pin) && !card.isBlock()) {
			 giftCardMenu(card);
		}else {
			 System.out.println("Invalid credentials or Gift Card is Blocked.");
		}
	}

	private void giftCardMenu(GiftCard card) {
		while (true) {
            System.out.println("1. Purchase");
            System.out.println("2. Redeem Points");
            System.out.println("3. Logout");
            int choice = input.nextInt();
            input.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    purchase(card);
                    break;
                case 2:
                    redeemPoints(card);
                    break;
                case 3:
                    return;
            }
		}
		
	}

	private void redeemPoints(GiftCard card) {
		  int rewardPoints = (int) (card.getTransactions().stream().filter(t -> t.getType().equals("Purchase")).mapToDouble(t -> t.getAmount()).sum() / 100);
	        if (rewardPoints >= 10) {
	            card.getTransactions().add(new Transaction("Redeem Points", 10, new Date()));
	            double bal=card.getBalance()+10;
	            card.setBalance(bal);
	            System.out.println("Redeemed 10 Points. New Balance: " + card.getBalance());
	        } else {
	            System.out.println("Not enough points to redeem.");
	        }
		
	}

	private void purchase(GiftCard card) {
		System.out.println("Enter Purchase Amount:");
		double amount=input.nextDouble();
		input.nextLine();
		
		if(card.getBalance()>=amount) {
			double bal=card.getBalance()-amount;
			card.setBalance(bal);
			card.getTransactions().add(new Transaction("Purchase",amount,new Date()));
			  System.out.println("Purchase Successful. Remaining Balance: " + card.getBalance());
		}else {
			 System.out.println("Insufficient Balance.");
		}
		
	}

	private GiftCard findGiftCardNumber(String giftCardNumber) {
		for(Customer c:customers.values()) {
			for(GiftCard card:c.getGiftcards()) {
				if(card.getCardNumber().equals(giftCardNumber)) {
					return card;
				}
			}
		}
		return null;
	}

	private void accountLogin() {
		System.out.println("Enter Customer ID:");
		String id=input.nextLine();
		System.out.println("Enter Password:");
		String pwd=encryptPassword(input.nextLine());
		
		Customer customer=customers.get(id);
		
		if(customer!=null && customer.getPassword().equals(pwd)) {
			accountMenu(customer);
		}else {
			System.out.println("Invalid Creadtional..");
		}
	}

	private void accountMenu(Customer customer) {
		while(true) {
			 System.out.println("1. Create Gift Card");
	         System.out.println("2. TopUp");
	         System.out.println("3. Transaction History");
	         System.out.println("4. Block");
	         System.out.println("5. Logout");
	         
	         System.out.println("Enter Your Choice:");
	         int choice=input.nextInt();
	         input.nextLine();
	         
	         switch(choice) {
	         case 1:{
	        	 createGiftCard(customer);
	        	 break;
	         }
	         case 2:{
	        	 topUp(customer);
	        	 break;
	         }
	         case 3:{
	        	 transactionHistroy(customer);
	        	 break;
	         }
	         case 4:{
	        	 blockGiftCard(customer);
	        	 break;
	         }
	         case 5:{
	        	 System.out.println("Logged out..");
	        	 System.exit(0);
	        	 break;
	         }
	         default:{
	        	 break;
	         }
	         }
		}
		
	}

	private void blockGiftCard(Customer customer) {
		System.out.println("Enter Giftcard Number:");
		String number=input.nextLine();
		
		GiftCard card=findGiftCard(customer,number);
		
		if(card!=null && !card.isBlock()) {
			double bal=customer.getBalance()+card.getBalance();
			customer.setBalance(bal);
			card.setBalance(0);
			card.setBlock(true);
			  card.getTransactions().add(new Transaction("Block", 0, new Date()));
	            System.out.println("Gift Card Blocked. Balance Transferred to Main Account.");
		}else {
			System.out.println("Invalid Gift Card or Card is Already Blocked.");
		}
	}

	private void transactionHistroy(Customer customer) {
		System.out.println("Enter GiftCard Number:");
		String number=input.nextLine();
		
		GiftCard card=findGiftCard(customer,number);
		
		if(card!=null) {
			for(Transaction t:card.getTransactions()) {
				System.out.println(t.getType()+"--"+t.getAmount()+"--"+t.getDate());
			}
		}else {
			System.out.println("Invalid Gift Card..");
		}
	}

	private void topUp(Customer customer) {
		System.out.println("Enter gift card Number:");
		String giftCardNumber=input.nextLine();
		
		GiftCard card=findGiftCard(customer,giftCardNumber);
		
		if(card!=null && !card.isBlock()) {
			System.out.println("Enter Amount:");
			double amount=input.nextDouble();
			input.nextLine();
			
			if(customer.getBalance() >= amount) {
				double bal=customer.getBalance()-amount;
				customer.setBalance(bal);
				double bal1=card.getBalance()+amount;
				card.setBalance(bal1);
				
				card.getTransactions().add(new Transaction("TopUp",amount,new Date()));
				 System.out.println("TopUp Successful. New Gift Card Balance: " + card.getBalance());
			}else {
				 System.out.println("Insufficient balance in main account.");
			}
		}else {
			 System.out.println("Invalid Gift Card or Card is Blocked.");
		}
		
	}

	private GiftCard findGiftCard(Customer customer, String giftCardNumber) {
		for(GiftCard card:customer.getGiftcards()) {
			if(card.getCardNumber().equals(giftCardNumber)) {
				return card;
			}
		}
		return null;
	}

	private void createGiftCard(Customer customer) {
		String cardNumber=genrateCardNumber();
		String pin=genratePin();
		
		GiftCard gf=new GiftCard(cardNumber,pin,0,false);
		customer.getGiftcards().add(gf);
		 System.out.println("Gift Card Created: Card Number: " + cardNumber + ", PIN: " + pin);
	}

	private String genratePin() {
		return String.format("%04d",random.nextInt(10000));
	}

	private String genrateCardNumber() {
		return String.format("%05d",random.nextInt(100000));
	}

	private String encryptPassword(String pwd) {
		StringBuilder encrypted=new StringBuilder();
		
		for(char c:pwd.toCharArray()) {
			if(Character.isUpperCase(c)) {
				encrypted.append((char)((c-'A'+1)%26+'A'));
			}else if(Character.isLowerCase(c)) {
				encrypted.append((char)((c-'a'+1)%26+'a'));
			}else if(Character.isDigit(c)) {
				encrypted.append((char)((c-'0'+1)%10+'0'));
			}else {
				encrypted.append(c);
			}
		}
		return 	encrypted.toString();
	}

}
