import java.util.Scanner;

public class Kalah_main {

	public static void main(String[] args) {
		/*****************************************************
		 * Getting data for game manager construction
		 ****************************************************/
		
		GameManager game_manager = new GameManager();
		game_manager.new_game();
		
		/*****************************************************
		 * Main Game Loop
		 ****************************************************/
		int location;
		int max_time = 30;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Enter -1 to start a new game and -2 to reset it: \n");
		System.out.println("Enter a location number to move: ");
		location = reader.nextInt();

		while(!game_manager.is_game_over()){
			game_manager.start_timer();
			
			while(game_manager.check_timer() != max_time){
				game_manager.draw_game();
				
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

