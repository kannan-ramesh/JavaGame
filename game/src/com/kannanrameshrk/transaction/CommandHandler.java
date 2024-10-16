package com.kannanrameshrk.transaction;

import java.util.Scanner;

public class CommandHandler {
		Scanner input=new Scanner(System.in);
		TransactionalKeyValueStore kvStore=new TransactionalKeyValueStore();
		
	public void start() {
		while(true) {
			displayMenu();
			System.out.println("Enter Your Choice(1-8):");
			int choice=input.nextInt();
			input.nextLine();
			
			 switch(choice) {
			 case 1:{
				 handleSet();
				 break;
			 }
			 case 2:{
				 handleGet();
				 break;
			 }
			 case 3:{
				 handleUnset();
				 break;
			 }
			 case 4:{
				 handleCount();
				 break;
			 }
			 case 5:{
				 kvStore.begin();
				 break;
			 }
			 case 6:{
				 kvStore.commit();
				 System.out.println("Transaction commited..");
				 break;
			 }
			 case 7:{
				 kvStore.rollBack();
				 break;
			 }
			 case 8:{
				 System.out.println("Exiting..");
				 input.close();
				 return;
			 }
			 default:{
				 System.out.println("Invalid choice...");
				 break;
			 }
			 }
		}
		
	}

	private void handleCount() {
		System.out.println("Enter the value to count:");
		String value = input.nextLine();
		int count=kvStore.count(value);
		System.out.println("Count of keys with value "+value+":"+count);
		
	}

	private void handleUnset() {
		System.out.println("Enter key to unset:");
		String key=input.nextLine().trim();
		kvStore.unSet(key);
		
	}

	private void handleGet() {
		System.out.println("Enter Key:");
		String key=input.nextLine();
		String value=kvStore.get(key);
		System.out.println("Value->"+value);
	}

	private void handleSet() {
		System.out.println("Enter Key:");
		String key=input.nextLine().trim();
		System.out.println("Enter Value:");
		String value=input.nextLine().trim();
		kvStore.set(key,value);
		System.out.println("Key-value pair set..");
		
	}

	private void displayMenu() {
		 	System.out.println("Choose an option:");
	        System.out.println("1. SET a key-value pair");
	        System.out.println("2. GET the value of a key");
	        System.out.println("3. UNSET a key");
	        System.out.println("4. COUNT keys with a specific value");
	        System.out.println("5. BEGIN a new transaction");
	        System.out.println("6. COMMIT the current transaction");
	        System.out.println("7. ROLLBACK the last transaction");
	        System.out.println("8. EXIT the program");
		
	}

}
