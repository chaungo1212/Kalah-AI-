import java.util.Scanner;

public class Kalah {

	public static void main(String[] args) {
		int houses;
		int seeds_per;
		int player_choice = -1;
		String player_name;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.print("Welcome to the game of kalah!\n");
		
		System.out.println("Enter the number of houses: ");
		houses = reader.nextInt();
		
		System.out.println("Enter the number of seeds per house: ");
		seeds_per = reader.nextInt();
		
		while(player_choice != 1 && player_choice != 2){
			System.out.println("Would you like to be player 1 or 2?\nEnter 1 or 2: ");
			player_choice = reader.nextInt();
		}
		
		System.out.println("Username: ");
		player_name = reader.toString();
		
		GameManager tmp = new GameManager(houses, seeds_per, player_choice, player_name); 
		
		while(!tmp.is_game_over()){
			
		}
		
	}

}
