/*  Assignment 4
 *  Written by: Mohamed Abdul Mohamed, 40098785 30/11/18
 *  For COMP 248 Section (FF) – Fall 2018
 * It's a dice class that create the attribute we need to create a dice object
 */
import java.util.Random;
public class Dice 
{
	private int dice1;		// private attribute dice
	private int dice2;
	public Dice() 			//constructor
	{
		this.dice1=0;
		this.dice2=0;
	}
	public int getDice1() // getters 
	{
		return dice1;
	}
	public int getDice2() 
	{
		return dice2;
	}
	public int rollDice() // random the dice values
	{

		this.dice1= (int) (Math.random()*6+1);
		this.dice2= (int) (Math.random()*6+1);


		return (this.dice1+this.dice2);

	}
	public String toString() // to String method change
	{
		return "(Die 1: "+ this.dice1+" Die 2: "+this.dice2+")";
	}

}
