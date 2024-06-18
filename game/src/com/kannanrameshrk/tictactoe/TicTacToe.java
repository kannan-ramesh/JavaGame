package com.kannanrameshrk.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	private static char board[][];
	private static char currentPlayer;
	private static final int SIZE=3;
	private static Scanner input;
	
	public static void main(String[] args) {
		board=new char[SIZE][SIZE];
		input=new Scanner(System.in);
		currentPlayer='X';
		
		initializeBoard();
		printBoard();
		
		while(true) {
			if(currentPlayer=='X') {
				playerMove();
			}else {
				computerMove();
			}
			
			printBoard();
			
			if(checkWon()) {
				System.out.println("player "+currentPlayer+" is won");
				break;
			}
			
			if(boardIsFull()) {
				System.out.println("The game is Draw..");
				break;
			}
			switchPlayer();
		}
		input.close();
	}

	private static void computerMove() {
		int[] move=findBestMove();
		int row=move[0];
		int col=move[1];
		
		board[row][col]=currentPlayer;
		System.out.println("Computer plays at (" + row + ", " + col + ")");
	}

	private static int[] findBestMove() {
		 // Check if the computer can win
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'O'; // Simulate move
                    if (checkWonCondition('O')) {
                        board[i][j] = '-';
                        return new int[]{i, j}; // This move wins the game
                    }
                    board[i][j] = '-'; // Undo move
                }
            }
        }
        
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(board[i][j]=='-') {
					board[i][j]='X';
					if(checkWonCondition('X')) {
						board[i][j]='-';
						return new int[] {i,j};
					}
					board[i][j]='-';
				}
			}
		}
		
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(board[i][j]=='-') {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {-1,-1};
	}

	private static boolean checkWonCondition(char player) {
		return checkRows(player) || checkCol(player) || checkDiagonals(player) ;
	}

	private static void switchPlayer() {
		if(currentPlayer=='X') {
			currentPlayer='O';
		}else {
			currentPlayer='X';
		}
		
	}

	private static boolean boardIsFull() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(board[i][j]=='-') {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkWon() {
		return checkWonCondition(currentPlayer);
	}

	private static boolean checkDiagonals(char player) {
		return (board[0][0]==player && board[1][1]==player && board[2][2]==player) || (board[0][2]==player && board[1][1]==player && board[2][0]==player);
	}

	private static boolean checkCol(char player) {
		for(int i=0;i<SIZE;i++) {
			if(board[0][i]==player && board[1][i]==player && board[2][i]==player) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkRows(char player) {
		for(int i=0;i<SIZE;i++) {
			if(board[i][0]==player && board[i][1]==player && board[i][2]==player) {
				return true;
			}
		}
		return false;
	}

	private static void playerMove() {
		int row=-1,col=-1;
		
		while(true) {
			System.out.println("Player "+currentPlayer+ " enter your move:");
			row=input.nextInt();
			col=input.nextInt();
			
			if(row >=0 && row<SIZE && col>=0 && col<SIZE && board[row][col]=='-') {
				board[row][col]=currentPlayer;
				break;
			}else {
				System.out.println("This move is not valid..");
			}
		}
	}

	private static void printBoard() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
	}

	private static void initializeBoard() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				board[i][j]='-';
			}
		}
	}

}
