/*
	Project: Kalah_315
	File: AI.java
	Authors: 
*/

import java.util.Random;
import java.util.Vector;

public class AI {
	private char type;
	private Vector<Integer> memory;
	
	public AI(char c) {
		type = c;
		memory = new Vector<Integer>();
	}
	
	public void runWhat() {
		switch(type)
		{
			case 'E': runE();
			case 'M': runM();
			case 'H': runH();
			default:
		}
	}
	
	public int runE() {
		//pick randomly from valid moves
		Random rand = new Random();
		int front = 0;
		int back = 0;
		return rand.nextInt(back - front + 1) + front;
	}
	
	public void runM() {
		//use min-max tree to look ahead
	}

	public void runH() {
		//use min-max tree to look ahead
	}


}

//simple AI to pick randomly from valid moves

//if AI is P1
//pick random from these values
//else
//pick random from other values