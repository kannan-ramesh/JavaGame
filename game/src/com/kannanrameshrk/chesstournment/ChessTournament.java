package com.kannanrameshrk.chesstournment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.kannanrameshrk.chesstournment.dto.Player;

public class ChessTournament {
	Scanner input=new Scanner(System.in);
	List<Player> playerData=new ArrayList<>();
	Random random=new Random();
	
	public void start() {
		System.out.println("Enter Number of Player:");
		int n=input.nextInt();
		
		addPlayer(n);
		System.out.println("Enter No of Rounds:");
		int round=input.nextInt();
		
		playGame(round,n);
	}

	private void playGame(int round, int n) {
		int i=0;
		while(i<round) {
			System.out.println("Round of Tournment:"+(i+1));
			System.out.println("---------------------------");
			
	        playRound(n);
	           
			System.out.println("round "+(i+1)+" score card:");
			System.out.println("---------------------------");
			scoreBoard(playerData);
			i++;
		}
	}

	private void playRound(int n) {
		//playerData.sort(Comparator.comparingDouble(player-> ((Player) player).getTotalScore()).reversed());
		 playerData.sort(Comparator.comparingDouble(Player::getTotalScore).reversed());
	        List<Player> roundPlayers = new ArrayList<>(playerData);
	        boolean flag = false;
	        Player bonusPlayer = null;

	        if (n % 2 != 0) {
	            bonusPlayer = roundPlayers.remove(roundPlayers.size() - 1);
	            bonusPlayer.setScore(1);
	            bonusPlayer.setTotalScore(1);
	            flag = true;
	        }

	        List<Player> matchedPlayers = new ArrayList<>();
	        
	        for (int j = 0; j < roundPlayers.size(); j++) {
	            Player player1 = roundPlayers.get(j);
	            if (matchedPlayers.contains(player1)) {
	                continue;
	            }

	            Player player2 = findOpponent(player1, roundPlayers, matchedPlayers);
	            if (player2 != null) {
	                addPoint(player1, player2);
	                matchedPlayers.add(player1);
	                matchedPlayers.add(player2);
	            }
	        }

	        printPlayer(matchedPlayers);
	        if (flag) {
	            System.out.println(bonusPlayer.getName() + "(" + bonusPlayer.getScore() + ": Bye)");
	        }
	}

	private Player findOpponent(Player player, List<Player> roundPlayers, List<Player> matchedPlayers) {
		 for (Player potentialOpponent : roundPlayers) {
	            if (!player.getOpponent().contains(potentialOpponent) && !matchedPlayers.contains(potentialOpponent) && !potentialOpponent.equals(player)) {
	                return potentialOpponent;
	            }
	        }
	        return null;
	}

	private void scoreBoard(List<Player> playerData2) {
		playerData2.stream().sorted((a,b)->(int)b.getTotalScore()-(int)a.getTotalScore()).forEach(p->System.out.println(p.getName()+" ("+p.getTotalScore()+")"));
	}

	private void printPlayer(List<Player> playerData) {
		for(int i=0;i<playerData.size()-1;i=i+2) {
			System.out.println(playerData.get(i).getName()+" ("+playerData.get(i).getScore()+":"+playerData.get(i+1).getScore()+") "+playerData.get(i+1).getName());
		}
	}

	private void addPoint(Player player, Player player2) {
		int point=random.nextInt(3);
		player.addOpponent(player2);
		player2.addOpponent(player);
		
		if(point==1) {
			player.setScore(1);
			player2.setScore(0);
			player.setTotalScore(1);
		}else if(point==0) {
			player.setScore(0);
			player2.setScore(1);
			player2.setTotalScore(1);
		}else {
			player.setScore(0.5);
			player2.setScore(0.5);
			player.setTotalScore(0.5);
			player2.setTotalScore(0.5);
		}
	}

	private void addPlayer(int n) {
		for(int i=1;i<=n;i++) {
			Player p=new Player(i,"Player "+i);
			playerData.add(p);
		}
	}
}