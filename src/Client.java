import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	String message;
	Socket client_socket;
	Scanner client_scanner;
	String game_info;
	String player_move;
	
	private static final String red = "READY";
	private static final String ok = "OK";

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
	
	public Client() throws IOException {
		client_socket = new Socket("127.0.0.1", 4444);
		client_scanner = new Scanner(client_socket.getInputStream());
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public String getGameInfo() {
		return game_info;
	}
	
	public void setGameInfo(String new_info) {
		game_info = new_info;
	}
	
	public void close() {
		try {
			client_socket.close();
			client_scanner.close();
		}
		catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
		}
		catch (Exception e) {
			System.exit(1);
		}
	}

	public static void main(String args[]) throws UnknownHostException, ConnectException, IOException{
		try {
			Client c = new Client();
			Scanner scanner = new Scanner(System.in);
			//System.out.println("Pass to server: ");
			//c.setMessage(scanner.nextLine());
			
			PrintStream p_stream = new PrintStream(c.client_socket.getOutputStream());
			//p_stream.println(c.getMessage());
			
			//c.setMessage(c.client_scanner.nextLine());
			//System.out.println(c.getMessage());
			
			boolean quit = false;
			
			while(!quit) {
				/*****************************************************
				 * Main Game Loop
				 ****************************************************/
				c.client_scanner.nextLine(); //gets Welcome
				c.setGameInfo(c.client_scanner.nextLine()); //gets game_info
				
				c.setMessage(red);	//sends READY
				p_stream.println(c.getMessage());
				
				//start game
				//while not game over
					//if client turn
						//player_move = getMove(scanner.nextLine());
						//c.setMessage(player_move);	//sends move
						//p_stream.println(c.getMessage());
					//else server turn
						//c.client_scanner.nextLine(); //gets move
						//setMessage(ok);
						//p_stream.println(getMessage());
					
				
				System.out.println("Play Again? : (y/n)");
				if (scanner.nextLine().charAt(0) == 'n') {
					scanner.close();
					quit = true;
				}
				else {
					quit = false;
				}
			}
			
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
