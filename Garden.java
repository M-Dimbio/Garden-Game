/*  Assignment 4
 *  Written by: Mohamed Abdul Mohamed, 40098785 30/11/18
 *  For COMP 248 Section (FF) – Fall 2018
 * It's a garden class that create the garden array 
 */
public class Garden 
{
	private char [][] garden; // private garden attributes
	public Garden() // constructor of garden
	{
		this.garden=new char [3][3];
		initializeGarden(3);
	}
	public Garden(int size) //constructor of garden
	{

		this.garden=new char [size][size];
		initializeGarden(size);
	}
	private void initializeGarden(int size) // private method only use inside this class
	{
		for (int i=0;i<size;i++) 
		{
			for(int j=0;j<size;j++) 
			{
				this.garden[i][j]='-';
			}
		}
	}
	public char getInLocation(int rLocation, int cLocation) // what's in those location
	{ 	
		return this.garden[rLocation][cLocation];
	}
	public char [][]plantFlower(int rLocation, int cLocation)
	{
		this.garden[rLocation][cLocation]='f';
		return this.garden;
	}
	public char[][] plantTree(int rLocation, int cLocation) // plant the tree
	{
		this.garden[rLocation][cLocation]='t';
		this.garden[rLocation+1][cLocation]='t';
		this.garden[rLocation][cLocation+1]='t';
		this.garden[rLocation+1][cLocation+1]='t';
		return this.garden;
	}
	public char[][] removeFlower(int rLocation, int cLocation) // remove the flower from a spot
	{
		this.garden[rLocation][cLocation]='-';
		return this.garden;
	}
	public int  countPossibleTrees() // count the number of trees that you can place
	{

		int count=0;
		for(int i=0;i<garden.length;i++) 
		{
			for(int j=0;j<garden.length;j++)

			{
				if (i+1<garden.length&&j+1<garden.length) 
				{
					if(this.garden[i][j]=='-'&&this.garden[i+1][j]=='-'&&this.garden[i][j+1]=='-'&&this.garden[i+1][j+1]=='-') 
					{
						count=count+1;
					}
				}
			}
		} 
		return count;
	}
	public int  countPossibleFlowers() // count possible flower we can plant in our board
	{

		int count=0;
		for(int i=0;i<garden.length;i++) 
		{
			for(int j=0;j<garden.length;j++)

			{
				if(this.garden[i][j]=='-') 
				{
					count=count+1;
				}

			}
		} 
		return count;
	}


	public String toString() // to string method change to give us the board
	{

		String aString="";
		int count=0;

		{
			aString+=" |";
			for(int col=0; col<garden.length;col++) 
			{
				aString+=" "+count;
				count=count+1;
			}
			count=0;

			aString=aString+"\n";
		}

		for(int row=0;row<garden.length;row++) 
		{
			aString+=""+count+"|";
			for (int col=0; col<garden.length;col++) 
			{

				aString+=" "+ garden[row][col];

			}

			aString=aString+"\n";
			count=count+1;
		}
		return aString;
	}

	public boolean gardenFull() // if the garden full return true
	{
		int count=0;
		for(int i=0; i<garden.length;i++) 
		{
			for (int j=0;j<garden.length;j++) 
			{
				if(garden[i][j]=='-')
					count=count+1;
			}
		}
		return count==0;
	}

}

