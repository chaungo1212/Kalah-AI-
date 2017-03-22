import java.util.Scanner;

public class Player {
	static final Scanner scanner = new Scanner(System.in);
	private String username;
	int score;
	boolean move = false;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public void makeMove(Board board, int seed, int from_house, int to_house) {
		// Move seed on the board.
		// Ask board.game_manager to check whether this move is available or not
		// bool check = board.game_manager.checkvalid()
		// if it is validate(true) => update the board and score
		// else give a message to player that this move is not available
		
	}

	// Override toString to display the correct amount information
	public String toString() {
		return ("\nName: " + username + "\nScore: " + score);
	}

	// Constructor
	public Player(String player_name, int player_score) {
		setUsername(player_name);
		setScore(player_score);
		// setMove(player_move);
	}
}
