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
	
	public Server() throws IOException {
		listener = new ServerSocket(4444); //getting an error because port number is taken on this computer
		server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
													// the server listens on, and another thread where is reads/writes
		socket_scanner = new Scanner(server_socket.getInputStream());
		closed = false;
		stopped = false;
		parseFile();
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
	
	public Socket getSocket() {
		return server_socket;
	}
	
	public ServerSocket getListener() {
		return listener;
	}
	
	public Scanner getSocketScan() {
		return socket_scanner;
	}
	
	public void parseFile() throws FileNotFoundException {
		try {
			File file = new File("game.txt");
			Scanner inputFile = new Scanner(file);
			while (inputFile.hasNext()) {
				game_config = inputFile.nextLine();
			}
			inputFile.close();
		}
		catch (Exception e) {
			System.err.println("Couldn't open game config file");
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
			  //catch exceptions
		  }
	}
	
	public static void main(String args[]) throws IOException {
		try {
			Server s = new Server();
			while (!s.getStopped()) {
				if (s.getSocket().isConnected()) {
					PrintStream p_stream = new PrintStream(s.getSocket().getOutputStream());
					s.setMessage(wel);
					p_stream.println(s.getMessage());
					s.setMessage("INFO" + s.game_config);
					p_stream.println(s.getMessage());
					if (s.getSocketScan().nextLine() == red) {
						GameManager game_manager = new GameManager();
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