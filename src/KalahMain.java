
/*
	Project: Kalah_315
	File: KalahMain.java
	Authors:
*/

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class KalahMain {
	
	public static Player startGame(GameManager gm) {
		while(!gm.game_over) {
			if ((gm.getP1Score() + gm.getP2Score()) == (gm.seeds_per * gm.houses)) {
				gm.game_over = true;
				break;
			}
			else {
				if (gm.player_1.getTurn() == true) {
					//while (getMove()) {
						//get move
						//update server
						//update board
						//update GUI
					//}
					gm.player_1.setTurn(false);
					gm.player_2.setTurn(true);
				}
				else {
					//while (getMove()) {
						//get move
						//update server
						//update board
						//update GUI
					//}
					gm.player_1.setTurn(true);
					gm.player_2.setTurn(false);
				}
			}
		}
		return gm.whoWon();
	}

	public static void main(String[] args) throws IOException {
		/*****************************************************
		 * Starting Server / Getting data for game manager construction
		 *****************************************************/
		
		Server s = new Server();
		boolean quit = false;
		
		while(!quit) {
			/*****************************************************
			 * Main Game Loop
			 ****************************************************/
			GameManager game_manager = new GameManager();

			while (!game_manager.isStarted()) {
				s.setMessage(s.getSocketScan().nextLine());
				
				PrintStream p_stream = new PrintStream(s.getSocket().getOutputStream());
				p_stream.println(s.getMessage());

			}
			
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
		s.close();

	}

}