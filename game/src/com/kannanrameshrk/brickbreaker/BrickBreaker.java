package com.kannanrameshrk.brickbreaker;

import java.util.HashMap;
import java.util.Map;

public class BrickBreaker {
	private static String wall="w";
	private static String brick="1";
	private static String ground="g";
	private static String ball="o";
	
	private static Map<Integer,Integer> brickeswithLife=new HashMap<>();
	private static int[] balPos=null;
	private static int ballLife=5;
	
	private String[][] gameBoard=null;

	public BrickBreaker(int row, int col) {
		gameBoard=new String[row][col];
		prepareBoard();
		gameBoard[row-1][col/2]=ball;
		balPos=new int[] {row-1,col/2};
	}

	private void prepareBoard() {
		for(int i=0;i<gameBoard.length;i++) {
			for(int j=0;j<gameBoard[0].length;j++) {
				if(i==0 || j==0 || j==gameBoard[0].length-1) {
					gameBoard[i][j]=wall;
				}else if(i==gameBoard.length-1) {
					gameBoard[i][j]=ground;
				}else {
					gameBoard[i][j]=" ";
				}
			}
		}
		
	}

	public void placeBricks(int row, int col, int life) {
		gameBoard[row][col]=brick;
		int exactPosition=getExactBallPosition(row,col);
		brickeswithLife.put(exactPosition, life);
	}

	private int getExactBallPosition(int row, int col) {
		return (row*gameBoard[0].length)+col+1;
	}

	public void printGameBoard() {
		for(String[] s:gameBoard) {
			for(String str:s) {
				System.out.print(str+" ");
			}
			System.out.println();
		}
		
	}

	public int getBallLife() {
		return ballLife;
	}

	public int[] getBallPosition() {
		return balPos;
	}

	public void initiateBall(int ballRow, int ballCol, int rowDirection, int colDirection) {
		moveDirection(ballRow,ballCol,rowDirection,colDirection);
	}

	private void moveDirection(int ballRow, int ballCol, int rowDirection, int colDirection) {
		//gameBoard[ballRow][ballCol]=ground;
		while(!gameBoard[ballRow][ballCol].equals(wall)){
			if(gameBoard[ballRow][ballCol].equals(brick)) {
				ballGoesDown(ballRow,ballCol);
				return;
			}
			movingIllusion(ballRow,ballCol);
			ballCol+=colDirection;
			ballRow+=rowDirection;
		}
		wallHit(ballRow,ballCol);
		 rowDirection = 0;
	     colDirection = colDirection*-1;
	        
	        if(colDirection == 0)     // means ball is in straight line. There is no possible of bricks in st line
	            ballGoesDown(ballRow+1, ballCol);
	        else
	            moveDirection(ballRow, ballCol + colDirection, rowDirection, colDirection);
	}

	private void wallHit(int ballRow, int ballCol) {
		 gameBoard[ballRow][ballCol] = ball;
	        printGameBoard();
	        sleepForOneSec();
	        gameBoard[ballRow][ballCol] = wall;
		
	}

	private void ballGoesDown(int ballRow, int ballCol) {
		  while(ballRow != gameBoard.length) {
	            movingIllusion(ballRow, ballCol); // move the ball
	            ballRow++;
	        }
	        balPos = new int[]{ballRow-1, ballCol};
	        gameBoard[balPos[0]][balPos[1]] = ball; // new ball position
	}

	private void movingIllusion(int ballRow, int ballCol) {
		  if(gameBoard[ballRow][ballCol].equals(brick)) {
	            reduceBrickAndBallLife(ballRow, ballCol);
	            if(brickeswithLife.get(getExactBallPosition(ballRow, ballCol)) == 0) {
	                gameBoard[ballRow][ballCol] = " ";
	            }
	        }
	        else {
	            gameBoard[ballRow][ballCol] = ball;
	            printGameBoard();
	            gameBoard[ballRow][ballCol] = " ";
	            sleepForOneSec();
	        }
		
	}

	private void sleepForOneSec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.getCause();
		}
	}

	private void reduceBrickAndBallLife(int ballRow, int ballCol) {
		int exactPosition = getExactBallPosition(ballRow, ballCol);
        ballLife--;  // if ball hits brick, ball's life reduces

        if(ballLife >= 0) // if ball life is not negative
            brickeswithLife.put(exactPosition, brickeswithLife.get(exactPosition) - 1);
	}

}
