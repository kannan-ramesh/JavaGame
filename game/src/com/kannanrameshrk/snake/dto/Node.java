package com.kannanrameshrk.snake.dto;

public class Node {
	private int row=0;
	private int col=0;
	
	public Node(int row, int col) {
		this.row=row;
		this.col=col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
}
