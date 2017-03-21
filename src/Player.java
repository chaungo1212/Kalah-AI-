import java.util.Scanner;

public class Player {
	static final Scanner scanner = new Scanner(System.in);
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
