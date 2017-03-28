/*
	Project: Kalah_315
	File: Board.java
	Authors:
*/

//import java.util.Arrays;

public class Board {
	/*
	 * left store is index "0", right store is index "length-1", houses start at
	 * upper left corner as "1", index moves right and down to the lower right
	 * corner
	 */
	private int[] seeds;

	public Board(int houses, int seedsPer) {
		seeds = new int[(2 * houses) + 2];
		seeds[0] = 0;
		seeds[seeds.length - 1] = 0;
		for (int i = 1; i < (seeds.length - 1); i++) {
			seeds[i] = seedsPer;
		}
	}

	public Board(int houses, int[] seedsPer) {
		// initialize seeds in each index based on rules given
		seeds = new int[(2 * houses) + 2];
		seeds[0] = 0;
		seeds[seeds.length - 1] = 0;
		if (seedsPer.length >= (seeds.length / 2)) {
			for (int i = 1; i < (seeds.length / 2); i++) {
				seeds[i] = seedsPer[i];
				seeds[(seeds.length - 1) - i] = seedsPer[i];
			}
		} else {
			// error
		}
	}

	public Board(Board b) {
		seeds = b.getSeeds();
	}

	// gets number of seeds at a particular index
	public int getNumSeeds(int from) {
		return seeds[from];
	}

	public int[] getSeeds() {
		return seeds;
	}

	public void setSeeds(int[] newSeeds) {
		seeds = newSeeds;
	}

	public void sowSeeds(int house) {
		int original = house;
		int remaining = getNumSeeds(house);
		
		//make sure player can only put in their store
		
		//if this player started from the top row
		//skip opponents store
		if(original < seeds.length / 2)
		{
			if(house == seeds.length-1)
			{
				house = (seeds.length/2);
			}
		}
		//if this player started from the bottom row,
		//skip opponents store
		else
		{
			if(house == 0)
			{
				house = (seeds.length/2)+1;
			}
		}
		
		while (remaining != 0) {
			if (house == 0 || house == (seeds.length - 1)) {
				if (house == 0) {
					house = ((seeds.length / 2) + 1);
				}
				else {
					house = (seeds.length / 2);
				}
			}
			else {
				house = house++;
				seeds[house]++;
				remaining--;
			}
		}
		//if last seed in a store
		if (house == 0 || house == (seeds.length - 1)) {
			//increase # of turns
		}
		
		// if last seed in empty house
		if (seeds[house] == 1) {
			// on player one's side
			if ((original - 0) < (seeds.length - 1) - original) {
				// capture all on both sides of the board for player 1
				seeds[0] += seeds[house] + seeds[house + ((seeds.length - 1) / 2)];
			}
			// on player two's side
			else if ((original - 0) > (seeds.length - 1) - original) {
				// capture all on both sides of the board for player 2
				seeds[(seeds.length - 1)] += seeds[house] + seeds[house - ((seeds.length - 1) / 2)];
			}
		}
	}

	public void drawBoard() {
		int[] temp = getSeeds();
		System.out.print(" ");
		for (int i = 1; i <= ((temp.length - 1) / 2); i++) {
			System.out.print(temp[i] + " ");
		}
		System.out.print("\n");

		// -----------------------

		System.out.print(temp[0]);
		for (int k = 1; k <= (temp.length - 1); k++) {
			System.out.print(" ");
		}
		System.out.print(temp[temp.length - 1]);
		System.out.print("\n");

		// -----------------------

		System.out.print(" ");
		for (int j = ((temp.length) / 2); j <= ((temp.length - 2)); j++) {
			System.out.print(temp[j] + " ");
		}
		System.out.print("\n");
	}

	public int getP1Score() {
		return seeds[0];
	}

	public int getP2Score() {
		return seeds[seeds.length - 1];
	}
	
	public int[] getP1Homes() {
		int[] listHalf = new int[(seeds.length-1)/2];
		for (int i = 1; i < seeds.length-1/2; i++) {
			listHalf[i-1] = (seeds[i-1]);
		}
		return listHalf;
	}
	
	public int[] getP2Homes() {
		int[] listHalf = new int[(seeds.length-1)/2];
		for (int i = (seeds.length-1/2+1); i < seeds.length-1; i++) {
			listHalf[i-1] = (seeds[i-1]);
		}
		return listHalf;
	}
}
