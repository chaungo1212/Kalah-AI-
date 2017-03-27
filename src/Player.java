/*
	Project: Kalah_315
	File: Player.java
	Authors: 
*/

public class Player {
	private String username;
	int score;
	boolean turn = false;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/*public boolean makeMove(Board board, int seed, int from_house, int to_house) {
		// Move seed on the board.
		// Ask board.game_manager to check whether this move is available or not
		// boolean check = board.game_manager.checkvalid()
		// if it is validate(true) => update the board and score
		// else give a message to player that this move is not available
		boolean check = board.game_manager.is_valid_move(from_house);
		if (check) {
			board.sowSeeds(from_house);
			return true;
		} else {
			System.out.println(" invalid move");
			return false;
		}
	}*/

	// Constructor
	public Player(String player_name) {
		this.username=player_name;
	}
}
