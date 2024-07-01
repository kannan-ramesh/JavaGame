package com.kannanrameshrk.snake;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.kannanrameshrk.snake.dto.Node;

public class SnakeBoard {
	char[][] board=null;
	Queue<Node> foodPositions=new LinkedList<>();
	Queue<Node> snake=new LinkedList<>();
	Scanner input=new Scanner(System.in);
	
	public SnakeBoard(int row, int col) {
		board=new char[row][col];
		fillArray(board);
		snake.add(new Node(0,0));
		foodPositions.add(new Node(1,0));
		foodPositions.add(new Node(2,2));
		foodPositions.add(new Node(3,4));
		foodPositions.add(new Node(5,2));
		foodPositions.add(new Node(4,5));
		displayFood(foodPositions.poll());
	}

	private void fillArray(char[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j]='-';
			}
			System.out.println();
		}
		
	}

	private void displayFood(Node poll) {
		int r=poll.getRow();
		int c=poll.getCol();
		
		board[r][c]='X';
	}

	public void startGame() {
		int r=0,c=0;
		
		board[r][c]='.';
		
		while(true) {
			printBoard();
			System.out.println("Enter snake Move:(L,R,U,D)");
			char pos=input.next().charAt(0);
			
			if(pos=='U') {
				snakeMove(--r,c);
			}
			if(pos=='D') {
				snakeMove(++r,c);
			}
			if(pos=='L') {
				snakeMove(r,--c);
			}
			if(pos=='R') {
				snakeMove(r,++c);
			}
			
		}
		
	}

	private void snakeMove(int r, int c) {
		if(r>=0 && r<board.length && c<board[0].length && c>=0 ) {
			snake.add(new Node(r,c));
			
			if(board[r][c]!='X') {
				Node node=snake.poll();
				
				int row=node.getRow();
				int col=node.getCol();
				
				board[row][col]='-';
			}
			
			if(board[r][c]=='X') {
				if(foodPositions.isEmpty()) {
					moveForwardPrint(r,c);
					System.out.println("Game is Over..");
					System.exit(0);
				}
				displayFood(foodPositions.poll());
			}
			if(board[r][c] == '.') {
                System.out.println("Game Over!!!");
                System.exit(0);
            }
            // Placing '.' and printing the snakeBoard combined in a single method
            moveForwardPrint(r, c);
		}else {
			 System.out.println("Invalid move");
	            System.exit(0);
		}
		
	}

	private void moveForwardPrint(int r, int c) {
		 board[r][c] = '.';
	     //printBoard();
		
	}

	private void printBoard() {
		for(char[] c:board) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(c[j]+" ");
			}
			System.out.println();
		}
		
	}

}
