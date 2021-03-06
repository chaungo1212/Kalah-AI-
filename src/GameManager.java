/*
	Project: Kalah_315
	File: GameManager.java
	Authors: 
*/

import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameManager {
	private Board board;
	private Player player_1;
	private Player player_2;
	private KalahGUI gui;
	private Timer timer;
	private boolean started = false;
	private boolean time_out = false;
	private boolean game_over = false;

	private int houses;
	private int seeds_per;
	private int scoreP1;
	private int scoreP2;
	private long timer_val;
	private long remaining_time;
	private int turn_num;
	private char player_choice = 'z';
	String player_name = "player";
	String file_name = "test.txt";

	Scanner reader = new Scanner(System.in);

	public boolean win_store1;
	public boolean win_store2;
	public int nseeds_all_south;
	public int nseeds_all_north;
	public GameManager(){
	//	newGame();
	//	gui = new KalahGUI();
		win_store1 = false;
		win_store2 = false;
		nseeds_all_south = 0;
		nseeds_all_north = 0;
	}
	
	public GameManager(int house, int seed, long time, char turn_num, char set_rand, int[] random, char AI_type) {
		newGame(house, seed, time, turn_num, set_rand, random, AI_type);
		//gui = new KalahGUI();
	}
	
	public GameManager(int house, int seed, long time, char turn_num, char AI_type) {
		newGame(house, seed, time, turn_num, AI_type);
		//gui = new KalahGUI();
	}

	public void makeMove(int location) {
		System.out.print("Updating Game\n");
	//	board.sowSeeds(location);
	}
	
	public boolean isStarted(){
		return started;
	}

/*	public boolean isValidMove(int location) {
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
*/
	public void startTimer() {
		remaining_time = timer_val;
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remaining_time == 0) {
					timer.stop();
					setTimeOut();
					//JOptionPane.showMessageDialog(null, "Out of time!");
				} else {
					remaining_time--;
				}
			}
		});
		timer.start();
	}
	
	public void endTimer() {
		timer.stop();
	}
	
	public boolean getTimeOut() {
		return time_out;
	}
	
	public void setTimeOut() {
		time_out = true;
	}
	
	public boolean getGameOver() {
		return game_over;
	}
	
	public void setGameOver() {
		game_over = true;
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
	
	public Board getBoard() {
		return board;
	}
	
	public int getSeeds() {
		return seeds_per;
	}
	
	public int getHouses() {
		return houses;
	}
	
	public Player getP1() {
		return player_1;
	}
	
	public void setP1(Player player) {
		player_1 = player;
	}
	
	public Player getP2() {
		return player_2;
	}
	
	public void setP2(Player player) {
		player_2 = player;
	}
	
	public int getTurnNum() {
		return turn_num;
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
		//	board = new Board(houses, seeds);
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
		
		turn_num = 0;
		player_1.setTurn(true);
		in.close();
	}
	
	public void newGame(int house, int seed, long time, char turn_num, char set_rand, int[] random, char AI_type) {
		//read # houses per side
		houses = house;

		//read # seeds
		seeds_per = seed;

		//if next is digit, read timer value
		if (time != 0)
		{
			timer_val = time;
			remaining_time = timer_val;
		}

		//read if client goes first or second
		player_choice = turn_num;
		
		//read if static # of seeds or random
		if (set_rand == 'R') {
			int[] seeds = new int[houses];
			for(int i = 0; i > houses; i++) {
					seeds[i] = random[i];	
			}
	//		board = new Board(houses, seeds);
		}
		else {
			board = new Board(houses, seeds_per);
		}
		
		//*******get user name of player?
		//System.out.println("Username: ");
		//player_name = reader.next();

		//create and set players
	/*	if (player_choice == 'F') {
			player_1 = new AI(AI_type);
			player_2 = new AI(AI_type);
		}
		else {
			player_1 = new AI(AI_type);
			player_2 = new AI(AI_type);
		}
	*/	
		turn_num = 0;
		player_1.setTurn(true);
	}
	
	public void newGame(int house, int seed, long time, char turn_num, char AI_type) {
		//read # houses per side
		houses = house;

		//read # seeds
		seeds_per = seed;

		//if next is digit, read timer value
		if (time != 0)
		{
			timer_val = time;
		}

		//read if client goes first or second
		player_choice = turn_num;
		
		board = new Board(houses, seeds_per);
		
		//create and set players
	/*	if (player_choice == 'F') {
			player_1 = new AI(AI_type);
			player_2 = new AI(AI_type);
		}
		else {
			player_1 = new AI(AI_type);
			player_2 = new AI(AI_type);
		}
*/		
		turn_num = 0;
		player_1.setTurn(true);
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



	public boolean checkwin(Vector<JButton> buttons_south, Vector<JButton> buttons_north, JLabel store1, JLabel store2){
		/* Check either north side or south side has all "0"
		 * Then game stops and sends one side's all seed to either store 1
		 * and store 2.
		 */
		win_store1 = true;
		nseeds_all_south = 0;
		//north side check all houses if it is equal to 0
		for (int i = 0; i < buttons_south.size(); i++) {
			if (Integer.parseInt(buttons_south.elementAt(i).getText()) != 0) {
				win_store1 = false;
			}
			nseeds_all_south = nseeds_all_south + Integer.parseInt(buttons_south.elementAt(i).getText());
		}
		win_store2 = true;
		nseeds_all_north = 0;
		//north side check all houses if it is equal to 0
		for (int i = 0; i < buttons_north.size(); i++) {
			if (Integer.parseInt(buttons_north.elementAt(i).getText()) != 0) {
				win_store2 = false;
			}
			nseeds_all_north = nseeds_all_north + Integer.parseInt(buttons_north.elementAt(i).getText());
		}
		
		return true;
	}
}
