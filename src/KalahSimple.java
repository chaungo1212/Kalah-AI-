
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
		while(!gm.game_over) {
			if ((gm.getP1Score() + gm.getP2Score()) == (gm.seeds_per * gm.houses)) {
				gm.game_over = true;
				break;
			}
			else {
				if (gm.player_1.getTurn() == true) {
					while (gm.player_1.getTurn()) {
						int location = 0;
						//location = minimax();
						//run minimax to get move
						//returns house number to move from
						gm.getBoard().sowSeeds(location);
					}
					gm.player_1.setTurn(false);
					gm.player_2.setTurn(true);
				}
				else {
					while (gm.player_2.getTurn()) {
						int location = 0;
						//run minimax to get move
						//returns house number to move from
						gm.getBoard().sowSeeds(location);
					}
					gm.player_1.setTurn(true);
					gm.player_2.setTurn(false);
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