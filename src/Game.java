import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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

	// public getMoves() {

	// }

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
		JLabel store1 = new JLabel("Store 1");
		storeLabel1.add(store1);
		panelWest.add(storeLabel1);

		JPanel panelEast = new JPanel();
		contentPane.add(panelEast, BorderLayout.EAST);
		JPanel storeLabel2 = new JPanel();
		storeLabel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		storeLabel2.setForeground(Color.BLUE);
		storeLabel2.setBackground(Color.WHITE);
		JLabel store2 = new JLabel("Store 2");
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
		JButton reset = new JButton("Reset");
		reset.setSize(10, 10);
		panel_button.add(reset);
		JLabel time = new JLabel("Time");
		panel_button.add(time);
		panel.add(panel_button);
		panel.add(panel_label);

		// panel.add(new JButton("A"));
		// panel.setSize(10,10);
		// panel.add(new JLabel("Bdsfsdf"));
		// panel.setSize(10,10);
		ButtonsListener button_listener = new ButtonsListener();
		for (int i = 0; i < nhouse_per; i++) {
			JButton button_south = new JButton(seeds_per);
			JButton button_north = new JButton(seeds_per);
			button_south.addActionListener(button_listener);
			button_north.addActionListener(button_listener);

			buttons_south.add(button_south);
			buttons_north.add(button_north);

			panelSouth.add(button_south);
			panelNorth.add(button_north);

		}
		// contentPane.setLayout(new GridLayout(rows, cols));
		// contentPane.add(new JButton("A"));
		// contentPane.add(new JButton("B"));

		// moves = new Queue<String>;

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
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(Color.WHITE);
		btnReset.setForeground(Color.BLUE);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblTime = new JLabel("Time: ");
		lblTime.setBackground(Color.WHITE);
		lblTime.setForeground(Color.BLUE);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblHouse1 = new JLabel("House1");
		/*
		 * JLabel lblImage = new JLabel(" "); Image img = new
		 * ImageIcon(this.getClass().getResource("circle-icon.png")).getImage();
		 * lblImage.setIcon(new ImageIcon(img)); lblImage.setBounds(53, 53, 166,
		 * 256); frame.getContentPane().add(lblImage);
		 */
		JLabel lblHouse2 = new JLabel("House2");
	
	}
		private class ButtonsListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(e.getActionCommand());
			}
		}
	
}
