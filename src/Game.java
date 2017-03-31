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
import java.awt.Button;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
//import java.awt.event.InputMethodListener;
//import java.awt.event.InputMethodEvent;

public class Game extends JFrame {

	private JPanel contentPane;
	private JPanel content;
	private JFrame frame;
	// private JButton[][] buttons;
	private Vector<JButton> buttons_south = new Vector<JButton>();
	private Vector<JButton> buttons_north = new Vector<JButton>();
	private Queue<String> moves;
	private boolean moveleftToRight;
	static Board board;
	private JLabel store1;
	private JLabel store2;

	/**
	 * sLaunch the application.
	 */

	public static void main(String[] args) {
		// Game a = new Game("4", "5");
		// a.setVisible(true);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game("6", "4");
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
		int house = Integer.parseInt(houses_per);
		int seeds = Integer.parseInt(seeds_per);
		board = new Board(house, seeds);
		setTitle("Kalah Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		JPanel storeLabel1 = new JPanel();
		storeLabel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		storeLabel1.setForeground(Color.BLUE);
		storeLabel1.setBackground(Color.WHITE);

		store1 = new JLabel("Store 1(0)");
		store1.putClientProperty("ID", -1);
		storeLabel1.add(store1);
		panelWest.add(storeLabel1);

		JPanel panelEast = new JPanel();
		contentPane.add(panelEast, BorderLayout.EAST);
		JPanel storeLabel2 = new JPanel();
		storeLabel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		storeLabel2.setForeground(Color.BLUE);
		storeLabel2.setBackground(Color.WHITE);
		store2 = new JLabel("Store 2(0)");
		store2.putClientProperty("ID", -2);
		storeLabel2.add(store2);
		panelEast.add(storeLabel2);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setPreferredSize(new Dimension(50, 50));

		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setPreferredSize(new Dimension(50, 50));

		int nhouse_per = Integer.parseInt(houses_per);
		panelSouth.setLayout(new GridLayout(1, nhouse_per));
		panelNorth.setLayout(new GridLayout(1, nhouse_per));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2));
		JPanel panel_button = new JPanel();
		JPanel panel_label = new JPanel();
		JButton reset = new JButton("New Game");
		reset.setSize(10, 10);
		reset.putClientProperty("ID", -3);
		
		panel_button.add(reset);
		JLabel time = new JLabel("Time");
		panel_button.add(time);
		panel.add(panel_button);
		panel.add(panel_label);
		ButtonsListener button_listener = new ButtonsListener();
		reset.addActionListener(button_listener);
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
		// set variable number of buttons
		// make buttons and store in matrix
		/*
		 * for (int i = 0; i < 2; i++) { for (int j = 0; j <
		 * Integer.parseInt(houses_per)*2; i++) { buttons[i][j] = new
		 * JButton("j"); } }
		 * 
		 * //with one action listener to rule them all ActionListener a = new
		 * ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent evt) { if (evt.getSource() ==
		 * buttons[0][0]) {
		 * 
		 * } // etc // common handling } };
		 * 
		 * for (int i = 0; i < 2; ++i){ for (int j = 0; j <
		 * Integer.parseInt(houses_per); ++j) {
		 * buttons[i][j].addActionListener(a); } }
		 * 
		 * /* add image to each of the buttons, if necessary (perhaps color code
		 * them for valid/invalid?)
		 *
		 * for(int i=0;i<30;i++){ buttons[i] = new JButton("label"+ i);
		 * buttons[i].setBackground(Color.white); if (i < 10) { if (i%2 == 0) {
		 * buttons[i].setIcon(piece2); } else { buttons[i].setIcon(piece1); } }
		 * panel.add(buttons[i]); }
		 */

	}

	private class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the click button ID
			// System.out.print(e.getActionCommand());
			JButton click_button = (JButton) e.getSource();
			int click_button_ID = (Integer) click_button.getClientProperty("ID");
			// System.out.println(click_button.getLabel());
			if (click_button_ID == -3) {
				dispose();
				System.out.println("HEY");
				KalahGUI newKalahGUI = new KalahGUI();
				newKalahGUI.setVisible(true);
			//	System.out.println("HEY");
			} else {
				// Set searching queue in counterclockwise way
				// System.out.println(b.getClientProperty("ID"));
				Queue searchingQ = new LinkedList();
				for (int i = buttons_north.size() - 1; i >= 0; i--) {
					// System.out.println(buttons_north.elementAt(i).getClientProperty("ID"));
					searchingQ.add(buttons_north.elementAt(i));
				}
				// System.out.println(store1.getClientProperty("ID"));
				JButton store1_fakebutton = new JButton();
				store1_fakebutton.putClientProperty("ID", -1);
				searchingQ.add(store1_fakebutton);
				for (int i = 0; i < buttons_south.size(); i++) {
					// System.out.println(buttons_south.elementAt(i).getClientProperty("ID"));
					searchingQ.add(buttons_south.elementAt(i));
				}
				// System.out.println(store2.getClientProperty("ID"));
				JButton store2_fakebutton = new JButton();
				store2_fakebutton.putClientProperty("ID", -2);
				searchingQ.add(store2_fakebutton);

				// Search click button in queue
				int seed_amount;
				while (true) {
					JButton top = (JButton) searchingQ.peek();
					if ((Integer) top.getClientProperty("ID") == click_button_ID) {
						seed_amount = Integer.parseInt(top.getText());
						top.setText("0");
						// System.out.println("Seed amount:" + seed_amount+ ",
						// ID:" + click_button_ID);
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
					if ((Integer) top.getClientProperty("ID") < 0) {
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
	}
}
