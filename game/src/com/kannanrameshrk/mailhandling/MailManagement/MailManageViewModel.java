package com.kannanrameshrk.mailhandling.MailManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kannanrameshrk.mailhandling.dto.Group;
import com.kannanrameshrk.mailhandling.dto.Mail;
import com.kannanrameshrk.mailhandling.dto.User;

class MailManageViewModel {
	public MailManageView mailManageView;
	private List<User> users=new ArrayList<>();
	private List<Group> groups=new ArrayList<>();
	private List<Mail> sentMails=new ArrayList<>();
	private List<Mail> inbox=new ArrayList<>();
	private Map<User, Set<User>> sharedInboxes = new HashMap<>();
	
	public MailManageViewModel(MailManageView mailManageView) {
		this.mailManageView=mailManageView;
	}

	public void createUser(String userName, String mail, String password) {
		if(isValidMail(mail) && !isEmailTaken(mail)) {
			users.add(new User(userName,mail,password));
			System.out.println("User Created..");
		}else {
			System.out.println("User Not created please enter correct mail..");
		}
		
	}

	private boolean isEmailTaken(String mail) {
		return users.stream().anyMatch(user-> user.getEmail().equals(mail)) || groups.stream().anyMatch(group -> group.getEmail().equals(mail));
	}

	private boolean isValidMail(String mail) {
		return mail.matches("^[A-Za-z0-9_.+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,6}$");
	}

	public void createGroup(String groupName, String groupEmail, String description) {
		if(isValidMail(groupEmail)) {
			groups.add(new Group(groupName,groupEmail,description));
			System.out.println("Group is Created..");
		}else {
			System.out.println("Invalid group Email...");
		}
	}

	public void addUserGroup(String userMail, String assignGroup) {
		User user=getUserByEmail(userMail);
		Group group=getGroupByName(assignGroup);
		
		if(user!=null && group!=null) {
			group.addMember(user);
			System.out.println("User Added Group..");
		}else {
			System.out.println("User group not found..");
		}
	}

	private Group getGroupByName(String assignGroup) {
		return groups.stream().filter(group-> group.getGroupName().equals(assignGroup)).findFirst().orElse(null);
	}

	private User getUserByEmail(String userMail) {
		return users.stream().filter(user->user.getEmail().equals(userMail)).findFirst().orElse(null);
	}

	public void composeMail(String fromMail, String toMail, String mailSubject, String content) {
		if(isValidRecipient(toMail)) {
			Mail mail=new Mail(fromMail,toMail,mailSubject,content);
			sentMails.add(mail);
			inbox.add(mail);
			System.out.println("Mail Sent...");
		}else {
			System.out.println("Invalid Recipient..");
		}
	}

	private boolean isValidRecipient(String toMail) {
		return users.stream().anyMatch(user->user.getEmail().equals(toMail)) || groups.stream().anyMatch(group->group.getEmail().equals(toMail));
	}

	public void viewInBox() {
		inbox.stream().filter(mail-> !mail.isRecalled())
			.sorted(Comparator.comparing(Mail::getId).reversed())
			.forEach(mail->System.out.println("From: " + mail.getFrom()+", Subject: "+mail.getSubject()+", Content: "+mail.getContent()));
	}

	public void viewSentMail() {
		sentMails.stream().sorted(Comparator.comparing(Mail::getId).reversed())
		.forEach(mail->{
			String status=mail.isRecalled() ?" (Recalled)" :"";
			System.out.println("To: "+mail.getTo()+", Subject: "+mail.getSubject()+ status);
		});
		
	}

	public void DeleteMail(int mailId) {
		inbox.removeIf(mail->mail.getId()==mailId);
		sentMails.removeIf(mail-> mail.getId()==mailId);
		System.out.println("Mail Deleted..");
	}

	public void recallMail(int mailIdToRecall) {
		for(Mail mail:sentMails) {
			if(mail.getId()== mailIdToRecall) {
				mail.recall();
				inbox.remove(mail);
				System.out.println("Mail Recalled");
				return;
			}
		}
		System.out.println("Mail Not found in sent items..");
	}

	public void shareInbox(String ownerMail, String shareWithEmail) {
		User owner=getUserByEmail(ownerMail);
		User sharedWith=getUserByEmail(shareWithEmail);
		
		if(owner !=null && sharedWith!=null) {
			sharedInboxes.computeIfAbsent(owner, k-> new HashSet<>()).add(sharedWith);
			System.out.println("Inbox Shared with "+shareWithEmail);
		}else {
			System.out.println("Invalid user email");
		}
	}

}
