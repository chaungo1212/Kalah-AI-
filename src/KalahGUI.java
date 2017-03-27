import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KalahGUI {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldSeeds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KalahGUI window = new KalahGUI();
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Kalah Game");
		frame.setBounds(100, 100, 460, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(45, 11, 311, 31);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(25, 50));
		frame.getContentPane().add(panel);
		
		JLabel welcome = new JLabel("Welcome to Kalah");
		welcome.setForeground(Color.BLUE);
		welcome.setFont(new Font("Tamaho", Font.PLAIN, 23));
		panel.add(welcome);
		panel.setPreferredSize(new Dimension(300, 50));
		
		JLabel username = new JLabel("Enter username");
		username.setBounds(10, 61, 116, 22);
		frame.getContentPane().add(username);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(112, 61, 145, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		/*
		 * JButton btnSaveUsername = new JButton("Save");
		 * btnSaveUsername.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { btnSaveUsername.setText("Saved"); }
		 * }); btnSaveUsername.setBounds(267, 61, 89, 23);
		 * frame.getContentPane().add(btnSaveUsername);
		 */
		
		JLabel seedsNum = new JLabel("Number of seeds");
		seedsNum.setBounds(10, 94, 116, 36);
		frame.getContentPane().add(seedsNum);
		
		textFieldSeeds = new JTextField();
		textFieldSeeds.setBounds(112, 102, 108, 20);
		frame.getContentPane().add(textFieldSeeds);
		textFieldSeeds.setColumns(10);

		JLabel difficulty = new JLabel("Difficulty");
		difficulty.setBounds(10, 141, 75, 22);
		frame.getContentPane().add(difficulty);
		// difficulty button
		JCheckBox checkBox1 = new JCheckBox("Easy");
		JCheckBox checkBox2 = new JCheckBox("Medium");
		JCheckBox checkBox3 = new JCheckBox("Hard");
		ButtonGroup group = new ButtonGroup();
		group.add(checkBox1);
		group.add(checkBox2);
		group.add(checkBox3);
		checkBox1.setBounds(112, 141, 75, 22);
		frame.getContentPane().add(checkBox1);
		checkBox2.setBounds(112, 168, 90, 22);
		frame.getContentPane().add(checkBox2);
		checkBox3.setBounds(112, 187, 129, 36);
		frame.getContentPane().add(checkBox3);

		// save seeds button
		JButton btnSaveSeeds = new JButton("Save");
		btnSaveSeeds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seedsNum = 0;
				try {
					int temp = Integer.parseInt(textFieldSeeds.getText());
					if (temp < 10 && temp > 0) {
						seedsNum = temp;
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter a Number from 1 to 10");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Please enter a Number from 1 to 10");
				}
				btnSaveSeeds.setText("Saved");

			}
		});
		btnSaveSeeds.setBounds(267, 101, 89, 23);
		frame.getContentPane().add(btnSaveSeeds);

		// next button
		JButton btnNext = new JButton("Next >");
		btnNext.addActionListener(new ActionListener() {
			//go to the next frame
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Game game = new Game();
				game.setVisible(true);
			}
		});
		btnNext.setBounds(305, 233, 89, 23);
		frame.getContentPane().add(btnNext);

		// image
	/*	JLabel lblImage = new JLabel(" ");
		Image img = new ImageIcon(this.getClass().getResource("welcome-icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		lblImage.setBounds(275, 53, 166, 256);
		frame.getContentPane().add(lblImage);*/

	}
	
}
