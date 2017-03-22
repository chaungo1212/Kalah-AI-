import java.util.Scanner;

public class GameManager {
	Board board;
	Player player_1;
	Player player_2;
	
	int houses;
	int seeds_per;
	int player_choice = -1;
	String player_name;
	
	Scanner reader = new Scanner(System.in);
	
	public GameManager(){}
	
	public void make_move(int location){
		System.out.print("Updating Game\n");
        
        int original = location;
		int remaining = getNumSeeds(location);
        int[] temp = board.getSeeds();
		while(remaining != 0)
		{
			if(location == 0 || location == (temp.length-1))
			{
				if(location == 0)
				{
					location = ((temp.length/2)+1);
				}
				else
				{
					location = (temp.length/2);
				}	
			}
			else
			{
				location = location++;
				temp[location]++;
				remaining--;
			}
		}
        //if last seed in empty house
        if (temp[location] == 1)
        {
            //on player one's side
            if((original-0) < (temp.length-1)-original)
            {
                //capture all on both sides of the board for player 1
                temp[0] +=
                    temp[location] + temp[location+((temp.length-1)/2)];
            }
            //on player two's side
            else if((original-0) > (temp.length-1)-original)
            {
                //capture all on both sides of the board for player 2
                temp[(temp.length-1)] +=
                    temp[location] + temp[location-((temp.length-1)/2)];
            }
        }
        board.seeds = temp;
    }

	public boolean is_valid_move(int location){
		return true;
	}
	
	public void start_timer(){
		System.out.print("Starting timer\n");
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
		System.out.print("Updating Game\n");
	}
	
	public void new_game(){
		
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
		player_name = reader.next();
		
		board = new Board(houses, seeds_per);
		
		if(player_choice == 1){
			player_1 = new Player(player_name, 0);
			player_2 = new Player("computer", 0);
		}
		else{
			player_1 = new Player("computer", 0);
			player_2 = new Player(player_name, 0);
		}
		
		System.out.print("New Game\n");
	}
	
	public void reset_game(){
		System.out.print("Reset Game\n");
		
	}
	
	public void draw_game(){
		board.drawBoard();
	}
	
}
