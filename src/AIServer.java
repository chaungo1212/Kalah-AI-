import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

//not sure where to place
public class AIServer {
	private String message;
	private static ServerSocket listener;
	private static Socket server_socket;
	private static Scanner socket_scanner;
	private static boolean closed;
	private boolean stopped;
	private String game_config;
	
	Board board;
	AI player_1;
	AI player_2;
	KalahGUI gui;
	private int houses;
	private int seeds;
	private long timer;
	private char turn;
	private char set_rand;
	private int[] random;
	String player_name = "player";
	String file_name = "test.txt";
	private int turn_num = 0;
	
	private static final String wel = "WELCOME";
	private static final String red = "READY";
	private static final String beg = "BEGIN";
	private static final String ok = "OK";
	private static final String ill = "ILLEGAL";
	private static final String time = "TIME";
	private static final String loss = "LOSER";
	private static final String win = "WINNER";
	private static final String tie = "TIE";
	private static final String again = "AGAIN";
	
	public AIServer() throws IOException {
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


// **************************************************************************
	
	public void setGameInfo(String new_info) {
		String info = new_info.substring(4);
		game_config = info;
		System.out.println(info);
		
		houses = Integer.parseInt(info);
		info = info.substring(2);
		System.out.println(info);
		
		seeds = Integer.parseInt(info);
		info = info.substring(2);
		System.out.println(info);
		
		Long.parseLong(info);
		while (Character.isDigit(info.charAt(0)) == true) {
			info = info.substring(1);
		}
		info = info.substring(1);
		System.out.println(info);
		char c = info.charAt(0);
		switch (c) {
			case 'F':
				turn = 1;
				break;
			case 'S':
				turn = 2;
				break;
			default:
				turn = 1;
		}
		info = info.substring(1);
		c = info.charAt(0);
		set_rand = c;
		switch (c) {
			case 'S':
				random = new int[0];
				break;
			case 'R':
				info = info.substring(1);
				random = new int[houses];
				for (int i = 0; i < houses; i++) {
					random[i] = info.charAt(0);
					info = info.substring(1);
				}
				break;
			default:
				random = new int[0];
		}
	}
	
	public void newGame(int house, int seed, long time, char turns, char set_rand, int[] random, char AI_type) {
		//read # houses per side
		houses = house;

		//read # seeds
		seeds = seed;

		//if next is digit, read timer value
		if (time != 0)
		{
			timer = time;
		}

		//read if client goes first or second
		turn = turns;
		
		//read if static # of seeds or random
		if (set_rand == 'R') {
			int[] seeds = new int[houses];
			for(int i = 0; i > houses; i++) {
					seeds[i] = random[i];	
			}
			board = new Board(houses, seeds);
		}
		else {
			board = new Board(houses, seeds);
		}

		player_1 = new AI();
		player_2 = new AI();
	}
	
	
	
	/*
	public void AI_turn(AI AI, GameManager gm, PrintStream p_stream) {
		int AI_move = AI.runWhat(AI.getType())
		if (gm.isValidMove(AI_move))
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
	
	public void Player_turn(Player player, GameManager gm, PrintStream p_stream) {
		String player_move;
		setMessage(getSocketScan().nextLine());
		if (gm.timeIsOut()) {
			setMessage(time);
			p_stream.println(getMessage());
			setMessage(loss);
			p_stream.println(getMessage());
			gm.game_over = true;
		}
		else {
			player_move = getMessage();
			if (!gm.isValidMove((Integer.parseInt(player_move)))) {
				setMessage(ill);
				p_stream.println(getMessage());
				setMessage(loss);
				p_stream.println(getMessage());
			}
			else {
				setMessage(ok);
				p_stream.println(getMessage());
			}
		}
	}
	*/
	public AI startGame(PrintStream p_stream, AI ai, AI ai2) {
		boolean game_over = false;
		int score1 = 0;
		int score2 = 0;
		boolean player1_turn;
		if (turn == 'F') {
			player1_turn = true;
		}
		else {
			player1_turn = false;
		}
		String move;
		int t = 0;
		
		while(!game_over) {
			if ((score1 + score2) == (seeds*houses)) {
				game_over = true;
				break;
			}
			else {
				if (player1_turn) {
					while (player_1.turn == true) {
						move = this.getSocketScan().nextLine();
						t = board.sowSeeds(Integer.parseInt(move));
						if (t == 1) {
							player_1.setTurn(true);
							player_2.setTurn(false);
						}
						else {
							player_1.setTurn(false);
							player_2.setTurn(true);
						}
					}
				}
				else {
					while (player_2.turn == true) {
						move = this.getSocketScan().nextLine();
						if (turn_num == 1 && move == "P") {
							board.flipBoard();
							//swap players
							AI temp;
							temp = player_1;
							player_1 = player_2;
							player_2 = temp;
						}
						else {
							t = board.sowSeeds(Integer.parseInt(move));
						}
						if (t == 1) {
							player_2.setTurn(true);
							player_1.setTurn(false);
						}
						else {
							player_2.setTurn(false);
							player_1.setTurn(true);
						}
					}
				}
			}
		}
		if (score1 > score2) {
			return ai;
		}
		else if (score2 > score1) {
			return ai2;
		}
		else {
			AI tie = new AI();
			return tie;
		}
	}
	
	public static void main(String args[]) throws IOException {
		try {
			AIServer s = new AIServer();
			//System.out.println("Server started");
			while (!s.getStopped()) {
				while (s.getSocket().isConnected()) {
					//System.out.println("Connected");
					PrintStream p_stream = new PrintStream(s.getSocket().getOutputStream());
					s.setMessage(wel);
					p_stream.println(s.getMessage());
					//System.out.println("Welcomed");
					s.setMessage("INFO" + s.getGameConfig());
					p_stream.println(s.getMessage());
					//System.out.println("Informed");
					//System.out.println(s.getSocketScan().nextLine());
					if (s.getSocketScan().nextLine() == red) {
						s.setMessage(beg);
						p_stream.println(s.getMessage());
						//GameManager game_manager = new GameManager(s.houses, s.seeds, s.timer, s.turn_num, s.set_rand, s.random, 'm');
						boolean game_over = false;
						int score1 = 0;
						int score2 = 0;
						boolean player1_turn;
						if (s.turn == 'F') {
							player1_turn = true;
						}
						else {
							player1_turn = false;
						}
						String move;
						
						while(!game_over) {
							if ((score1 + score2) == (s.seeds*s.houses)) {
								game_over = true;
								break;
							}
							else {
								if (player1_turn) {
									move = s.getSocketScan().nextLine();
									player1_turn = false;
								}
								else {
									move = s.getSocketScan().nextLine();
									player1_turn = true;
								}
							}
						}
						if (score1 > score2) {
							System.out.println("AI_1 won!");
						}
						else if (score2 > score1) {
							System.out.println("AI_2 won!");
						}
						else {
							System.out.println("It's a tie!");
						}	
					}
					/*if (s.getSocketScan().hasNext()) {
						if (s.getSocketScan().nextLine() == again) {
							s.setStopped(false);
						}
					}
					else {
						s.setStopped(true);
					}*/
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