package com.kannanrameshrk.trainticketbooking;

import java.util.Scanner;

import com.kannanrameshrk.trainticketbooking.viewmodel.BookingView;

public class Main {

	public static void main(String[] args) {
		System.out.println("\t\tTrain Ticket Booking");
		System.out.println("\t\t----------------------");
		Scanner input=new Scanner(System.in);
		BookingView bookingView=new BookingView();
		
		while(true) {
			System.out.println(" 1.Ticket Booking\n 2.Cancel Ticket\n 3.Print Chart\n 4.Exit");
			System.out.println("Enter your choice:");
			int choice=input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:{
				bookingView.ticketBooking(input);
				break;
			}
			case 2:{
				bookingView.cancelTicket(input);
				break;
			}
			case 3:{
				bookingView.printChart();
				break;
			}
			case 4:{
				System.out.println("Exit application..");
				return;
			}
			default:{
				System.out.println("Invalid Choice..try again");
				break;
			}
			}
		}
	}
}