/*
	Project: Kalah_315
	File: AI.java
	Authors: 
*/

import java.util.Random;
import java.util.Vector;

public class AI extends Player {
	private char type;
	private Vector<Integer> memory;
	
	public AI(String t, int playernum) {
		super(t, playernum);
		type = t.charAt(0);
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
		if(turn == true) {
			int pos = 0;
			if (playernum == 1) {
				pos = rand.nextInt(GameManager.seeds_per-1) + 1;
			}
			else {
				pos = rand.nextInt(GameManager.seeds_per-1) + GameManager.seeds_per+1;
			}
			return pos;
		}
		else {
			//error
			return 0;
		}
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