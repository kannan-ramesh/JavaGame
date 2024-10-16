package com.kannanrameshrk.mailhandling.dto;

public class Mail {
	private static int counter=0;
	private int id;
	private String from;
	private String to;
	private String subject;
	private String content;
	private boolean recalled;
	
	
	public Mail(String from, String to, String subject, String content) {
		this.id=++counter;
		this.from=from;
		this.to=to;
		this.subject=subject;
		this.content=content;
		this.recalled=false;
	}
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Mail.counter = counter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isRecalled() {
		return recalled;
	}
	public void setRecalled(boolean recalled) {
		this.recalled = recalled;
	}
	public void recall() {
		this.recalled=true;
		
	}
	
	
}
