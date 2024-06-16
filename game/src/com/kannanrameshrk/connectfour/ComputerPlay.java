package com.kannanrameshrk.connectfour;

public class ComputerPlay {

	public int getComputerMove(char[][] board) {
		//level3 
		
		for(int j=0;j<board[0].length;j++) {
			if(ConnectFour.validate(j, board)) {
				for(int i=board.length-1;i>=0;i--) {
					if(board[i][j]=='-') {
						board[i][j]='Y';
						if(ConnectFour.isWinner('Y', board)) {
							board[i][j]='-';
							return j;
						}
						board[i][j]='-';
						break;
					}
				}
			}
		}
		
		//level 2
		for (int col = 0; col < board[0].length; col++) {
            if (ConnectFour.validate(col, board)) {
                // Temporarily make the move
                for (int row = board.length - 1; row >= 0; row--) {
                    if (board[row][col] == '-') {
                        board[row][col] = 'R';
                        if (ConnectFour.isWinner('R', board)) {
                            board[row][col] = '-'; // Undo the move
                            return col; // Block this move
                        }
                        board[row][col] = '-'; // Undo the move
                        break;
                    }
                }
            }
        }
        // If no immediate threat, choose the first available column
        for (int col = 0; col < board[0].length; col++) {
            if (ConnectFour.validate(col, board)) {
                return col;
            }
        }
        return -1;
	}

}
