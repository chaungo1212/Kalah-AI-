/*
	Project: Kalah_315
	File: AI.java
	Authors: 
*/

import java.util.Random;
import java.util.Vector;

public class AI extends Player{
	private char type;
	private Vector<Integer> memory;
	
	public AI(char c) {
		super("computer");
		type = c;
		memory = new Vector<Integer>();
	}
	
	public char getType () {
		return type;
	}
	
	public void setType (char c) {
		type = c;
	}
	
	public int runWhat(char c) {
		int move = 0;
		switch(c)
		{
			case 'E':
				move = runE();
				break;
			case 'M':
				move = runM();
				break;
			case 'H':
				move = runH();
				break;
			default:
				break;
		}
		return move;
	}
	
	public int runE() {
		//pick randomly from valid moves
		//Random rand = new Random();
		int move = 0;
		//int max = 13;
		//pick randomly from valid moves
		//while (!isValidMove(move)) {
		//	move = rand.nextInt(1, max);
		//}
		return move;
	}
	
	public int runM() {
		//use min-max tree to look ahead
		return 0;
	}

	public int runH() {
		//use min-max tree to look ahead
		return 0;
	}


}

//simple AI to pick randomly from valid moves

//if AI is P1
//pick random from these values
//else
//pick random from other values