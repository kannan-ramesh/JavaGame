package com.kannanrameshrk.trainticketbooking.dto;

import java.util.ArrayList;
import java.util.List;

public class Passanger {
	private int pnr;
	private  int ticketCount;
	private char startPoint;
	private char endPoint;
	private boolean isConfirm;
	private List<Integer> seatNo;
	private static int pnrCounter = 0;
	
	public Passanger(int ticketCount, char startPoint, char endPoint) {
		this.pnr=++pnrCounter;
		this.ticketCount=ticketCount;
		this.startPoint=startPoint;
		this.endPoint=endPoint;
		this.isConfirm=false;
		this.setSeatNo(new ArrayList<>());	
	}
	
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public char getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(char startPoint) {
		this.startPoint = startPoint;
	}
	public char getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(char endPoint) {
		this.endPoint = endPoint;
	}
	public boolean isConfirm() {
		return isConfirm;
	}
	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}
	public List<Integer> getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(List<Integer> seatNo) {
		this.seatNo = seatNo;
	}

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}
}