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

public class ClientGame extends JFrame {
	private Vector<JButton> buttons_south = new Vector<JButton>();//store all the buttons in the north
	private Vector<JButton> buttons_north = new Vector<JButton>();//store all the buttons in the south
	private JLabel store1;
	private JPanel store_label1;
	private JLabel store2;
	private JPanel store_label2;
	private int nhouse_per; // house amount per side
	private int nseed_per; // seed amount per house
	private int remaining_time;
	private Timer timer;
	private JPanel contentPane;
	private JPanel content;
	private JFrame frame;
	private JLabel time_label;
	private boolean finished;
	private GameManager judge;
	public Vector<Integer> AI_move_indices;
	public Vector<Integer> op_move_indices;
	
	String message;
	private static Socket client_socket;
	private static Scanner client_scanner;
	private static PrintStream p_stream;
	
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

	/**
	 * sLaunch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client_socket = new Socket("127.0.0.1", 4444);
					client_scanner = new Scanner(client_socket.getInputStream());
					p_stream = new PrintStream(client_socket.getOutputStream());
					ClientGame frame = new ClientGame("6", "4"); //set this default so that while doing it is easier to run
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		String moves = client_scanner.nextLine();
		op_move_indices = parseMoves(moves);
	}

	/**
	 * Create the frame.
	 */

	public ClientGame(String houses_per, String seeds_per) {
		finished = false;
		judge = new GameManager();
		
		while (finished == false) {
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
					ClientGame frame = new ClientGame("6","4");
					frame.setVisible(true);
					
				}
			});
			button_reset.setForeground(Color.YELLOW);
			button_reset.setBackground(Color.DARK_GRAY);
			button_reset.setSize(10, 10);
			panel_label.add(button_reset);
	
			for (int i = 0; i < nhouse_per; i++) {
				JButton button_south = new JButton(seeds_per);
				JButton button_north = new JButton(seeds_per);
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
			
				if(finished == false){
					// Refresh remaining_time
					remaining_time = 60;
					timer.stop();
					time_label.setText(Integer.toString(remaining_time));
					timer.start();
					
					// AI working part
					System.out.println("AI's turn.");
					if(judge.win_store1 == false && judge.win_store2 == false){
						Board currentboard = new Board(nhouse_per, nseed_per);
						for(int i = 0; i < buttons_north.size(); i++)
							currentboard.north_house.get(i).nseed = Integer.parseInt(buttons_north.get(i).getText()); 
						for(int i = 0; i < buttons_south.size(); i++)
							currentboard.south_house.get(i).nseed = Integer.parseInt(buttons_south.get(i).getText()); 
						currentboard.store1.nseed = Integer.parseInt(store1.getText().substring(8, store1.getText().length() - 1));
						currentboard.store2.nseed = Integer.parseInt(store2.getText().substring(8, store2.getText().length() - 1));
						AI smart = new AI();
						Board nextboard = smart.search_move(currentboard);
						AI_move_indices = new Vector<Integer>(); // All move indices for AI
						AI_move_indices.add(nextboard.move_index);
						while(true){
							if(nextboard.free_turn == true){
								nextboard = smart.search_move(nextboard);
								AI_move_indices.add(nextboard.move_index);
							}
							else{
								break;
							}
						}
						System.out.print("AI's move indices(including all free turn):");
						for(int i = 0; i < AI_move_indices.size(); i++)
							System.out.print(Integer.toString(AI_move_indices.get(i)) + ",");
						System.out.println();
						
						// Put back the nextboard onto GUI and let player know what AI did
						for(int i = 0; i < nextboard.north_house.size(); i++)
							buttons_north.get(i).setText(Integer.toString(nextboard.north_house.get(i).nseed));
						for(int i = 0; i < nextboard.south_house.size(); i++)
							buttons_south.get(i).setText(Integer.toString(nextboard.south_house.get(i).nseed));
						store1.setText("Store 1(" + Integer.toString(nextboard.store1.nseed) + ")");
						store2.setText("Store 2(" + Integer.toString(nextboard.store2.nseed) + ")");
	
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
					}
				}
				
			}
		return;
	}
}
