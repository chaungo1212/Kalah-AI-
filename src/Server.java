import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

//not sure where to place
public class Server {
	private String message;
	private static ServerSocket listener;
	private static Socket server_socket;
	private static Scanner socket_scanner;
	private static boolean closed;
	private boolean stopped;
	private String game_config;
	
	private static final String wel = "WELCOME";
	private static final String red = "READY";
	private static final String ok = "OK";
	private static final String ill = "ILLEGAL";
	private static final String time = "TIME";
	private static final String loss = "LOSER";
	private static final String win = "WINNER";
	private static final String tie = "TIE";
	private static final String again = "AGAIN";
	
	public Server() throws IOException {
		parseFile();
		listener = new ServerSocket(4444); //getting an error because port number is taken on this computer
		server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
													// the server listens on, and another thread where is reads/writes
		socket_scanner = new Scanner(server_socket.getInputStream());
		closed = false;
		stopped = false;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public boolean getClosed() {
		return closed;
	}
	
	public boolean getStopped() {
		return stopped;
	}
	
	public void setStopped(boolean new_stop) {
		stopped = new_stop;
	}
	
	public Socket getSocket() {
		return server_socket;
	}
	
	public ServerSocket getListener() {
		return listener;
	}
	
	public Scanner getSocketScan() {
		return socket_scanner;
	}
	
	public String getGameConfig() {
		return game_config;
	}
	
	public void setGameConfig(String new_config) {
		game_config = new_config;
	}
	
	public void parseFile() throws FileNotFoundException {
		try {
			File file = new File("game2.txt");
			Scanner inputFile = new Scanner(file);
			while (inputFile.hasNext()) {
				setGameConfig(inputFile.nextLine().trim());
				System.out.println(getGameConfig());
			}
			inputFile.close();
			return;
		}
		catch (FileNotFoundException e) {
			System.err.println("Couldn't open game config file");
			System.exit(0);
		}
		catch (Exception e) {
			System.err.println("Exception thrown while parsing file");
			System.exit(0);
		}
	}
	
	public static void close() throws IOException {
		  try {
			if (!closed)
			{
				closed = true;
			    server_socket.close();
			    listener.close();
			    socket_scanner.close();
			}
		  }
		  catch(Exception e) {
				System.err.println("Couldn't close server");
				System.exit(0);
		  }
	}
	
	public Player startGame(GameManager gm) {
		while(!gm.game_over) {
			if ((gm.getP1Score() + gm.getP2Score()) == (gm.seeds_per * gm.houses)) {
				gm.game_over = true;
				break;
			}
			else {
				//if AI
				//start game
					//if client turn,
						//setMessage(getSocketScan().nextLine());
						//check if time out
						//if time out
							//setMessage(time);
							//p_stream.println(getMessage());
							//setMessage(loss);
							//p_stream.println(getMessage());
						//else
						//check if player_move (message) is valid
						//if is not valid
							//setMessage(ill);
							//p_stream.println(getMessage());
							//setMessage(loss);
							//p_stream.println(getMessage());
						//else
							//setMessage(ok);
							//p_stream.println(getMessage());
					//else (if server turn)
						//AI_move = AI_getmove();
						//setMessage(AI_move);
						//p_stream.println(getMessage());
						//check if time out
						//if time out
							//setMessage(time);
							//p_stream.println(getMessage());
							//setMessage(win);
							//p_stream.println(getMessage());
						//else
						//check if AI_move is valid
						//if is not valid
							//setMessage(ill);
							//p_stream.println(getMessage());
							//setMessage(win);
							//p_stream.println(getMessage());
				
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
	
	public static void main(String args[]) throws IOException {
		try {
			Server s = new Server();
			while (!s.getStopped()) {
				while (s.getSocket().isConnected()) {
					PrintStream p_stream = new PrintStream(s.getSocket().getOutputStream());
					s.setMessage(wel);
					p_stream.println(s.getMessage());
					s.setMessage("INFO" + s.game_config);
					p_stream.println(s.getMessage());
					if (s.getSocketScan().nextLine() == red) {
						GameManager game_manager = new GameManager();
						s.startGame(game_manager);	
					}
					if (s.getSocketScan().hasNext()) {
						if (s.getSocketScan().nextLine() == again) {
							s.setStopped(false);
						}
					}
					else {
						s.setStopped(true);
					}
				}
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
		finally {
			close();
		}
	}
}