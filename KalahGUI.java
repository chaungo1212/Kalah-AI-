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
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;

public class Mancala {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mancala window = new Mancala();
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
	public Mancala() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Kalah Game");
		frame.setBounds(100, 100, 431, 306);
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
		welcome.setFont(new Font("Calisto MT", Font.PLAIN, 23));
		panel.add(welcome);
		panel.setPreferredSize(new Dimension(300, 50));
		
		JLabel username = new JLabel("Enter username");
		username.setBounds(10, 61, 90, 22);
		frame.getContentPane().add(username);
		
		JLabel seedsNum = new JLabel("Number of seeds");
		seedsNum.setBounds(10, 94, 116, 36);
		frame.getContentPane().add(seedsNum);
		
		JLabel difficulty = new JLabel("Difficulty");
		difficulty.setBounds(10, 141, 75, 22);
		frame.getContentPane().add(difficulty);
		
		textField = new JTextField();
		textField.setBounds(112, 61, 145, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 102, 108, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JCheckBox checkBox1 = new JCheckBox("Easy");
		JCheckBox checkBox2 = new JCheckBox("Medium");
		JCheckBox checkBox3 = new JCheckBox("Hard");
		ButtonGroup group=new ButtonGroup();
		group.add(checkBox1);
		group.add(checkBox2);
		group.add(checkBox3);
		checkBox1.setBounds(112, 141, 75, 22);
		frame.getContentPane().add(checkBox1);
		checkBox2.setBounds(112, 168, 90, 22);
		frame.getContentPane().add(checkBox2);
		checkBox3.setBounds(112, 187, 129, 36);
		frame.getContentPane().add(checkBox3);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(267, 61, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnNext = new JButton("Next >");
		btnNext.setBounds(305, 233, 89, 23);
		frame.getContentPane().add(btnNext);
	}
}