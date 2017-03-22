public class Player {
	private String username;
	int score;
	boolean move = false;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userame) {
		this.username = userame;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean getMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean makeMove(Board board, int seed, int from_house, int to_house) {
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
	}

	// Constructor
	public Player(String player_name) {
		setUsername(player_name);
		// setMove(player_move);
	}
}
