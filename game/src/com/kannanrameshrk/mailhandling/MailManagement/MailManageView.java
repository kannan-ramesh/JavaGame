package com.kannanrameshrk.mailhandling.MailManagement;

import java.util.Scanner;

public class MailManageView {
	public MailManageViewModel mailManageViewModel;
	
	public MailManageView() {
		mailManageViewModel=new MailManageViewModel(this);
	}

	public void start() {
		Scanner input=new Scanner(System.in);
		
		while(true) {
			System.out.println("\t\t Mail System");
			System.out.println("\t\t***************");
			
			System.out.println(" 1.Create User");
			System.out.println(" 2.Create Group");
			System.out.println(" 3.Group Assignment");
			System.out.println(" 4.Compose Mail");
			System.out.println(" 5.View Inbox");
			System.out.println(" 6.View Sent Mail");
			System.out.println(" 7.Delete Mail");
			System.out.println(" 8.Recall");
			System.out.println(" 9.Share Inbox");
			System.out.println(" 10.Exit");
			
			System.out.println("Enter Your Choice:");
			System.out.println("-------------------");
			int choice=input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:{
				System.out.println("\t\tCreate User");
				System.out.println("\t\t------------");
				
				System.out.println("Enter User Name:");
				String userName=input.nextLine();
				System.out.println("Enter your Email:");
				String mail=input.next();
				System.out.println("Enter Your Password:");
				String password=input.next();
				mailManageViewModel.createUser(userName,mail,password);
				break;
			}
			case 2:{
				System.out.println("\t\tCreate Group");
				System.out.println("\t\t------------");
				
				System.out.println("Enter Group Name:");
				String groupName=input.nextLine();
				System.out.println("ENter GroupEmail:");
				String groupEmail=input.nextLine();
				System.out.println("Enter Description:");
				String description=input.nextLine();
				mailManageViewModel.createGroup(groupName,groupEmail,description);
				break;
			}
			case 3:{
				System.out.println("Enter User mail:");
				String userMail=input.nextLine();
				System.out.println("Enter GroupName:");
				String assignGroup=input.nextLine();
				mailManageViewModel.addUserGroup(userMail,assignGroup);
				break;
			}
			case 4:{
				System.out.println("\t\tCompose Mail");
				System.out.println("\t\t------------");
				
				System.out.println("Enter your mail(From):");
				String fromMail=input.nextLine();
				System.out.println("Enter To Mail:");
				String toMail=input.nextLine();
				System.out.println("Enter Mail Subject:");
				String mailSubject=input.nextLine();
				System.out.println("Enter Mail Content:");
				String content=input.nextLine();
				
				mailManageViewModel.composeMail(fromMail,toMail,mailSubject,content);
				break;
			}
			case 5:{
				System.out.println("\t\t Mail InBox");
				System.out.println("\t\t------------");
				mailManageViewModel.viewInBox();
				break;
			}
			case 6:{
				System.out.println("\t\t Sent Mail InBox");
				System.out.println("\t\t------------");
				mailManageViewModel.viewSentMail();
				break;
			}
			case 7:{
				System.out.println("\t\t Delete Mail");
				System.out.println("\t\t------------");
				System.out.println("Enter mail ID to delete:");
				int mailId=input.nextInt();
				mailManageViewModel.DeleteMail(mailId);
				break;
			}
			case 8:{
				System.out.println("\t\t Mail Recall");
				System.out.println("\t\t------------");
				
				System.out.println("Enter Mail ID to Recall: ");
				int mailIdToRecall=input.nextInt();
				mailManageViewModel.recallMail(mailIdToRecall);
				break;
			}
			case 9:{
				System.out.println("Owner Email:");
				String ownerMail=input.nextLine();
				System.out.println("Share with Email:");
				String shareWithEmail=input.nextLine();
				mailManageViewModel.shareInbox(ownerMail,shareWithEmail);
				break;
			}
			case 10:{
				System.out.println("Exit Application...");
				return;
			}
			default:{
				System.out.println("Invalid Choice Try Again..");
				break;
			}
			}
		}
		
	}
}
