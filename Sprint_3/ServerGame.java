import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class ServerGame extends JFrame {
	private Vector<JButton> buttons_south = new Vector<JButton>();//store all the buttons in the north
	private Vector<JButton> buttons_north = new Vector<JButton>();//store all the buttons in the south
	private JLabel store1;
	private JPanel store_label1;
	private JLabel store2;
	private JPanel store_label2;
	private int nhouse_per; // house amount per side
	private int nseed_per; // seed amount per house
	private JPanel contentPane;
	private JPanel content;
	private JFrame frame;
	private JLabel time_label;
	private boolean finished;
	private GameManager judge;
	public Vector<Integer> Player_move_indices;
	public Vector<Integer> op_move_indices;
	
	
	private String message;
	private static ServerSocket listener;
	private static Socket server_socket;
	private static Scanner socket_scanner;
	private static PrintStream p_stream; 
	
	private static boolean closed;
	boolean ai;
	
	private Timer timer;
	private int timer_val;
	private int remaining_time;
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

	/**
	 * sLaunch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listener = new ServerSocket(4444); //getting an error because port number is taken on this computer
					server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
																// the server listens on, and another thread where is reads/writes
					socket_scanner = new Scanner(server_socket.getInputStream());
					closed = false;
					p_stream = new PrintStream(server_socket.getOutputStream());
					ServerGame frame = new ServerGame("6", "4"); //set this default so that while doing it is easier to run
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Socket getSocket() {
		return server_socket;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public void giveMoves(Vector<Integer> moves) {
		//compile all moves into string
		String str = "";
		for (Integer i : moves) {
			str = str + " " + Integer.toString(i);
		}
		//set message
		setMessage(str);
		//send message
		p_stream.println(getMessage());
	}
	
	public Vector<Integer> parseMoves(String str) {
		String[] splited = str.split("\\s+");
		Vector<Integer> moves = new Vector<Integer>();
		for (int i = 0; i < splited.length; i++) {
			moves.add(Integer.parseInt(splited[i]));
		}
		return moves;
	}
	
	public void getMoves() {
		//wait for moves to arrive
		String moves = socket_scanner.nextLine();
		op_move_indices = parseMoves(moves);
	}

	public ServerGame(String houses_per, String seeds_per) {
		//while (getSocket().isConnected()) {
			finished = false;
			nhouse_per = Integer.parseInt(houses_per);
			nseed_per = Integer.parseInt(seeds_per);
			setTitle("Kalah Game");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 631, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
	
			/************************/
			/******* West Panel *******/
			/************************/
			JPanel panelWest = new JPanel();
			contentPane.add(panelWest, BorderLayout.WEST);
			store_label1 = new JPanel();
			store_label1.setBorder(new LineBorder(new Color(0, 0, 0)));
			store_label1.setPreferredSize(new Dimension(100, 50));
			store_label1.setForeground(Color.YELLOW);
			store_label1.setBackground(Color.DARK_GRAY);
			store1 = new JLabel("Store 1(0)");
			store1.setForeground(Color.YELLOW);
			store1.putClientProperty("ID", -1);
			store1.setFont(new Font("Tamaho", Font.BOLD, 15));
			store_label1.add(store1);
			panelWest.add(store_label1);
	
			/************************/
			/******* East Panel *******/
			/************************/
			JPanel panelEast = new JPanel();
			contentPane.add(panelEast, BorderLayout.EAST);
			store_label2 = new JPanel();
			store_label2.setBorder(new LineBorder(new Color(0, 0, 0)));
			store_label2.setPreferredSize(new Dimension(100, 50));
			store_label2.setForeground(Color.YELLOW);
			store_label2.setBackground(Color.DARK_GRAY);
			store2 = new JLabel("Store 2(0)");
			store2.setForeground(Color.YELLOW);
			store2.setFont(new Font("Tamaho", Font.BOLD, 15));
			store2.putClientProperty("ID", -2);
			store_label2.add(store2);
			panelEast.add(store_label2);
	
			/*************************/
			/******* North Panel *******/
			/*************************/
			JPanel panelNorth = new JPanel();
			panelNorth.setForeground(Color.YELLOW);
			panelNorth.setBackground(Color.WHITE);
			contentPane.add(panelNorth, BorderLayout.NORTH);
			panelNorth.setPreferredSize(new Dimension(90, 50));
	
			/************************/
			/****** South Panel *******/
			/************************/
			JPanel panelSouth = new JPanel();
			panelSouth.setForeground(Color.YELLOW);
			panelSouth.setBackground(Color.WHITE);
			contentPane.add(panelSouth, BorderLayout.SOUTH);
			panelSouth.setPreferredSize(new Dimension(50, 50));
			panelSouth.setLayout(new GridLayout(1, nhouse_per));
			panelNorth.setLayout(new GridLayout(1, nhouse_per));
	
			/************************/
			/****** Center Panel *******/
			/************************/
			// new game button and time label
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(1, 2));
			JPanel panel_button = new JPanel();
			JButton new_game = new JButton("New Game");
			new_game.setForeground(Color.YELLOW);
			new_game.setBackground(Color.DARK_GRAY);
			new_game.setSize(10, 10);
			new_game.putClientProperty("ID", -3);
			panel_button.add(new_game);
	
			JPanel panel_label = new JPanel();
			JLabel time = new JLabel("Time");
			time.setBackground(SystemColor.controlShadow);
			panel_button.add(time);
			panel.add(panel_button);
			time_label = new JLabel("");
			time_label.setForeground(Color.RED);
			time_label.setBackground(Color.WHITE);
			panel_button.add(time_label);
			panel.add(panel_label);
			
			JButton button_reset = new JButton("Reset");
			button_reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					timer.stop();
					dispose();
					ServerGame frame = new ServerGame("6","4");
					frame.setVisible(true);
					
				}
			});
			button_reset.setForeground(Color.YELLOW);
			button_reset.setBackground(Color.DARK_GRAY);
			button_reset.setSize(10, 10);
			panel_label.add(button_reset);
	
			ButtonsListener button_listener = new ButtonsListener();
			new_game.addActionListener(button_listener);
			for (int i = 0; i < nhouse_per; i++) {
				JButton button_south = new JButton(seeds_per);
				JButton button_north = new JButton(seeds_per);
				button_south.addActionListener(button_listener);
				button_north.addActionListener(button_listener);
				button_north.putClientProperty("ID", i);
				button_south.putClientProperty("ID", i + nhouse_per);
	
				buttons_south.add(button_south);
				buttons_north.add(button_north);
	
				panelSouth.add(button_south);
				panelNorth.add(button_north);
	
			}
			// Timer setting allow 60 seconds for user to input
			remaining_time = 60;
			time_label.setText(Integer.toString(remaining_time));
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (remaining_time == 0) {
						((Timer) e.getSource()).stop();
						JOptionPane.showMessageDialog(null, "Out of time!");
					} else {
						remaining_time--;
						time_label.setText(Integer.toString(remaining_time));
					}
				}
			});
			timer.start();
			//System.out.println("Your turn.");
		//}
	}

	private class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(finished == false){
				// Refresh remaining_time
				remaining_time = 60;
				timer.stop();
				time_label.setText(Integer.toString(remaining_time));
				timer.start();

				// Get the click button ID
				Player_move_indices = new Vector<Integer>();
				JButton click_button = (JButton) e.getSource();
				int click_button_ID = (Integer) click_button.getClientProperty("ID");
				Player_move_indices.add(click_button_ID);
				if (click_button_ID == -3) {
					dispose();
					KalahGUI newKalahGUI = new KalahGUI();
					newKalahGUI.setVisible(true);
				}
				else if(click_button_ID < nhouse_per){
					JOptionPane.showMessageDialog(null, "Player only can click south side buttons");
				}
				else if(Integer.parseInt(click_button.getText()) == 0){
					JOptionPane.showMessageDialog(null, "There is no seed in this house");
				}
				else {
					// Set searching queue in counterclockwise way
					Queue searchingQ = new LinkedList();
					for (int i = buttons_north.size() - 1; i >= 0; i--) {
						searchingQ.add(buttons_north.elementAt(i));
					}
					JButton store1_fakebutton = new JButton();
					store1_fakebutton.putClientProperty("ID", -1);
					searchingQ.add(store1_fakebutton);
					for (int i = 0; i < buttons_south.size(); i++) {
						searchingQ.add(buttons_south.elementAt(i));
					}
					JButton store2_fakebutton = new JButton();
					store2_fakebutton.putClientProperty("ID", -2);
					searchingQ.add(store2_fakebutton);

					// Search click button in queue
					// get the button then push it back to the end of the queue
					int seed_amount;
					while (true) {
						JButton top = (JButton) searchingQ.peek();
						if ((Integer) top.getClientProperty("ID") == click_button_ID) {
							seed_amount = Integer.parseInt(top.getText());
							top.setText("0");
							searchingQ.poll();
							searchingQ.add(top);
							break;
						} else {
							searchingQ.poll();
							searchingQ.add(top);
						}
					}
					boolean free_turn = false;
					// Send seed to the following house from top of queue
					for (int i = 0; i < seed_amount; i++) {
						JButton top = (JButton) searchingQ.peek();
						if ((Integer) top.getClientProperty("ID") < 0) { // Seed to store
							if ((Integer) top.getClientProperty("ID") == -1) {
								String store1text = store1.getText(); // store 1(?)
								int length = store1text.length();
								int nseed = Integer.parseInt(store1text.substring(8, length - 1));
								nseed = nseed + 1;
								store1text = "Store 1(" + Integer.toString(nseed) + ")";
								store1.setText(store1text);
								searchingQ.poll();
								searchingQ.add(top);
							}
							if ((Integer) top.getClientProperty("ID") == -2) {
								String store2text = store2.getText(); // store 2(?)
								int length = store2text.length();
								int nseed = Integer.parseInt(store2text.substring(8, length - 1));
								nseed = nseed + 1;
								store2text = "Store 2(" + Integer.toString(nseed) + ")";
								store2.setText(store2text);
								searchingQ.poll();
								searchingQ.add(top);
								if(i == seed_amount - 1){ // the last seed
									free_turn = true;
								}
							}
						} else { // Seed to house
							if (i == seed_amount - 1) { // the last seed
								int nseed = Integer.parseInt(top.getText());
								if (nseed == 0) { /* If it is an empty house =>
													 * The seed + the all seeds on opposite house will go to
													 * store1(north) or store2(south)
													 */
									int house_ID = (Integer) top.getClientProperty("ID");
									if (house_ID < nhouse_per) { // the house is in north
										// The amount of seed on the opposite house
										int nseed_opposite = Integer.parseInt(buttons_south.elementAt(house_ID).getText());
										nseed = 1 + nseed_opposite; // amount of seed for store 1
										int nseed_store1 = Integer
												.parseInt(store1.getText().substring(8, store1.getText().length() - 1));
										nseed_store1 = nseed_store1 + nseed;
										// Update the seed in store1 and empty the opposite house
										store1.setText("Store 1(" + Integer.toString(nseed_store1) + ")");
										buttons_south.elementAt(house_ID).setText("0");
									} else { // the house is in south
												// Use the ID to find the index in vector
										int house_index = house_ID - nhouse_per;
										int nseed_opposite = Integer.parseInt(buttons_north.elementAt(house_index).getText());
										nseed = 1 + nseed_opposite;
										int nseed_store2 = Integer.parseInt(store2.getText().substring(8, store2.getText().length() - 1));
										nseed_store2 = nseed_store2 + nseed;
										store2.setText("Store 2(" + Integer.toString(nseed_store2) + ")");
										buttons_north.elementAt(house_index).setText("0");
									}
								}
							} else {
								int nseed = Integer.parseInt(top.getText());
								nseed = nseed + 1;
								String new_seed = Integer.toString(nseed);
								top.setText(new_seed);
								searchingQ.poll();
								searchingQ.add(top);
							}
						}
					}
					/* Use GameManager to check either store1 or store2 wint
					 */
					judge = new GameManager();
					judge.checkwin(buttons_south, buttons_north, store1, store2);
					int nseed_store1 = Integer.parseInt(store1.getText().substring(8, store1.getText().length() - 1));
					int nseed_store2 = Integer.parseInt(store2.getText().substring(8, store2.getText().length() - 1));
					if (judge.win_store1) {//seed in store 1
						nseed_store1 = nseed_store1 + judge.nseeds_all_north;
					}
					if (judge.win_store2) {//seed in store 2
						nseed_store2 = nseed_store2 + judge.nseeds_all_south;
					}
					if (judge.win_store1 || judge.win_store2) {
						timer.stop();
						for (int i = 0; i < buttons_south.size(); i++) {
							buttons_south.elementAt(i).setText("0");
							buttons_north.elementAt(i).setText("0");
						}
						//get the seeds from each store then can compare
						store1.setText("Store 1(" + Integer.toString(nseed_store1) + ")");
						store2.setText("Store 2(" + Integer.toString(nseed_store2) + ")");
						if (nseed_store1 > nseed_store2) {
							JOptionPane.showMessageDialog(null, "Player 1 wins");
							finished = true;
						} else if (nseed_store1 == nseed_store2) {
							JOptionPane.showMessageDialog(null, "Player 1 and Player 2 are tie");
							finished = true;
						} else { // nseed_store1 < nseed_store2
							JOptionPane.showMessageDialog(null, "Player 2 wins");
							finished = true;
						}
					}
					giveMoves(Player_move_indices);
					getMoves();
				}
			}

			return;
		}
		
	}
}
