package com.kannanrameshrk.brickbreaker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("\t\t Brick Breaker");
		System.out.println("\t\t**************");
		
		BrickBreaker brickBreaker=new BrickBreaker(7,7);
		brickBreaker.placeBricks(2,2,2);
		brickBreaker.placeBricks(2,3,2);
		brickBreaker.placeBricks(2,4,2);
		brickBreaker.placeBricks(3,2,2);
		brickBreaker.placeBricks(3,3,2);
		brickBreaker.placeBricks(3,4,2);
		
		while(true) {
			brickBreaker.printGameBoard();
			
			if(brickBreaker.getBallLife()<=0) {
				System.out.println("Ball life is Over..");
				System.exit(0);
			}
			
			System.out.println("Enter the direction:");
			String direction=new Scanner(System.in).next();
			
			switch(direction) {
			case "lt":{
				int[] ballPos=brickBreaker.getBallPosition();
				brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,-1);
				break;
			}
			case "rt":{
				int[] ballPos=brickBreaker.getBallPosition();
				brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,1);
				break;
			}
			case "st":{
				int[] ballPos=brickBreaker.getBallPosition();
				brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,0);
				break;
			}
			default:{
				System.out.println("Invalid direction..");
				break;
			}
			}
		}
	}

}
