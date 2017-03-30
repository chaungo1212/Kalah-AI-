
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