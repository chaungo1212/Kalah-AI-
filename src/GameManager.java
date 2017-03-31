/*
	Project: Kalah_315
	File: GameManager.java
	Authors: 
*/

import java.util.Scanner;

public class GameManager {
	Board board;
	Player player_1;
	Player player_2;
	KalahGUI gui;
	boolean started = false;
	boolean game_over = false;

	int houses;
	int seeds_per;
	private int scoreP1;
	private int scoreP2;
	int timer_val;
	int timer;
	char player_choice = 'z';
	String player_name = "player";
	String file_name = "test.txt";

	Scanner reader = new Scanner(System.in);

	public GameManager() {
		newGame();
		gui = new KalahGUI();
	}

	public void makeMove(int location) {
		System.out.print("Updating Game\n");
		board.sowSeeds(location);
	}
	
	public boolean isStarted(){
		return started;
	}

	public boolean isValidMove(int location) {
		if (location > 0 && location < board.getSeeds().length-1) {
			if (player_1.getTurn() == true) {
				if (location <= board.getSeeds().length-1/2) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if (location > board.getSeeds().length-1/2) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	public void startTimer() {
		System.out.print("Starting timer\n");
		timer = 0;
	}

	public int checkTimer() {
		return timer;
	}

	public boolean timeIsOut() {
		if (timer >= timer_val) {
			return true;
		}
		else {
			return false;
		}
	}

	public Player whoWon() {
		if (scoreP1 > scoreP2) {
			return player_1;
		}
		else if (scoreP1 < scoreP2) {
			return player_2;
		}
		else {
			Player tie = new Player("tie");
			tie.setScore(scoreP1);
			return tie;
		}
	}

	public int getP1Score() {
		return scoreP1;
	}

	public void setP1Score(int score) {
		this.scoreP1 = score;
	}

	public int getP2Score() {
		return scoreP2;
	}

	public void setP2Score(int score) {
		this.scoreP2 = score;
	}

	public void newGame() {
		Scanner in = new Scanner(file_name);
		
		//read # houses from file
		houses = in.nextInt();

		//read # seeds from file
		seeds_per = in.nextInt();

		//if next is digit, read timer value
		if (in.hasNextInt())
		{
			timer_val = in.nextInt();
		}

		//read if client goes first or second
		player_choice = in.next().charAt(0);
		
		//read if static # of seeds or random
		if (in.next().charAt(0) == 'R') {
			int[] seeds = new int[houses];
			for(int i = 0; i > houses; i++) {
				if (in.hasNextInt()) {
					seeds[i] = in.nextInt();	
				}
			}
			board = new Board(houses, seeds);
		}
		else {
			board = new Board(houses, seeds_per);
		}
		
		//*******get user name of player?
		//System.out.println("Username: ");
		//player_name = reader.next();

		//create and set players
		if (player_choice == 'F') {
			player_1 = new Player(player_name);
			player_2 = new Player("computer");
		}
		else {
			player_1 = new Player("computer");
			player_2 = new Player(player_name);
		}
		
		player_1.setTurn(true);
		in.close();
	}
	
	public void updateGame() {
		System.out.print("Updating Game\n");
	}

	public void resetGame() {
		System.out.print("Reset Game\n");

	}

	public void drawGame() {
		board.drawBoard();
	}
}
