/*
	Project: Kalah_315
	File: Board.java
	Author: Sarah White
*/

class Board
{
	/* left store is index "0", right store is index "length-1",
	   houses start at upper left corner as "1",
	   index moves right and down to the lower right corner
	*/
	int[] seeds;
	
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
	}
	
	public int[] getSeeds()
	{
        return seeds;
	}
}