
/*
	Project: Kalah_315
	File: KalahSimple.java
	Authors:
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class KalahSimple {
	
	Board board;
	Player player_1;
	Player player_2;
	KalahGUI gui;
	private int houses = 6;
	private int seeds = 4;
	private long timer = 5000;
	private char turn = 'F';
	
	public static AI startGame(GameManager gm) {
		String move = "";
		int t = 0;
		while(!gm.getGameOver()) {
			if ((gm.getP1Score() + gm.getP2Score()) == (gm.getSeeds() * gm.getHouses())) {
				gm.setGameOver();
				break;
			}
			else {
				if (gm.getP1().getTurn() == true) {
					while (gm.getP1().getTurn() == true) {

						// ************
						//move = minimax();
						//run minimax to get move
						//returns house number to move from
						t = gm.getBoard().sowSeeds(Integer.parseInt(move));
						if (t == 1) {
							gm.getP2().setTurn(true);
							gm.getP1().setTurn(false);
						}
						else {
							gm.getP2().setTurn(false);
							gm.getP1().setTurn(true);
						}
					}
				}
				else {
					while (gm.getP2().getTurn()) {
						
						// ************
						//move = minimax();
						//run minimax to get move
						//returns house number to move from
						if (gm.getTurnNum() == 1 && move == "P") {
							gm.getBoard().flipBoard();
							//swap players
							Player temp;
							temp = gm.getP1();
							gm.setP1(gm.getP2());
							gm.setP1(temp);
						}
						else {
							t = gm.getBoard().sowSeeds(Integer.parseInt(move));
						}
						if (t == 1) {
							gm.getP2().setTurn(true);
							gm.getP1().setTurn(false);
						}
						else {
							gm.getP2().setTurn(false);
							gm.getP1().setTurn(true);
						}
					}
				}
			}
		}
		return (AI) gm.whoWon();
	}
	
	public static void main(String[] args) throws IOException {
		/*****************************************************
		 * Starting Server / Getting data for game manager construction
		 *****************************************************/
		KalahSimple k = new KalahSimple();
		boolean quit = false;
		
		while(!quit) {
			/*****************************************************
			 * Main Game Loop
			 ****************************************************/
			GameManager game_manager = new GameManager(k.houses, k.seeds, k.timer, k.turn, 'm');
			
			Player p = startGame(game_manager);
			
			System.out.println("Winner:");
			System.out.print(p.getUsername());
			System.out.println(" " + p.getScore());
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Play Again? : (y/n)");
			Scanner input = new Scanner(System.in);
			if (input.nextLine().charAt(0) == 'n') {
				input.close();
				quit = true;
			}
			else {
				quit = false;
			}
		}

	}

}