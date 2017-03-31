import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.Queue;
import java.awt.event.ActionEvent;

public class KalahGUI extends JFrame {
	public int seedsNum;
	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldSeeds;
	private String houses_per;
	private String seeds_per;
	private static final String default_house = "6";
	private static final String default_seeds = "4";
	private JTextField textFieldHouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KalahGUI window = new KalahGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KalahGUI() {
		initialize();
		System.out.println("dsf");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frame = new JFrame("Kalah Game");
		setTitle("Kalah Game");
		// frame.setBounds(100, 100, 508, 389);
		setBounds(100, 100, 508, 389);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(91, 11, 311, 31);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(25, 50));
		// frame.getContentPane().add(panel);
		getContentPane().add(panel);

		JLabel welcome = new JLabel("Welcome to Kalah");
		welcome.setForeground(Color.BLUE);
		welcome.setFont(new Font("Tamaho", Font.PLAIN, 23));
		panel.add(welcome);
		panel.setPreferredSize(new Dimension(300, 50));

		JLabel username = new JLabel("Enter username");
		username.setBounds(10, 61, 116, 22);
		// frame.getContentPane().add(username);
		getContentPane().add(username);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(112, 63, 145, 20);
		// frame.getContentPane().add(textFieldUsername);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		// save username for player constructor

		// save player number for player constructor

		/*
		 * JButton btnSaveUsername = new JButton("Save");
		 * btnSaveUsername.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { btnSaveUsername.setText("Saved"); }
		 * }); btnSaveUsername.setBounds(267, 61, 89, 23);
		 * frame.getContentPane().add(btnSaveUsername);
		 */

		JLabel seedsNum = new JLabel("Number of seeds");
		seedsNum.setBounds(10, 103, 116, 36);
		// frame.getContentPane().add(seedsNum);
		getContentPane().add(seedsNum);

		textFieldSeeds = new JTextField();
		textFieldSeeds.setBounds(112, 111, 108, 20);
		// frame.getContentPane().add(textFieldSeeds);
		getContentPane().add(textFieldSeeds);
		textFieldSeeds.setColumns(10);

		JLabel houseNum = new JLabel("Number of houses");
		houseNum.setBounds(10, 153, 108, 14);
		// frame.getContentPane().add(houseNum);
		getContentPane().add(houseNum);

		textFieldHouse = new JTextField();
		textFieldHouse.setBounds(112, 150, 108, 20);
		// frame.getContentPane().add(textFieldHouse);
		getContentPane().add(textFieldHouse);
		textFieldHouse.setColumns(10);

		JLabel difficulty = new JLabel("Difficulty");
		difficulty.setBounds(10, 194, 75, 22);
		// frame.getContentPane().add(difficulty);
		getContentPane().add(difficulty);
		// difficulty button
		JCheckBox checkBox1 = new JCheckBox("Easy");
		JCheckBox checkBox2 = new JCheckBox("Medium");
		JCheckBox checkBox3 = new JCheckBox("Hard");
		ButtonGroup group = new ButtonGroup();
		group.add(checkBox1);
		group.add(checkBox2);
		group.add(checkBox3);
		checkBox1.setBounds(112, 194, 75, 22);
		// frame.getContentPane().add(checkBox1);
		getContentPane().add(checkBox1);
		checkBox2.setBounds(112, 230, 90, 22);
		// frame.getContentPane().add(checkBox2);
		getContentPane().add(checkBox2);
		checkBox3.setBounds(112, 254, 129, 36);
		// frame.getContentPane().add(checkBox3);
		getContentPane().add(checkBox3);

		// next button
		JButton btnNext = new JButton("Next >");
		btnNext.addActionListener(new ActionListener() {
			// go to the next frame
			public void actionPerformed(ActionEvent arg0) {
				String username = textFieldUsername.getText();
				seeds_per = textFieldSeeds.getText();
				houses_per = textFieldHouse.getText();
				if (username.equals("") || seeds_per.equals("") || group.getSelection() == null
						|| houses_per.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all the blank and select difficulty ");
				} else {
					int seedsTemp = Integer.parseInt(seeds_per);
					int houseTemp = Integer.parseInt(houses_per);
					if (seedsTemp > 10 || seedsTemp < 1 || houseTemp > 9 || houseTemp < 4) {
						JOptionPane.showMessageDialog(null, "Please enter a Number from 1 to 10");
					} else {

						// frame.dispose();
						dispose();
						Game game = new Game(houses_per, seeds_per);
						game.setVisible(true);
					}
				}
			}
		});
		btnNext.setBounds(351, 300, 89, 23);
		// frame.getContentPane().add(btnNext);
		getContentPane().add(btnNext);

		/*
		 * // image JLabel lblImage = new JLabel(" "); Image img = new
		 * ImageIcon(this.getClass().getResource("welcome-icon.png")).getImage()
		 * ; lblImage.setIcon(new ImageIcon(img)); lblImage.setBounds(275, 53,
		 * 166, 256); frame.getContentPane().add(lblImage);
		 */
	}
}
