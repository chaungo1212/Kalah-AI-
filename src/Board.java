/*
	Project: Kalah_315
	File: Board.java
	Author: Sarah White
*/

import java.util.Arrays;

class Board {
	/*
	 * left store is index "0", right store is index "length-1", houses start at
	 * upper left corner as "1", index moves right and down to the lower right
	 * corner
	 */
	private int[] seeds;

	public GameManager game_manager;

	public Board(int houses, int seedsPer)
	{
		seeds = new int[houses];
		seeds[0] = 0;
		seeds[seeds.length-1] = 0;
		for (int i = 1; i < (seeds.length-1); i++)
		{
			seeds[i] = seedsPer;
		}
	}
	
	public Board(int houses, int[] seedsPer)
	{
		//initialize seeds in each index based on rules given
		seeds = new int[houses];
		seeds[0] = 0;
		seeds[seeds.length-1] = 0;
		if (seedsPer.length >= (seeds.length/2))
		{
			for (int i = 1; i < (seeds.length/2); i++)
			{
				seeds[i] = seedsPer[i];
				seeds[(seeds.length-1)-i] = seedsPer[i];
			}
		}
		else
		{
			//error
		}
	}
	
	//gets number of seeds at a particular index
	public int getSeeds(int from)
	{
		return seeds[from];
	}
	
	public void sowSeeds(int house)
	{
        int original = house;
		int remaining = getSeeds(house);
		while(remaining != 0)
		{
			if(house == 0 || house == (seeds.length-1))
			{
				if(house == 0)
				{
					house = ((seeds.length/2)+1);
				}
				else
				{
					house = (seeds.length/2);
				}	
			}
			else
			{
				house = house++;
				seeds[house]++;
				remaining--;
			}
		}
        //if last seed in empty house
        if (seeds[house] == 1)
        {
            //on player one's side
            if((original-0) < (seeds.length-1)-original)
                //capture all on both sides of the board for player 1
                seeds[0] +=
                    seeds[house] + seeds[house+((seeds.length-1)/2)];
            }
            //on player two's side
            else if((original-0) > (seeds.length-1)-original)
            {
                //capture all on both sides of the board for player 2
                seeds[(seeds.length-1)] +=
                    seeds[house] + seeds[house-((seeds.length-1)/2)];
            }
        }
	
	public int[] getSeeds()
	{
        return seeds;
	}
    
    public void drawBoard()
    {
        int[] temp = getSeeds();
        for(int i = 1; i <= ((temp.length-1)/2); i++)
        {
            System.out.print(temp[i] + " ");
        }
        System.out.println("\n");
        
        System.out.print(temp[0]);
        for(int k = 1; k <= (temp.length-1); k++)
        {
            System.out.print(" ");
        }
        System.out.print(temp[temp.length]);
        System.out.println("\n");
        
        for(int j = ((temp.length-1)/2); j <= ((temp.length-1)); j++)
        {
            System.out.print(temp[j] + " ");
        }
        System.out.println("\n");
    }
}
