import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class NewClient {
	String message;
	Socket client_socket;
	Scanner client_scanner;
	
	Player s_player;
	Player c_player;
	String game_info;
	String move;
	int cur_turn;
	int client_turn;
	long timer_val;
	Board b;
	Game game;
	boolean ai;
	
	private static final String red = "READY";
	private static final String ok = "OK";
	
	public NewClient() throws IOException {
		client_socket = new Socket("127.0.0.1", 4444);
		client_scanner = new Scanner(client_socket.getInputStream());
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public Board setGameInfo(String new_info) {
		game_info = new_info;
		String info = new_info.substring(4);
		//houses
		int houses = Integer.parseInt(info);
		info = info.substring(2);
		//seeds
		int seeds = Integer.parseInt(info);
		info = info.substring(2);
		//time
		timer_val = Long.parseLong(info);
		while (Character.isDigit(info.charAt(0)) == true) {
			info = info.substring(1);
		}
		info = info.substring(1);
		char c = info.charAt(0);
		switch (c) {
			case 'F':
				client_turn = 0;
				break;
			case 'S':
				client_turn = 1;
				break;
			default:
				client_turn = 0;
		}
		cur_turn=0;
		return new Board(houses, seeds);
	}
	
	public void close() {
		try {
			client_socket.close();
			client_scanner.close();
		}
		catch (IOException e) {
            System.err.println("Couldn't close client");
            System.exit(1);
		}
		catch (Exception e) {
			System.exit(1);
		}
	}

	public static void main(String args[]) throws UnknownHostException, ConnectException, IOException{
		try {
			NewClient c = new NewClient();
			Scanner scanner = new Scanner(System.in);
			PrintStream p_stream = new PrintStream(c.client_socket.getOutputStream());
			boolean quit = false;
			
			//ask what kind of client this is
			int result = JOptionPane.showConfirmDialog(null, 
					   "Is this an AI running client?",null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				c.ai = true;
			} 
			else {
				c.ai = false;
			}
			
			
			while (!quit) {
				// Main Game Loop
				c.client_scanner.nextLine(); //gets welcome
				String gamein = c.client_scanner.nextLine(); //gets game_info
				c.b = c.setGameInfo(gamein); 
				
				if(c.ai) {
					c.setMessage(red);	//sends READY
					p_stream.println(c.getMessage());
					System.out.println(red);
					String begin = c.client_scanner.nextLine(); //gets begin
					System.out.println(begin);
					
					//run AI moves
					while (c.client_scanner.hasNextLine()) {		
						String input = c.client_scanner.nextLine();
						
						while (input != "WINNER" || input != "LOSER" || input != "TIME" || input != "ILLEGAL" || input != "TIE") {
							if(c.cur_turn%2 == c.client_turn) {
								//ai_move = c.this_AI.search_move(board);
								//c.setMessage(ai_move);	//sends move
								p_stream.println(c.getMessage());
								c.cur_turn++;
								System.out.println(c.cur_turn);
							}
							else {
								//input is move
								c.setMessage(ok);
								p_stream.println(c.getMessage());
								c.cur_turn++;
								System.out.println(c.cur_turn);
							}
						}	
					}
				}
				else {
					c.setMessage(red);	//sends READY
					p_stream.println(c.getMessage());
					System.out.println(red);
					String begin = c.client_scanner.nextLine(); //gets begin
					System.out.println(begin);
					// *****
					//launch gui and prepare player
					while (c.client_scanner.hasNextLine()) {		
						String input = c.client_scanner.nextLine();
						
						while (input != "WINNER" || input != "LOSER" || input != "TIME" || input != "ILLEGAL" || input != "TIE") {
							//client turn
							if(c.cur_turn%2 == c.client_turn) {
								// *****
								//get client turn
								if (c.ai) {
									//get AI move
								}
								else {
									//get player move
								}
							}
							//server turn
							else {
								//input is move
								c.setMessage(ok);
								p_stream.println(c.getMessage());
								c.cur_turn++;
								System.out.println(c.cur_turn);
							}
						}
					}
				}
			}
			//bye
			scanner.close();
			c.close();
		}
		catch (IOException i) {
	            System.err.println("Couldn't get I/O for the connection to: localhost.");
	            System.err.println("Connection refused: localhost.");
	            System.exit(1);
	    }
		catch (Exception e) {
			System.exit(1);
		}
	}
}
