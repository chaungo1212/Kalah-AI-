import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import java.awt.Button;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class Game extends JFrame {
	private Vector<JButton> buttons_south = new Vector<JButton>();//store all the buttons in the north
	private Vector<JButton> buttons_north = new Vector<JButton>();//store all the buttons in the south
	static Board board;
	private JLabel store1;
	private JLabel store2;
	private int nhouse_per; // house amount per side
	private int nseed_per; // seed amount per house
	private int remaining_time;
	private Timer timer;
	private JPanel contentPane;
	private JPanel content;
	private JFrame frame;
	private JLabel time_label;

	/**
	 * sLaunch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game("6", "4");//set this default so that while doing it is easier to run
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
	public Game(String houses_per, String seeds_per) {
		nhouse_per = Integer.parseInt(houses_per);
		nseed_per = Integer.parseInt(seeds_per);
		board = new Board(nhouse_per * 2 + 2, nseed_per);
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
		JPanel store_label1 = new JPanel();
		store_label1.setBorder(new LineBorder(new Color(0, 0, 0)));
		store_label1.setPreferredSize(new Dimension(70, 50));
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
		JPanel store_label2 = new JPanel();
		store_label2.setBorder(new LineBorder(new Color(0, 0, 0)));
		store_label2.setPreferredSize(new Dimension(90, 50));
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

		// nhouse_per = Integer.parseInt(houses_per);
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
		// Timer setting allow 10 seconds for user to input
		remaining_time = 50;
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
	}

	private class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Refresh remaining_time
			remaining_time = 50;
			timer.stop();
			time_label.setText(Integer.toString(remaining_time));
			timer.start();

			// Get the click button ID
			// System.out.print(e.getActionCommand());
			JButton click_button = (JButton) e.getSource();
			int click_button_ID = (Integer) click_button.getClientProperty("ID");
			// System.out.println(click_button.getLabel());
			if (click_button_ID == -3) {
				dispose();
				KalahGUI newKalahGUI = new KalahGUI();
				newKalahGUI.setVisible(true);
			} else {
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
				// Send seed to the following house from top of queue
				for (int i = 0; i < seed_amount; i++) {
					JButton top = (JButton) searchingQ.peek();
					if ((Integer) top.getClientProperty("ID") < 0) { // Seed to
																		// store
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
			}
			/* Check either north side or south side has all "0"
			 * Then game stops and sends one side's all seed to either store 1
			 * and store 2.
			 */
			boolean win_store1 = true;
			int nseeds_all_south = 0;
			//north side check all houses if it is equal to 0
			for (int i = 0; i < buttons_south.size(); i++) {
				if (Integer.parseInt(buttons_south.elementAt(i).getText()) != 0) {
					win_store1 = false;
				}
				nseeds_all_south = nseeds_all_south + Integer.parseInt(buttons_south.elementAt(i).getText());
			}
			boolean win_store2 = true;
			int nseeds_all_north = 0;
			//north side check all houses if it is equal to 0
			for (int i = 0; i < buttons_north.size(); i++) {
				if (Integer.parseInt(buttons_north.elementAt(i).getText()) != 0) {
					win_store2 = false;
				}
				nseeds_all_north = nseeds_all_north + Integer.parseInt(buttons_north.elementAt(i).getText());
			}
			int nseed_store1 = Integer.parseInt(store1.getText().substring(8, store1.getText().length() - 1));
			int nseed_store2 = Integer.parseInt(store2.getText().substring(8, store2.getText().length() - 1));
			if (win_store1) {//seed in store 1
				nseed_store1 = nseed_store1 + nseeds_all_north;
			}
			if (win_store2) {//seed in store 2
				nseed_store2 = nseed_store2 + nseeds_all_south;
			}
			if (win_store1 || win_store2) {
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
				} else if (nseed_store1 == nseed_store2) {
					JOptionPane.showMessageDialog(null, "Player 1 and Player 2 are tie");
				} else { // nseed_store1 < nseed_store2
					JOptionPane.showMessageDialog(null, "Player 2 wins");
				}
			}
		}
	}
}
