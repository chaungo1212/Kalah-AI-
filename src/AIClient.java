import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AIClient {
	String message;
	Socket client_socket;
	Scanner client_scanner;
	String game_info;
	String player_move;
	int turn;
	int client_turn;
	AI this_AI;
	
	private static final String red = "READY";
	private static final String ok = "OK";
	private static final String again = "AGAIN";
	
	public AIClient() throws IOException {
		client_socket = new Socket("127.0.0.1", 4444);
		client_scanner = new Scanner(client_socket.getInputStream());
		turn=0;
		//change this to test
		this_AI = new AI('m');
	}
	
	public AI getAI() {
		return this_AI;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public int getClientTurn() {
		return client_turn;
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
		//System.out.println(new_info);
		game_info = new_info;
		String info = new_info.substring(4);
		//System.out.println(info);
		//houses
		//Integer.parseInt(info);
		info = info.substring(2);
		//System.out.println(info);
		//seeds
		//Integer.parseInt(info);
		info = info.substring(2);
		//System.out.println(info);
		//time
		//Long.parseLong(info);
		while (Character.isDigit(info.charAt(0)) == true) {
			info = info.substring(1);
			//System.out.println(info);
		}
		info = info.substring(1);
		//System.out.println(info);
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
			AIClient c = new AIClient();
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
				String wel = c.client_scanner.nextLine(); //gets welcome
				String gamein = c.client_scanner.nextLine();
				c.setGameInfo(gamein); //gets game_info
				
				c.setMessage(red);	//sends READY
				p_stream.println(c.getMessage());
				System.out.println(red);
				String ai_move;
				
				
				String begin = c.client_scanner.nextLine(); //gets begin
				System.out.println(begin);
				
				while (c.client_scanner.hasNextLine()) {		
					String input = c.client_scanner.nextLine();
					
					while (input != "WINNER" || input != "LOSER" || input != "TIME" || input != "ILLEGAL") {
						if(c.turn%2 == c.client_turn) {
							ai_move = c.this_AI.runWhat(c.this_AI.getType());
							c.setMessage(ai_move);	//sends move
							p_stream.println(c.getMessage());
							c.turn++;
							System.out.println(c.turn);
						}
						else {
							//input is move
							c.setMessage(ok);
							p_stream.println(c.getMessage());
							c.turn++;
							System.out.println(c.turn);
						}
				}	
				//bye
			}
			
			scanner.close();
			c.close();
			}
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
