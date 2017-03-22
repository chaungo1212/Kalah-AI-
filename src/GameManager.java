import java.util.Scanner;

public class GameManager {
	Board board;
	Player player_1;
	Player player_2;

	int houses;
	int seeds_per;
	int player_choice = -1;
	int scoreP1;
	int scoreP2;
	String player_name;

	Scanner reader = new Scanner(System.in);

	public GameManager() {
	}

	public void makeMove(int location) {
		System.out.print("Updating Game\n");
		board.sowSeeds(location);
	}

	public boolean isValidMove(int location) {
		return true;
	}

	public void startTimer() {
		System.out.print("Starting timer\n");
	}

	public int checkTimer() {
		return 1;
	}

	public boolean timeIsOut() {
		return false;
	}

	public boolean isGameOver() {
		return false;
	}

	public Player whoWon() {
		return player_1;
	}

	public int getP1Score() {
		return scoreP1;
	}

	public void setP1Score(int score) {
		this.scoreP1 = score;
	}

	public int getP2Score() {
		return scoreP2;
	}

	public void setP2Score(int score) {
		this.scoreP2 = score;
	}

	public void updateGame() {
		System.out.print("Updating Game\n");
	}

	public void newGame() {

		System.out.print("Welcome to the game of kalah!\n");

		System.out.println("Enter the number of houses: ");
		houses = reader.nextInt();

		System.out.println("Enter the number of seeds per house: ");
		seeds_per = reader.nextInt();

		while (player_choice != 1 && player_choice != 2) {
			System.out.println("Would you like to be player 1 or 2?\nEnter 1 or 2: ");
			player_choice = reader.nextInt();
		}

		System.out.println("Username: ");
		player_name = reader.next();

		board = new Board(houses, seeds_per);

		if (player_choice == 1) {
			player_1 = new Player(player_name);
			player_2 = new Player("computer");
		} else {
			player_1 = new Player("computer");
			player_2 = new Player(player_name);
		}

		System.out.print("New Game\n");
	}

	public void resetGame() {
		System.out.print("Reset Game\n");

	}

	public void drawGame() {
		board.drawBoard();
	}

}
