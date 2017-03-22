import java.util.Scanner;

public class Kalah_main {

	public static void main(String[] args) {
		int houses;
		int seeds_per;
		int player_choice = -1;
		String player_name;
		
		Scanner reader = new Scanner(System.in);
		
		/*****************************************************
		 * Getting data for game manager construction
		 ****************************************************/
		
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
		
		GameManager game_manager = new GameManager(houses, seeds_per, player_choice, player_name); 
		
		/*****************************************************
		 * Main Game Loop
		 ****************************************************/
		int location;
		int max_time = 30;
		
		System.out.println("Enter -1 to start a new game and -2 to reset it: \n");
		System.out.println("Enter a location number to move: ");
		location = reader.nextInt();

		while(!game_manager.is_game_over()){
			game_manager.start_timer();
			
			while(game_manager.check_timer() != max_time){
				System.out.println("Enter Location: ");
				location = reader.nextInt();
				
				if(game_manager.is_valid_move(location)){
					game_manager.make_move(location);
				}
				else if(location == -1){
					game_manager.new_game();
				}
				else if(location == -2){
					game_manager.reset_game();
				}
				else{
					System.out.print("Invalid Move\n");
				}
			}
			if(game_manager.check_timer() == max_time){
				System.out.print("You ran out of time\n");
			}
			game_manager.update_game();
			
		}
		
	}

}
