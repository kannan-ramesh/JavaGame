package com.kannanrameshrk.trainticketbooking.viewmodel;

import java.util.Scanner;

import com.kannanrameshrk.trainticketbooking.dto.Passanger;

public class BookingView {
	private  BookingViewModel bookingViewModel;
	
	public BookingView() {
		bookingViewModel=new BookingViewModel(this);
	}

	public void ticketBooking(Scanner input) {
		System.out.println("Enter Ticket Count:");
		int ticketCount=input.nextInt();
		System.out.println("Enter Starting Station:(A,B,C,D)");
		char startStation=input.next().charAt(0);
		System.out.println("Enter Ending Station(B,C,D,E)");
		char endStation=input.next().charAt(0);
		
		Passanger passanger=new Passanger(ticketCount,startStation,endStation);
		bookingViewModel.bookTicket(passanger);
	}

	public void printChart() {
		char[][] arr=bookingViewModel.getChart();
	
		System.out.println("  A  B  C  D  E");
		for(int i=0;i<arr.length;i++) {
			System.out.print((i+1)+" ");
			for(int j=0;j<arr[0].length;j++) {
				System.out.print(arr[i][j]+"  ");
			}
			System.out.println();
		}
	}

	public void cancelTicket(Scanner input) {
		System.out.println("Enter PNR Number:");
		int pnr=input.nextInt();
		System.out.println("Enter no of Tickets Cancel:");
		int noOfTickets=input.nextInt();
		
		if( bookingViewModel.checkPNR(pnr)) {
			bookingViewModel.ticketCancel(pnr,noOfTickets);
			System.out.println("Successfully canceled your seat..");
		}else {
			System.out.println("Your pnr is not available..");
		}	
	}	
}