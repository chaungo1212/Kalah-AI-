/*
	Project: Kalah_315
	File: Player.java
	Authors: 
*/

public class Player {
	private String username;
	private int score;
	boolean turn = false;
	private boolean AI;
	
	// Constructors
	public Player(String player_name) {
		this.username=player_name;
		if (player_name.equals("computer")) {
			AI=true;
		}
	}
	
	public Player(Player play) {
		username = play.getUsername();
		score = play.getScore();
		turn = play.getTurn();
		AI = play.isAI();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String new_username) {
		this.username = new_username;
	}

	public boolean getTurn() {
		return turn;
	}

	public void setTurn(boolean new_turn) {
		this.turn = new_turn;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int new_score) {
		score = new_score;
	}
	
	public boolean isAI() {
		return AI;
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
}
