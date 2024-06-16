package com.kannanrameshrk.connectfour;

import java.util.Scanner;

public class ConnectFour {
	
	
	public static void main(String[] args) {
		
		Scanner input=new Scanner(System.in);
		char[][] board=new char[6][7];
		
		fillArray(board);
		int turn=1;
		boolean winner=false;
		char player='R';
		
		while(winner == false && turn<=42) {
			boolean validPlay = false;
			int play;
			do {
				display(board);
				System.out.println("player "+player+" choose a column(0-6)");
				play=input.nextInt();
				validPlay=validate(play,board);
				
			}while(validPlay == false);
			
			//drop the tocken board
			
			for(int i=board.length-1;i>=0;i--) {
				if(board[i][play]=='-') {
					board[i][play]=player;
					break;
				}
			}
			
			//detarmin winner
			
			winner=isWinner(player,board);
			
			//switch player
			if(player=='R') {
				player='Y';
			}else {
				player='R';
			}
			turn++;
		}
		display(board);
		
		if(winner) {
			if(player=='R') {
				System.out.println("Player Yellow is Winner..");
			}else {
				System.out.println("Player Red is Winner..");
			}
		}else {
			System.out.println("Tie game..");
		}
		
	}

	private static boolean isWinner(char player, char[][] board) {
		//check for four across
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length-3;j++) {
				if(board[i][j]==player && board[i][j+1] == player && board[i][j+2]==player && board[i][j+3]==player) {
					return true;
				}
			}
		}
		
		//check four up and down
		
		for(int i=0;i<board.length-3;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j]==player && board[i+1][j]==player && board[i+2][j]==player && board[i+3][j]==player) {
					return true;
				}
			}
		}
		
		//check upward diagnol
		
		for(int i=3;i<board.length;i++) {
			for(int j=0;j<board[0].length-3;j++) {
				if(board[i][j]==player && board[i-1][j+1]==player && board[i-2][j+2]==player && board[i-3][j+3]==player) {
					return true;
				}
			}
		}
		
		//check downward diagonal
		
		for(int i=0;i<board.length-3;i++) {
			for(int j=0;j<board[0].length-3;j++) {
				if(board[i][j] == player && board[i+1][j+1]==player && board[i+2][j+2]==player && board[i+3][j+3]==player) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean validate(int n, char[][] board) {
		if(n<0 && n>board[0].length) {
			return false;
		}
		
		if(board[0][n]!='-') {
			return false;
		}
		return true;
	}

	private static void display(char[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
	}

	private static void fillArray(char[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j]='-';
			}
		}
		
	}

	
}
