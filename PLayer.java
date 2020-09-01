/*  Assignment 4
 *  Written by: Mohamed Abdul Mohamed, 40098785 30/11/18
 *  For COMP 248 Section (FF) – Fall 2018
 * It's a player class the take all the information of player that we store in the object player
 */
public class PLayer // constructor 
{
	private String name;
	private Garden garden;
	public  PLayer (String name,int size) 
	{
		this.name=name;
		this.garden= new Garden(size);
	}

	public String getName() // getters get name
	{
		return name;
	}
	public int HowManyFlowerPossible() // number of flower we can place
	{
		return garden.countPossibleFlowers();
	}
	public int countPossibleTrees() // number of tree we can place
	{
		return garden.countPossibleTrees();
	}
	public char whatIsPlanted(int rLocation,int cLocation) // see what is planted at these place
	{
		return garden.getInLocation(rLocation, cLocation);
	}
	public void plantTreeInGarden(int rLocation, int cLocation) // plant a tree
	{
		garden.plantTree(rLocation, cLocation);
	}
	public void plantFlowerInGarden(int rLocation, int cLocation) // plant a flower
	{
		garden.plantFlower(rLocation, cLocation);
	}
	public void eatHere (int rLocation,int cLocation) // rabbit that remove flower or tree 1x1
	{
		garden.removeFlower(rLocation, cLocation);
	}
	public boolean isGardenFull() // see if garden full
	{
		return garden.gardenFull();
	}
	public String showGarden() // show us the garden
	{
		return garden.toString();
	}
}
