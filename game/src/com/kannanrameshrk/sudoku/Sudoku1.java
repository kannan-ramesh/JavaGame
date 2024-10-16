package com.kannanrameshrk.sudoku;

public class Sudoku1 {
	private static final int Size=9;
	private static int[][] board=new int[Size][Size];
	
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
        if(validSudokku()) {
        	System.out.println("Congratulations You Won");
        	printBoard();
        }else {
        	System.out.println("play gain..");
        	printBoard();
        }

	}

	private static boolean validSudokku() {
		for(int i=0;i<Size;i++) {
			for(int j=0;j<Size;j++) {
				if(board[i][j]==0) {
					for(int num=1;num<=Size;num++) {
						if(validMove(i,j,num)) {
							board[i][j]=num;
							
							if(validSudokku()) {
								return true;
							}else {
								board[i][j]=0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean validMove(int row, int col, int num) {
		if(board[row][col]!=0) {
			return false;
		}
		
		for(int i=0;i<Size;i++) {
			if(board[row][i]==num || board[i][col]==num) {
				return false;
			}
		}
		
		int startRow=(row/3)*3;
		int startCol=(col/3)*3;
		
		for(int i=startRow;i<startRow+3;i++) {
			for(int j=startCol;j<startCol+3;j++) {
				if(board[i][j]==num) {
					return false;
				}
			}
		}
		return true;
	}

	private static void printBoard() {
		for(int i=0;i<Size;i++) {
			if(i%3==0 && i!=0) {
				System.out.println("--------------------");
			}
			for(int j=0;j<Size;j++) {
				if(j%3==0 &&j!=0) {
					System.out.print("|");
				}
				System.out.print(board[i][j]==0?". ":board[i][j]+" ");
			}
			System.out.println();
		}	
	}
}