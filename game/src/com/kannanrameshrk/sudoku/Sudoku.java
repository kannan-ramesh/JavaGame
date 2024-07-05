package com.kannanrameshrk.sudoku;

import java.util.Random;
import java.util.Scanner;

public class Sudoku {
	private static final int SIZE=9;
	private static int[][] board=new int[SIZE][SIZE];
	private static Random random=new Random();
	
	public static void main(String[] args) {
		System.out.println("\t\t SUDOKU");
		System.out.println("\t\t********");
		
		board = new int[][] {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        printBoard();
        
        if(solveSudoku()) {
        	System.out.println("Congratulations you won..");
        	printBoard();
        }else {
        	System.out.println("play again");
        }
	}

	private static boolean solveSudoku() {
		for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
            	
                if (board[row][col] == 0) {
                	
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidMove(row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
		
	}

	private static boolean isSolved() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(board[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValidMove(int row, int col, int number) {
		if(board[row][col]!=0) {
			return false;
		}
		
		for(int i=0;i<SIZE;i++) {
			if(board[row][i]==number || board[i][col]==number ) {
				return false;
			}
		}
		
		int boxRowStart=(row/3)*3;
		int boxColStart=(col/3)*3;
		
		for(int i=boxRowStart;i<boxRowStart+3;i++) {
			for(int j=boxColStart;j<boxColStart+3;j++) {
				if(board[i][j]==number) {
					return false;
				}
			}
		}
		return true;
	}

	private static void printBoard() {
		for(int i=0;i<SIZE;i++) {
			if(i%3==0 && i!=0) {
				System.out.println("-------------------");
			}
			for(int j=0;j<SIZE;j++){
				if(j%3==0 && j!=0) {
					System.out.print("|");
				}
				System.out.print(board[i][j]==0 ?". ":board[i][j]+" ");
			}
			System.out.println();
		}
	}

}
