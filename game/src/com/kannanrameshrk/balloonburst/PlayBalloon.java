package com.kannanrameshrk.balloonburst;

import java.util.Arrays;
import java.util.Scanner;

public class PlayBalloon {
	static Scanner input=new Scanner(System.in);
	char[] color= {'R','B'};
	boolean flag=true;
	
	public void start() {
		System.out.println("Ballon Burst");
		System.out.println("--------------");
		
		System.out.println("Enter the Matrix Size:(m*n)");
		int m=input.nextInt();
		int n=input.nextInt();
		
		char[][] board=new char[m][n];
		
		fillBoard(board);
		viewBoard(board);
		
		while(flag) {
			System.out.println("Enter the column Number:");
			int col=input.nextInt();
			System.out.println("Enter The Color Of Balloon:");
			System.out.println("Available Color: "+Arrays.toString(color));
			char color=input.next().charAt(0);
			
			fillBalloon(board,col,color);
			viewBoard(board);
			burstBallon(board);
			dropBallons(board);
			
			System.out.println("Do you wish to continue(Y/N):");
			char choice=input.next().charAt(0);
			if(choice=='N') {
				System.out.println("Program Stopped..");
				return;
			}
		}
	}
	private void dropBallons(char[][] board) {
		for(int j=0;j<board[0].length;j++) {
			for(int i=board.length-1;i>0;i--) {
				if(board[i][j]=='-') {
					for(int k=i-1;k>=0;k--) {
						if(board[k][j]!='-') {
							board[i][j]=board[k][j];
							board[k][j]='-';
							break;
						}
					}
				}
			}
		}
	}
	private void burstBallon(char[][] board) {
		 // Check T shape first
        for (int i = board.length - 1; i >= 2; i--) {
            for (int j = 1; j <= board[0].length - 2; j++) {
                if (board[i][j] == 'R' && board[i - 1][j] == 'R' && board[i - 2][j] == 'R' &&
                    board[i - 1][j - 1] == 'R' && board[i - 1][j + 1] == 'R') {
                    board[i][j] = '-';
                    board[i - 1][j] = '-';
                    board[i - 2][j] = '-';
                    board[i - 1][j - 1] = '-';
                    board[i - 1][j + 1] = '-';
                  
                }
                if (board[i][j] == 'B' && board[i - 1][j] == 'B' && board[i - 2][j] == 'B' &&
                    board[i - 1][j - 1] == 'B' && board[i - 1][j + 1] == 'B') {
                    board[i][j] = '-';
                    board[i - 1][j] = '-';
                    board[i - 2][j] = '-';
                    board[i - 1][j - 1] = '-';
                    board[i - 1][j + 1] = '-';
     
                }
            }
        }
		//check horizontal
		for(int i=board.length-1;i>=3;i--) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j]=='R' && board[i-1][j]=='R' && board[i-2][j]=='R') {
					board[i][j]='-';
					board[i-1][j]='-';
					board[i-2][j]='-';
					System.out.println("burst Balloon");
					viewBoard(board);
				}
				if(board[i][j]=='B' && board[i-1][j]=='B' && board[i-2][j]=='B') {
					board[i][j]='-';
					board[i-1][j]='-';
					board[i-2][j]='-';
					System.out.println("burst Balloon");
					viewBoard(board);
				}
			}
		}
		
		//check Vertical
		
		for(int i=board.length-1;i>=0;i--) {
			for(int j=0;j<=board[0].length-3;j++) {
				if(board[i][j]=='R' && board[i][j+1]=='R' && board[i][j+2]=='R') {
					board[i][j]='-';
					board[i][j+1]='-';
					board[i][j+2]='-';
					System.out.println("burst Balloon");
					viewBoard(board);
				}
				if(board[i][j]=='B' && board[i][j+1]=='B' && board[i][j+2]=='B') {
					board[i][j]='-';
					board[i][j+1]='-';
					board[i][j+2]='-';
					System.out.println("burst Balloon");
					viewBoard(board);
				}
			}
		}
	}
	private void fillBalloon(char[][] board, int col, char color) {
		if(col<0 || board[0].length<col) {
			System.out.println("Invalid column Number Try Again..");
			return;
		}
		if(board[0][col]!='-') {
			System.out.println(" Column is filled completely. Program is terminated.");
			System.exit(0);
			return;
		}
		
		for(int i=board.length-1;i>=0;i--) {
			if(board[i][col]=='-') {
				board[i][col]=color;
				break;
			}
		}
	}
	private void fillBoard(char[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j]='-';
			}
		}
	}
	private void viewBoard(char[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
	}
}