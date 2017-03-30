<<<<<<< HEAD
/*
	Project: Kalah_315
	File: KalahMain.java
	Authors:
*/

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class KalahMain {

	public static void main(String[] args) throws IOException {		
		/*****************************************************
		 * Getting data for game manager construction
		 ****************************************************/
		GameManager game_manager = new GameManager();
		
		String message;
		ServerSocket listener = new ServerSocket(5353); //getting an error because port number is taken on this computer
		Socket server_socket = listener.accept();
		Scanner socket_scanner = new Scanner(server_socket.getInputStream());

		
		/*****************************************************
		 * Main Game Loop
		 ****************************************************/
		while (!game_manager.isStarted()) {
			message = socket_scanner.nextLine();
			
			PrintStream p_stream = new PrintStream(server_socket.getOutputStream());
			p_stream.println(message);

		}

		socket_scanner.close();
		listener.close();

	}

}
=======
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
>>>>>>> 4a4a04df5b5250f4f44b48ea01648a4b09483f69
