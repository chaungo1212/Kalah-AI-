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
	
	public AI(AI ai) {
		super("computer");
		type = ai.getType();
		memory = ai.getMemory();
	}
	
	public char getType () {
		return type;
	}
	
	public void setType (char c) {
		type = c;
	}
	
	public Vector<Integer> getMemory() {
		return memory;
	}
	
	public String runWhat(char c) {
		String move = "0 0";
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
	
	public String runE() {
		//pick randomly from valid moves
		//Random rand = new Random();
		String move = "0 0";
		//int max = 13;
		//pick randomly from valid moves
		//while (!isValidMove(move)) {
		//	move = rand.nextInt(1, max);
		//}
		return move;
	}
	
	public String runM() {
		String move = "0 0";
		//use min-max tree to look ahead
		return move;
	}

	public String runH() {
		String move = "0 0";
		//use min-max tree to look ahead
		return move;
	}


}

//simple AI to pick randomly from valid moves

//if AI is P1
//pick random from these values
//else
//pick random from other values