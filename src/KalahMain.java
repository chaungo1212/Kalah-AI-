/*
	Project: Kalah_315
	File: KalahMain.java
	Authors:
*/

import java.util.Scanner;

public class KalahMain {

	public static void main(String[] args) {
		/*****************************************************
		 * Getting data for game manager construction
		 ****************************************************/

		GameManager game_manager = new GameManager();
		game_manager.newGame();

		/*****************************************************
		 * Main Game Loop
		 ****************************************************/
		int location;
		int max_time = 30;

		Scanner reader = new Scanner(System.in);

		// System.out.println("Enter -1 to start a new game and -2 to reset it:
		// \n");
		game_manager.drawGame();
		System.out.println("Enter a location number to move: ");
		location = reader.nextInt();

		while (!game_manager.isGameOver()) {
			game_manager.startTimer();

			while (game_manager.checkTimer() != max_time) {
				game_manager.drawGame();

				System.out.println("Enter Location: ");
				location = reader.nextInt();

				if (game_manager.isValidMove(location)) {
					game_manager.makeMove(location);
				} else if (location == -1) {

					game_manager.newGame();
				} else if (location == -2) {
					game_manager.resetGame();
				} else {
					System.out.print("Invalid Move\n");
				}
			}
			if (game_manager.checkTimer() == max_time) {
				System.out.print("You ran out of time\n");
			}
			game_manager.updateGame();

		}

		reader.close();

	}

}
