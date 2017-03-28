/*
	Project: Kalah_315
	File: GameManager.java
	Authors: 
*/

import java.util.Scanner;


//need some GameManager functions to be static so that
//they can be called by the other classes
public class GameManager {
	static Board board;
	static Player player_1;
	static Player player_2;
	KalahGUI gui;

	int houses;
	static int seeds_per;
	int player_choice = -1;
	int scoreP1;
	int scoreP2;
	String player_name;

	Scanner reader = new Scanner(System.in);

	public GameManager() {
	}

	public void makeMove(int location) {
		System.out.print("Updating Game\n");
		board.sowSeeds(location);
	}

	public static boolean isValidMove(int location) {
		if(player_1.getTurn() == true) {
			if((location <= board.getSeeds().length/2) && location > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if((location > board.getSeeds().length/2) && (location < board.getSeeds().length-1)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public void startTimer() {
		System.out.print("Starting timer\n");
	}

	public int checkTimer() {
		return 1;
	}

	public boolean timeIsOut() {
		return false;
	}

	public boolean isGameOver() {
		return false;
	}

	public Player whoWon() {
		if (scoreP1 > scoreP2) {
			return player_1;
		}
		else if (scoreP1 < scoreP2) {
			return player_2;
		}
		else {
			Player tie = new Player("tie", 3);
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

	public void updateGame() {
		System.out.print("Updating Game\n");
	}

	public void newGame() {

		System.out.print("Welcome to the game of kalah!\n");

		System.out.println("Enter the number of houses: ");
		houses = reader.nextInt();

		System.out.println("Enter the number of seeds per house: ");
		seeds_per = reader.nextInt();

		while (player_choice != 1 && player_choice != 2) {
			System.out.println("Would you like to be player 1 or 2?\nEnter 1 or 2: ");
			player_choice = reader.nextInt();
		}

		System.out.println("Username: ");
		player_name = reader.next();

		board = new Board(houses, seeds_per);

		if (player_choice == 1) {
			player_1 = new Player(player_name, 1);
			player_2 = new Player("computer", 2);
		} else {
			player_1 = new Player("computer", 1);
			player_2 = new Player(player_name, 2);
		}

		System.out.print("New Game\n");
	}

	public void resetGame() {
		System.out.print("Reset Game\n");

	}

	public void drawGame() {
		board.drawBoard();
	}
	
	public static int playerTurn() {
		if (player_1.getTurn() == true) {
			return 1;
		}
		else {
			return 2;
		}
	}

}
