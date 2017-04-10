import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//not sure where to place
public class NewServer {
	private String message;
	private static ServerSocket listener;
	private static Socket server_socket;
	private static Scanner socket_scanner;
	
	private static boolean closed;
	private boolean stopped;
	boolean ai;
	
	private Timer timer;
	private long timer_val;
	private long remaining_time;
	private boolean time_out = false;
	
	Player s_player;
	Player c_player;
	private String game_config;
	Board b;
	Game game;
	private int houses;
	private int seeds;
	private int server_turn;
	private int cur_turn = 1;
	boolean s_turn;
	private int[] random;
	String file_name = "game2.txt";

	
	private static final String wel = "WELCOME";
	private static final String red = "READY";
	private static final String beg = "BEGIN";
	private static final String ok = "OK";
	private static final String ill = "ILLEGAL";
	private static final String time = "TIME";
	private static final String loss = "LOSER";
	private static final String win = "WINNER";
	private static final String tie = "TIE";
	
	public NewServer() throws IOException {
		parseFile();
		System.out.println("parse done");
		listener = new ServerSocket(4444); //getting an error because port number is taken on this computer
		server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
													// the server listens on, and another thread where is reads/writes
		socket_scanner = new Scanner(server_socket.getInputStream());
		closed = false;
		stopped = false;
		System.out.println("init done");
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
			File file = new File(this.file_name);
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
	
	public Board setGameInfo(String new_info) {
		String info = new_info;
		game_config = info;
		
		houses = Integer.parseInt(info);
		info = info.substring(2);
		
		seeds = Integer.parseInt(info);
		info = info.substring(2);
		
		timer_val = Long.parseLong(info);
		while (Character.isDigit(info.charAt(0)) == true) {
			info = info.substring(1);
		}
		info = info.substring(1);
		char c = info.charAt(0);
		switch (c) {
			case 'F':
				server_turn = 1;
				break;
			case 'S':
				server_turn = 0;
				break;
			default:
				server_turn = 1;
		}
		info = info.substring(1);
		c = info.charAt(0);
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
		
		if (random.length > 0) {
			return new Board(houses, random);
		}
		else {
			return new Board(houses, seeds);
		}
	}
	
	public void startTimer() {
		remaining_time = timer_val;
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remaining_time == 0) {
					timer.stop();
					setTimeOut();
					//JOptionPane.showMessageDialog(null, "Out of time!");
				} else {
					remaining_time--;
				}
			}
		});
		timer.start();
	}
	
	public void endTimer() {
		timer.stop();
	}
	
	public void setTimeOut() {
		time_out = true;
	}
	
	public boolean getTimeOut() {
		return time_out;
	}
	
	public boolean isValidMoveNorth(int location) {
		if (location < 0 || location > this.houses) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean isValidMoveSouth(int location) {
		if (location < 0 || location < this.houses || location > this.houses*2) {
			return false;
		}
		else {
			return true;
		}

	}
	
	public int[] parseMoves(String str) {
		String[] splited = str.split("\\s+");
		int[] moves = new int[splited.length];
		for (int i = 0; i < splited.length; i++) {
			moves[i] = Integer.parseInt(splited[i]);
		}
		return moves;
	}

	public static void main(String args[]) throws IOException {
		try {
			NewServer s = new NewServer();
			
			while (s.getSocket().isConnected()) {
				//ask what kind of server this is
				int result = JOptionPane.showConfirmDialog(null, 
						   "Is this an AI running server?",null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					s.ai = true;
				} 
				else {
					s.ai = false;
				}
				PrintStream p_stream = new PrintStream(s.getSocket().getOutputStream());
				s.setMessage(wel);
				p_stream.println(s.getMessage());
				s.setMessage("INFO" + s.getGameConfig());
				p_stream.println(s.getMessage());
				s.b = s.setGameInfo(s.getGameConfig()); 
				if (s.getSocketScan().nextLine() == red) {
					s.setMessage(beg);
					p_stream.println(s.getMessage());
					//GameManager game_manager = new GameManager(s.houses, s.seeds, s.timer, s.turn_num, s.set_rand, s.random, 'm');
					boolean game_over = false;
					String move = "";
					int t = 0;
					
					while(!game_over) {
						if ((s.s_player.getScore() + s.c_player.getScore()) == (s.seeds*s.houses)) {
							game_over = true;
							break;
						}
						else {
							//server turn
							if (s.cur_turn%2 == s.server_turn) {
								s.s_turn = true;
								while (s.s_turn == true) {
									s.startTimer();
									if (s.ai) {
										// *****
										//get AI move
									}
									else {
										// *****
										//get player move from GUI
									}
									s.endTimer();

									//check to see if our move was in time
									if (s.getTimeOut() == true) {
										s.setMessage(time);
										p_stream.println(s.getMessage());
										s.setMessage(loss);
										p_stream.println(s.getMessage());
										game_over = true;
										s.s_turn = false;
									}
									//check to see if our move was valid
									else if (s.isValidMoveSouth(Integer.parseInt(move)) == false) {
										s.setMessage(ill);
										p_stream.println(s.getMessage());
										s.setMessage(loss);
										p_stream.println(s.getMessage());
										game_over = true;
										s.s_turn = false;
									}
									else {
										s.setMessage(ok);
										p_stream.println(s.getMessage());
										if (s.cur_turn == 1 && move == "P") {
											// *****
											//s.b.flipBoard();
											//swap players
											Player temp;
											temp = s.c_player;
											s.s_player = s.c_player;
											s.s_player = temp;
											s.s_turn = false;
										}
										// *****
										//t = s.b.sowSeeds(Integer.parseInt(move));
										//push our move back into the message queue
										s.setMessage(s.getMessage() + " " + move);
										//check to see if it is still s_turn
										if (t == 1) {
											s.s_turn = true;
										}
										else {
											s.s_turn = false;
										}
									}
								} // while server turn
							} // if server turn
							//client turn
							else {
								s.s_turn = false;
								while (s.s_turn == false) {
									s.startTimer();
									move = s.getSocketScan().nextLine();
									s.endTimer();
									//check to see if their move was in time
									//check to see if their moves are valid
									int[] client_moves = s.parseMoves(move);
									for (int i : client_moves) {
										 if (s.isValidMoveNorth(i) == false) {
												s.setMessage(ill);
												p_stream.println(s.getMessage());
												s.setMessage(loss);
												p_stream.println(s.getMessage());
												game_over = true;
												s.s_turn = false;
											}
									}
									if (s.getTimeOut() == true) {
										s.setMessage(time);
										p_stream.println(s.getMessage());
										s.setMessage(loss);
										p_stream.println(s.getMessage());
										game_over = true;
										s.s_turn = false;
									}
									else {
										s.setMessage(ok);
										p_stream.println(s.getMessage());
										if (s.cur_turn == 1 && move == "P") {
											// *****
											//s.b.flipBoard();
											//swap players
											Player temp;
											temp = s.c_player;
											s.s_player = s.c_player;
											s.s_player = temp;
											s.s_turn = true;
										}
										else {
											// *****
											//t = s.b.sowSeeds(Integer.parseInt(move));
											s.s_turn = true;
										}
									}
								} // while client turn
							} // client turn
						} // turns
					} // game over
					if (s.s_player.getScore() > s.c_player.getScore()) {
						System.out.println("Server win");
						s.setMessage(loss);
						p_stream.println(s.getMessage());
					}
					else if (s.s_player.getScore() < s.c_player.getScore()) {
						System.out.println("Client win");
						s.setMessage(win);
						p_stream.println(s.getMessage());
					}
					else {
						System.out.println("It's a tie!");
						s.setMessage(tie);
						p_stream.println(s.getMessage());
					}
				} // end ready
			} // end connected
		} // end try
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
	} // end main
	
} // end NewServer