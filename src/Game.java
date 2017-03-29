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

import java.util.LinkedList;
import java.util.Queue;
//import java.awt.event.InputMethodListener;
//import java.awt.event.InputMethodEvent;

public class Game extends JFrame {

	private JPanel contentPane;
	private JPanel content;
	private JFrame frame;
	private Queue<String> moves;
	private boolean moveleftToRight;
	static Board board;

	/**
	 * sLaunch the application.
	 */

	public void makeMove(int location) {
		System.out.print("Updating Game\n");
		board.sowSeeds(location);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
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
	public Game() {
		setTitle("Kalah Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ButtonsListener listener = new ButtonsListener();

		JButton button1 = new JButton("1");
		button1.addActionListener(listener);

		JButton button2 = new JButton("2");
		button2.addActionListener(listener);

		JButton button3 = new JButton("3");
		button3.addActionListener(listener);

		JButton button4 = new JButton("4");
		button4.addActionListener(listener);

		JButton button5 = new JButton("5");
		button5.addActionListener(listener);

		JButton button_1 = new JButton("1");
		button_1.addActionListener(listener);

		JButton button_2 = new JButton("2");
		button_2.addActionListener(listener);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(listener);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(listener);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(listener);

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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblTime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnReset, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addGap(14)
				.addGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_5,
												GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(button1, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(button2, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(button3, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(button4, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button5,
												GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblHouse1)
						.addPreferredGap(ComponentPlacement.RELATED, 485, Short.MAX_VALUE).addComponent(lblHouse2)
						.addGap(28)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblTime).addGap(17)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button4, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button5, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblHouse1)
								.addComponent(lblHouse2))
						.addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 37,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 37,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)

						.addComponent(btnReset)));
		contentPane.setLayout(gl_contentPane);
	}

	private class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton) event.getSource();

		}
	}
}