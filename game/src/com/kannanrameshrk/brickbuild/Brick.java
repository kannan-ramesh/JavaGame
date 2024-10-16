package com.kannanrameshrk.brickbuild;

import java.util.Arrays;
import java.util.Scanner;

public class Brick {
	public static final Scanner input=new Scanner(System.in);
	public static boolean loop=true;
	
	public void start() {
		System.out.println("Enter Board Size:");
		System.out.println("Row:");
		int n=input.nextInt();
		System.out.println("Enter Col:");
		int m=input.nextInt();
		
		char[][] board=new char[n][m];
		fillBoard(board,n);
		printBoard(board,n,m);
		
		gameStart(board,n,m);
	}

	private void gameStart(char[][] board, int n, int m) {
		 char[][] shape1= {{'#','#'},{'#','#'}};
		 char[][] shape2= {{'#'},{'#'},{'#'},{'#'}};
		 char[][] shape3= {{'#',' '},{'#',' '},{'#','#'}};
		 char[][] shape4= {{'#','#',' '},{' ','#','#'}};
		 char[][] shape5= {{' ','#',' '},{'#','#','#'}};
		
		
		printShape(shape1);
		printShape(shape2);
		printShape(shape3);
		printShape(shape4);
		printShape(shape5);
		
		while(loop) {
			int score=0;
			
			System.out.println("Enter your shape:(1,2,3,4)");
			int shape=input.nextInt();
			System.out.println("Enter no Of Rotations");
			int rotate=input.nextInt();
			System.out.println("Enter shape insert column(0-"+(m-1)+")");
			int startColumn=input.nextInt();
			
			switch(shape) {
			case 1:{
				printShape(shape1);
				score+=insertBoard(shape1,n,m,board,startColumn);
				printBoard(board,n,m);
				break;
			}
			case 2:{
				if(rotate==1 || rotate==3) {
					char[][] shapes2=rotateShape(shape2,rotate);
					score+=insertBoard(shapes2,n,m,board,startColumn);
					printShape(shapes2);
					printBoard(board,n,m);
				}else {
					score+=insertBoard(shape2,n,m,board,startColumn);
					printShape(shape2);
					printBoard(board,n,m);
				}
				break;
			}
			case 3:{
				char[][] shapes3=rotateShape(shape3,rotate);
				score+=insertBoard(shapes3,n,m,board,startColumn);
				printShape(shapes3);
				printBoard(board,n,m);
				break;
			}
			case 4:{
				char[][] shapes4=rotateShape(shape4,rotate);
				score+=insertBoard(shapes4,n,m,board,startColumn);
				printShape(shapes4);
				printBoard(board,n,m);
				break;
			}
			case 5:{
				char[][] shapes5=rotateShape(shape5,rotate);
				score+=insertBoard(shapes5,n,m,board,startColumn);
				printShape(shapes5);
				printBoard(board,n,m);
				break;
			}
			default:{
				System.out.println("Invalid shape..");
				break;
			}
			}
		}
	}

	private char[][] rotateShape(char[][] shape, int rotate) {
		
		char[][] res=shape;
		
		while(rotate>0) {
			res=rotate90deg(res);
			rotate--;
		}
		
		return res;
	}

	private char[][] rotate90deg(char[][] shape) {
		int n=shape.length;
		int m=shape[0].length;
		
		char[][] arr=new char[m][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[j][i]=shape[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			reverse(arr[i]);
		}
		return arr;
	}

	private static void reverse(char[] cs) {
		int i = 0;
		int j = cs.length - 1;

		while (i < j) {
			char temp = cs[i];
			cs[i] = cs[j];
			cs[j] = temp;
			i++;
			j--;
		}
	}


	private int insertBoard(char[][] shape, int n, int m, char[][] board, int startColumn) {
		int startRow=findDropRow(shape,n,m,board,startColumn);
		
		for(int i=0;i<shape.length;i++) {
			for(int j=0;j<shape[i].length;j++) {
				if(shape[i][j]=='#') {
					board[startRow+i][startColumn+j]=shape[i][j];
				}
			}
		}
		return clearFilledRows(board);
	}

	private int clearFilledRows(char[][] board) {
		int consecutiveRowsCleared=0;
		int score=0;
		
		for(int i=board.length-1;i>=0;i--) {
			boolean filled=true;
			for(int j=0;j<board[i].length;j++) {
				if(board[i][j]!='#') {
					filled=false;
					break;
				}
			}
			if(filled) {
				clearRow(board,i);
				consecutiveRowsCleared++;
				i++;
			}
		}
		if(consecutiveRowsCleared>0) {
			score=consecutiveRowsCleared*10;
			if(consecutiveRowsCleared>1) {
				score+=(consecutiveRowsCleared-1)*20;
			}
			System.out.println("Score:"+score);
		}
		return score;
	}

	private void clearRow(char[][] arr, int row) {
		for (int i = row; i > 0; i--) {
		    for (int j = 0; j < arr[0].length; j++) {
		        arr[i][j] = arr[i - 1][j];  // Copy each element from arr[i-1] to arr[i]
		    }
		}
		
		for(int j=0;j<arr[0].length;j++) {
			arr[0][j]='-';
		}
		
	}

	private int findDropRow(char[][] shape, int n, int m, char[][] board, int startColumn) {
		for(int i=board.length-shape.length;i>=0;i--) {
			boolean flag=true;
			
			for(int j=0;j<shape.length;j++) {
				for(int k=0;k<shape[j].length;k++) {
					if(shape[j][k]=='#' && board[i+j][startColumn+k]=='#') {
						flag=false;
						loop=false;
						break;
					}
				}
				if(!flag) {
					break;
				}
			}
			if(flag) {
				return i;
			}
		}
		return 0;
	}

	private void printShape(char[][] shape) {
		for(int i=0;i<shape.length;i++) {
			for(int j=0;j<shape[0].length;j++) {
				System.out.print(shape[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private void printBoard(char[][] board, int n, int m) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
		
	}

	private void fillBoard(char[][] board, int n) {
		for(int i=0;i<n;i++) {
			Arrays.fill(board[i],'-');
		}
		
	}

}
