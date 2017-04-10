/*
	Project: Kalah_315
	File: Board.java
	Authors:
*/

//import java.util.Arrays;

//public class Board {
	/*
	 * left store is index "0", right store is index "length-1", houses start at
	 * upper left corner as "1", index moves right and down to the lower right
	 * corner
	 */
/*	private int[] seeds;

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

	public int sowSeeds(int house) {
		int original = house;
		int remaining = getNumSeeds(house);
		
		while (remaining != 0) {
			//make sure player can only put in their store
			if(original > seeds.length/2)
			{
				if(house == 0)
				{
					house = (seeds.length/2)+1;
				}

			}
			else
			{
				if(house == seeds.length-1)
				{
					house = (seeds.length/2);
				}
			}
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
		// if last seed in empty house
		if (seeds[house] == 1) {
			// on player one's side
			if ((original - 0) < (seeds.length - 1) - original) {
				if (seeds[house - ((seeds.length - 1) / 2)] != 0) {
					// capture all on both sides of the board for player 1
					seeds[0] += seeds[house] + seeds[house + ((seeds.length - 1) / 2)];
				}
				//else don't capture
			}
			// on player two's side
			else if ((original - 0) > (seeds.length - 1) - original) {
				if (seeds[house + ((seeds.length - 1) / 2)] != 0) {
					// capture all on both sides of the board for player 2
					seeds[(seeds.length - 1)] += seeds[house] + seeds[house - ((seeds.length - 1) / 2)];
				}
				//else don't capture
			}
		}
		//if last seed in a store
		if (house == 0 || house == (seeds.length - 1)) {
			//one more turn
			return 1;
		}
		else {
			//no more turns
			return 0;
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
	
	public int[] getH1Seeds() {
		int[] half = getSeeds();
		int j = 0;
		for (int i = 0; i < seeds.length; i++) {
			if (!(i == 0) || !(i == seeds.length-1) || !(i > ((seeds.length-1)/2))) {
				half[j] = seeds[i];
				j++;
			}
		}
		return half;
	}
	
	public int[] getH2Seeds() {
		int[] half = getSeeds();
		int j = 0;
		for (int i = 0; i < seeds.length; i++) {
			if (!(i == 0) || !(i == seeds.length-1) || !(i < ((seeds.length-1)/2))) {
				half[j] = seeds[i];
				j++;
			}
		}
		return half;
	}
	
	public int sumSide(int[] side) {
		int sum = 0;
		for (int i : side) {
			sum += i;
		}
		return sum;
	}
	
	public boolean finished() {
		if (sumSide(getH1Seeds()) == 0 || sumSide(getH2Seeds()) == 0) {
			if (sumSide(getH1Seeds()) == 0) {
				//add remainder to other player's store
				seeds[seeds.length-1] += sumSide(getH2Seeds());
			}
			else {
				//add remainder to other player's store
				seeds[0] += sumSide(getH1Seeds());
			}
			return true;
		}
		return false;
	}
	
	public void flipBoard() {
		int temp;
		int j = seeds.length-1;
		for (int i = 0; i < ((seeds.length-1)/2)+1; i++) {
				temp = seeds[i];
				seeds[i] = seeds[j];
				seeds[j] = temp;
				j--;
		}
	}
	
}*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Board {
	public Vector<House> north_house; // AI houses
	public Vector<House> south_house; // players' houses
	public House store1; // AI
	public House store2; // player
	public int next_board_index; // index in maxmintree
	public boolean free_turn; // in searching
	public float score; // for maxmin searching tree to choose
	public Board parent;

	public Board(int nhouse_per, int nseed) {
		north_house = new Vector<House>();
		south_house = new Vector<House>();
		for (int i = 0; i < nhouse_per; i++) {
			House north = new House(nseed);
			House south = new House(nseed);
			north.type = i;
			south.type = i;
			north_house.add(north);
			south_house.add(south);
		}
		store1 = new House();
		store1.type = -1;
		store2 = new House();
		store2.type = -2;
		free_turn = false;
		parent = null;
		score = 0;
	}
	
	public Board(int houses, int[] seeds_per) {
		// initialize seeds in each index based on rules given
		north_house = new Vector<House>();
		south_house = new Vector<House>();
		for (int i = 0; i < houses; i++) {
			House north = new House(seeds_per[seeds_per.length - i]);
			House south = new House(seeds_per[i]);
			north.type = i;
			south.type = i;
			north_house.add(north);
			south_house.add(south);
		}
		store1 = new House();
		store1.type = -1;
		store2 = new House();
		store2.type = -2;
		free_turn = false;
		parent = null;
		score = 0;
	}

	public Board(Board b) {
		north_house = new Vector<House>();
		south_house = new Vector<House>();
		for (int i = 0; i < b.north_house.size(); i++) {
			House newnorth = new House(b.north_house.elementAt(i));
			House newsouth = new House(b.south_house.elementAt(i));
			newnorth.type = i;
			newsouth.type = i;
			north_house.add(newnorth);
			south_house.add(newsouth);
		}
		store1 = new House(b.store1);
		store1.type = -1;
		store2 = new House(b.store2);
		store2.type = -2;
		score = b.score;
		free_turn = false; // This always initially set false. Only update when checking.
		parent = null;
		score = b.score;
	}

	public Board AImoveNorth(int index_house) {
		Board newboard = new Board(this);
		newboard.parent = this;
		int nseed = newboard.north_house.elementAt(index_house).nseed;
		newboard.north_house.elementAt(index_house).nseed = 0;
		Queue q = new LinkedList();
		// Set Houses into q in counterclockwise.
		for (int i = newboard.north_house.size() - 1; i >= 0; i--) {
			q.add(newboard.north_house.get(i));
		}
		q.add(newboard.store1);
		for (int i = 0; i < newboard.south_house.size(); i++) {
			q.add(newboard.south_house.get(i));
		}
		q.add(newboard.store2);
		// Make q start from the house which is going to add seed
		int npop = newboard.north_house.size() - index_house;
		for (int i = 0; i < npop; i++) {
			House pop = (House) q.poll();
			q.add(pop);
		}
		// Then start to update the seed in the house
		for (int i = 0; i < nseed; i++) {
			House updated = (House) q.poll();
			if ((i == nseed - 1) && (updated.type == -1)) { // The last seed go to store1 => get free turn
				updated.nseed++;
				newboard.free_turn = true;
				newboard.score += 1;
			} else if ((i == nseed - 1) && updated.type > 0 && updated.nseed == 0
					&& newboard.south_house.get(updated.type).nseed != 0) {
				newboard.store1.nseed = newboard.store1.nseed + newboard.south_house.get(updated.type).nseed + 1;
				updated.nseed = 0;
				newboard.south_house.get(updated.type).nseed = 0;
			} else { // normal case
				updated.nseed++;
			}
			q.add(updated);

		}
		/*
		 * Check whether one side is all zeros or not. North all zero => south
		 * nseed go to store2 South all zero => north nseed go to store1 Check
		 * north
		 */
		int allsouth = 0;
		int allnorth = 0;
		boolean allnorthzero = true;
		boolean allsouthzero = true;
		for (int i = 0; i < newboard.north_house.size(); i++) {
			if (newboard.north_house.get(i).nseed != 0) {
				allnorthzero = false;
			}
			allsouth = allsouth + newboard.south_house.get(i).nseed;
		}
		for (int i = 0; i < newboard.south_house.size(); i++) {
			if (newboard.south_house.get(i).nseed != 0) {
				allsouthzero = false;
			}
			allnorth = allnorth + newboard.north_house.get(i).nseed;
		}
		if (allnorthzero == true) {
			newboard.store2.nseed += allsouth;
			for (int i = 0; i < newboard.south_house.size(); i++) {
				newboard.south_house.get(i).nseed = 0;
			}
		}
		if (allsouthzero == true) {
			newboard.store1.nseed += allnorth;
			for (int i = 0; i < newboard.north_house.size(); i++) {
				newboard.north_house.get(i).nseed = 0;
			}
		}
		return newboard;
	}

	public Board AImoveSouth(int index_house) {
		Board newboard = new Board(this); // child board
		newboard.parent = this;
		int nseed = newboard.south_house.elementAt(index_house).nseed;
		newboard.south_house.elementAt(index_house).nseed = 0;
		Queue q = new LinkedList();
		// Set Houses into q in counterclockwise.
		for (int i = 0; i < newboard.south_house.size(); i++) {
			q.add(newboard.south_house.get(i));
		}
		q.add(newboard.store2);
		for (int i = newboard.north_house.size() - 1; i >= 0; i--) {
			q.add(newboard.north_house.get(i));
		}
		q.add(newboard.store1);
		// Make q start from the house which is going to add seed
		int npop = index_house + 1;
		for (int i = 0; i < npop; i++) {
			House pop = (House) q.poll();
			q.add(pop);
		}
		// Then start to update the seed in the house
		for (int i = 0; i < nseed; i++) {
			House updated = (House) q.poll();
			if ((i == nseed - 1) && updated.type == -2) { // The last seed go to store2 => get free turn
				updated.nseed++;
				newboard.free_turn = true;
				newboard.score += 1;
			} else if ((i == nseed - 1) && updated.type > 0 && updated.nseed == 0
					&& newboard.north_house.get(updated.type).nseed != 0) {
				newboard.store2.nseed = newboard.store2.nseed + newboard.north_house.get(updated.type).nseed + 1;
				updated.nseed = 0;
				newboard.north_house.get(updated.type).nseed = 0;
			} else {
				updated.nseed++;
			}
			q.add(updated);
		}
		/*
		 * Check whether one side is all zeros or not. North all zero => south
		 * nseed go to store2 South all zero => north nseed go to store1 Check
		 * north
		 */
		int allsouth = 0;
		int allnorth = 0;
		boolean allnorthzero = true;
		boolean allsouthzero = true;
		for (int i = 0; i < newboard.north_house.size(); i++) {
			if (newboard.north_house.get(i).nseed != 0) {
				allnorthzero = false;
			}
			allsouth = allsouth + newboard.south_house.get(i).nseed;
		}
		for (int i = 0; i < newboard.south_house.size(); i++) {
			if (newboard.south_house.get(i).nseed != 0) {
				allsouthzero = false;
			}
			allnorth = allnorth + newboard.north_house.get(i).nseed;
		}
		if (allnorthzero == true) {
			newboard.store2.nseed += allsouth;
			for (int i = 0; i < newboard.south_house.size(); i++) {
				newboard.south_house.get(i).nseed = 0;
			}
		}
		if (allsouthzero == true) {
			newboard.store1.nseed += allnorth;
			for (int i = 0; i < newboard.north_house.size(); i++) {
				newboard.north_house.get(i).nseed = 0;
			}
		}
		return newboard;
	}

	public void drawBoard() {
		System.out.print(Integer.toString(store1.nseed) + "|");
		for (int i = 0; i < north_house.size(); i++) {
			System.out.print(north_house.get(i).nseed);
		}
		System.out.println("|");

		System.out.print(" |");
		for (int i = 0; i < south_house.size(); i++) {
			System.out.print(south_house.get(i).nseed);
		}
		System.out.print("|");
		System.out.println(Integer.toString(store2.nseed));
	}
}
