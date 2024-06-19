package com.kannanrameshrk.hangman;

import java.util.Arrays;
import java.util.Scanner;

public class HangMen {
	
	private String wordToGuess;
	private final int NoOfAttampt=6;
	private char[] guessWord;
	private int attemptsLeft;
	
	public HangMen(String word) {
		wordToGuess=word;
		guessWord=new char[word.length()];
		Arrays.fill(guessWord,'-');
		attemptsLeft=NoOfAttampt;
	}
	
	public static void main(String[] args) {
		HangMen hangmen=new HangMen("hangmen");
		hangmen.play();

	}

	private void play() {
		Scanner input=new Scanner(System.in);
		
		while(attemptsLeft >0) {
			System.out.println("Guessed so far:"+ new String(guessWord));
			System.out.println("Attempts Left:"+attemptsLeft);
			System.out.println("Enter your Letter:");
			char guess=input.next().charAt(0);
			boolean correct =false;
			
			for(int i=0;i<wordToGuess.length();i++) {
				if(wordToGuess.charAt(i)==guess) {
					guessWord[i]=guess;
					correct=true;
				}
			}
			
			if(!correct) {
				attemptsLeft--;
			}
			
			if(Arrays.equals(guessWord, wordToGuess.toCharArray())) {
				 System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
	           return;
			}
		}
		 System.out.println("Sorry, you've run out of attempts. The word was: " + wordToGuess);
	}

}
