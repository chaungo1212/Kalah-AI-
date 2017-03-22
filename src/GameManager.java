
public class GameManager {
	Board board;
	Player player_1;
	Player player_2;
	
	public GameManager(int houses, int seeds_per, int player_choice, String player_name){
		board = new Board(houses, seeds_per);
		if(player_choice == 1){
			player_1 = new Player(player_name, 0);
		}
	}
	
	public void move(String cmd){
		
	}
	
	public boolean is_valid_move(){
		return true;
	}
	
	public void start_timer(){
		
	}
	public void check_timer(){
		
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