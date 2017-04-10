import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class AI extends Player {
	public AI() {
		super("computer");
	}

	public Board search_move(Board current_board) { // return the Board after AIdecides to move
		// Set the root as current_board with score 0
		current_board.score = 0;
		// Start BFS search to grow the tree.
		Queue<Board> searchinglist = new LinkedList();
		// The first level.
		for (int i = 0; i < current_board.north_house.size(); i++) {
			// Start to move for new board
			Board newboard = null;
			if (current_board.north_house.get(i).nseed != 0) {
				newboard = current_board.AImoveNorth(i);
				newboard.move_index = i;
				searchinglist.add(newboard);
			}
		}
		// The second level.
		Vector<Board> seclevel_boards = new Vector<Board>();
		while (searchinglist.size() != 0) {
			Board b = searchinglist.poll();
			boolean nochild = true;
			if (b.free_turn == true) { // newboard will be simulated in northside by AI
				for (int i = 0; i < b.north_house.size(); i++) {
					// Start to move for new board
					Board newboard = null;
					if (b.north_house.get(i).nseed != 0) {
						newboard = b.AImoveNorth(i);
						newboard.move_index = i;
						seclevel_boards.add(newboard);
						nochild = false;
					}
				}
			} else { // newboard will be simulated in south side by player
				for (int i = 0; i < b.south_house.size(); i++) {
					// Start to move for new board
					Board newboard = null;
					if (b.south_house.get(i).nseed != 0) {
						newboard = b.AImoveSouth(i);
						newboard.move_index = i;
						seclevel_boards.add(newboard);
						nochild = false;
					}
				}
			}
			if (nochild == true) {
				Board newboard = new Board(b);
				newboard.parent = b;
				seclevel_boards.add(newboard);
			}
		}
		// Decide which board in 2nd level has highest score
		float max = -1;
		int max_index = -1;
		for (int i = 0; i < seclevel_boards.size(); i++) {
			float score = seclevel_boards.get(i).score + seclevel_boards.get(i).store1.nseed
					- seclevel_boards.get(i).store2.nseed;
			if (score > max) {
				max = score;
				max_index = i;
			}
		}
		return seclevel_boards.get(max_index).parent;
	}
}
