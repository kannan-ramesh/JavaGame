package com.kannanrameshrk.familytree.tree;

import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.familytree.dto.Person;

public class FamilyTreeView {
	private FamilyTreeViewModel familyTreeViewModel;
	Scanner input;
	
	public FamilyTreeView() {
		familyTreeViewModel=new FamilyTreeViewModel(this);
		input=new Scanner(System.in);
	}

	public void start() {
		
		while(true) {
			System.out.println("1.Register Members:");
			System.out.println("2.Matching Married Pair:");
			System.out.println("3.view family Members:");
			System.out.println("4.Exit");
			
			System.out.println("Enter Your Choice:");
			int choice=input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:{
				System.out.println("Enter Person Name:");
				String name=input.nextLine();
				System.out.println("Enter Person Gender:");
				String gender=input.nextLine();
				System.out.println("Enter Father Name:");
				String father=input.nextLine();
				System.out.println("Enter Mother Name:");
				String mother=input.nextLine();
				
				Person person=new Person(name,gender,father,mother);
				if(familyTreeViewModel.regPerson(person)) {
					message("Register Successfull..");
				}else {
					message("Duplicate name of person,enter name Uniquely..");
				}
				break;
			}
			case 2:{
				System.out.println("Enter Person Name:");
				String personName=input.nextLine();
				List<Person> list=familyTreeViewModel.findPair(personName);
				
				if(list.size()==0) {
					System.out.println("No Matching Married Pair...");
				}else {
					for(Person p:list) {
						System.out.print(p.getName()+",");
					}
					System.out.println();
				}
				
				break;
			}
			case 3:{
				List<Person> list=familyTreeViewModel.getData();
				message("Name   Gender   Father  Mother");
				message("*******************************");
				
				for(Person i:list) {
					System.out.println(i.toString());
				}
				message("*******************************");
				break;
			}
			case 4:{
				return;
			}
			default:{
				break;
			}
			}
		}
		
	}

	private void message(String onMessage) {
		System.out.println(onMessage);
	}
}
