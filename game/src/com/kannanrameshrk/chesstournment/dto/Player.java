package com.kannanrameshrk.chesstournment.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int id;
	private String name;
	private double score;
	private double totalScore;
	private List<Player> opponent;
	
	public Player() {
		opponent=new ArrayList<>();
	}
	public Player(int id, String name) {
		this.id=id;
		this.name=name;
		 this.opponent = new ArrayList<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore += totalScore;
	}
	public List<Player> getOpponent() {
		return opponent;
	}
	public void setOpponent(List<Player> player2) {
		this.opponent = player2;
	}
	
	public void addOpponent(Player player) {
		opponent.add(player);
	}
}
