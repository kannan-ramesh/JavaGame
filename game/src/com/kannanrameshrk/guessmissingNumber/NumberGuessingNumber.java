package com.kannanrameshrk.guessmissingNumber;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingNumber {

	public static void main(String[] args) {
		Scanner input= new Scanner(System.in);
		Random random=new Random();
		
		int min=1,max=100;
		int numberToGuss=random.nextInt(max-min)+min;
		int numberOfTries=0;
		boolean hasGuessedCorrectly = false;
		
		System.out.println("Welcome To The Number Guessing Game!..");
		System.out.println("I have choose a number between"+min+" max"+max+".");
		System.out.println("Try to Guss it..");
		
		while(!hasGuessedCorrectly) {
			System.out.println("Enter your Guess:");
			int num=input.nextInt();
			numberOfTries++;
			
			if(num<numberToGuss) {
				System.out.println("--------------------");
				System.out.println("Two low Try again..");
				System.out.println("--------------------");
			}else if(num > numberToGuss) {
				System.out.println("--------------------");
				System.out.println("Two hight Try again..");
				System.out.println("--------------------");
			}else {
				hasGuessedCorrectly=true;
				System.out.println("congratulations! you ve guessed correctly..");
				System.out.println("yow number "+numberOfTries+" Tries.");
			}
		}
		input.close();
	}

}
