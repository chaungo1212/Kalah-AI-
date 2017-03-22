
public class GameManager {
	Board board;
	Player player_1;
	Player player_2;
	
	public GameManager(int houses, int seeds_per, int player_choice, String player_name){
		board = new Board(houses, seeds_per);
		if(player_choice == 1){
			player_1 = new Player(player_name, 0);
			player_2 = new Player("computer", 0);
		}
		else{
			player_1 = new Player("computer", 0);
			player_2 = new Player(player_name, 0);
		}
	}
	
	public void make_move(int location){
		
	}
	
	public boolean is_valid_move(int location){
		return true;
	}
	
	public void start_timer(){
		
	}
	public int check_timer(){
		return 1;
	}
	public boolean time_is_out(){
		return false;
	}
	
	public boolean is_game_over(){
		return false;
	}
	
	public Player who_won(){
		return player_1;
	}
	
	public void update_game(){
		
	}
	
	public void new_game(){
		
	}
	
	public void reset_game(){
		
	}
	
	
	
}