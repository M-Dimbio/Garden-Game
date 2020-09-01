/*  Assignment 4
 *  Written by: Mohamed Abdul Mohamed, 40098785 30/11/18
 *  For COMP 248 Section (FF) – Fall 2018
 * It's a game that can be play up to 10 players with any size of a NxN board. The person who have a full garden first win */
import java.util.Scanner;
public class Lets_play 
{
	public static void main(String[] args) // general rules
	{
		System.out.println("\t\t\t\t***************************************************");
		System.out.println("\t\t\t\t\tWelcome to Crazy Mohamed's Garden Game!");
		System.out.println("\t\t\t\t***************************************************");
		System.out.println("\nTo win this game you need some luck with the dice and a bit of strategy.\r\n" + 
				"Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\r\n" + 
				"You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.\r\n" + 
				"Rolls and their outcome:\r\n\n" + 
				"---------------------\r\n" + 
				"3: plan a tree (2x2) and a flower (1x1)\r\n" + 
				"6: plant 2 flowers (2 times 1x1)\r\n" + 
				"12: plant 2 trees (2 times 2x2)\r\n" + 
				"5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)\r\n" + 
				"Any other EVEN rolls: plant a tree (2x2)\r\n" + 
				"Any other ODD rolls: plant a flower (1x1)\r\n\n" + 
				"Minimum number of players: 2.\r\n" + 
				"Minimum garden size: 3x3.\r\n" + 
				"You can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\r\n" + 
				"and I will check to make sure all 4 locations are free.\r\n" + 
				"Okay .. Let's start the game! May the best gardener win!!!");
		System.out.print("\nThe default garden size is a 3x3 square. You can use this default board size or change the size\r\n" + 
				"Enter 0 to use the default garden size or -1 to enter your own size:");
		Scanner kb=new Scanner(System.in);				//Scanner 
		int boardSize;
		int indice=0;
		int numbersPlayers;
		PLayer [] players  ;
		Dice dice[];
		boolean evaluate=true;
		boardSize=kb.nextInt();
		String playerName=" ";
		if(boardSize==-1|| boardSize==0) 	// Choosing either the default size of board or a custom one
		{
			evaluate=false;
		}
		while(evaluate)
		{
			if(boardSize==-1 || boardSize==0) 
			{
				evaluate=false;
				boardSize=3;
			}
			else
				System.out.print("Sorry but "+boardSize+ " is not a legal choice. Enter your choice:");
			boardSize=kb.nextInt();
		}
		System.out.print("How many gardeners will there be (minimum 2 required to play, max allowed 10)?");  // how many players
		evaluate=true;
		numbersPlayers=kb.nextInt();
		if(numbersPlayers>=2 && numbersPlayers<=10) // controlling the numbers of players
		{
			evaluate=false;
		}
		while(evaluate)			// if numbers of players incorrect
		{
			if(numbersPlayers>=2 && numbersPlayers<=10) 
			{
				evaluate=false;
			}
			else 
			{
				System.out.println("** Sorry but "+numbersPlayers+ " is not a legal number of players. ");
				System.out.print("How many gardeners will there be (minimum 2 required to play, max allowed 10)?");
				numbersPlayers=kb.nextInt();
			}
		}
		System.out.println("Great,"+ numbersPlayers+" players it will be!");
		players = new PLayer [numbersPlayers];
		if (boardSize==0) 
		{
			for (int i=0;i<numbersPlayers;i++) 	// initializing players[] array
 			{
				boardSize=3;
				System.out.print("Name of player "+(i+1) +" (no spaces allowed):");
				playerName=kb.next();
				PLayer name=new PLayer(playerName,boardSize);
				players [i]=name;
		
			}

		}
		else			// choose your grid size
		{
			System.out.print("What size board would you like?(minimum size 3):");
			boardSize=kb.nextInt();
			evaluate=true;
			if(boardSize>=3) 
			{
				evaluate=false;
			}
			while(evaluate)
			{
				if(boardSize>=3) 
				{
					evaluate=false;
				}
				else
					System.out.println("** Sorry but "+boardSize+ " is not a legal number of grid. ");
				boardSize=kb.nextInt();
			}
			for (int i=0;i<numbersPlayers;i++) 		//name of players
			{
				System.out.print("Name of player "+(i+1) +" (no spaces allowed):");
				playerName=kb.next();
				PLayer name=new PLayer(playerName,boardSize);
				players [i]=name;
				System.out.println();
			}
		}
		Dice rollValue= new Dice();		// creating dice object 
		dice = new Dice [players.length];
		int [] numbersRoll= new int [players.length];  
		System.out.println("Let's see who goes first\n");
		for (int i=0;i<players.length;i++)   		// Initializing dice[] array
 		{
			dice[i]=rollValue;
		}

		do // who start first
		{
			int max=0;
			int sameRoll=0;
			evaluate=true;

			for(int i=0;i<players.length;i++) 
			{
				dice[i].rollDice();
				numbersRoll[i]=(dice[i].getDice1()+dice[i].getDice2());
				System.out.println(players[i].getName()+" rolled "+ numbersRoll[i]+"\n");

			}
			for(int i=0;i<players.length;i++) 
			{
				if(numbersRoll[i]>max) 
				{
					max=numbersRoll[i];
					indice=i;
					evaluate=false;

				}

			}
			for(int j=0;j<players.length ;j++)  // repeat numbers start again
			{
				for(int p=j;p<players.length;p++) 
				{
					if(p+1<players.length)
						if(numbersRoll[j]==numbersRoll[p+1]) 
						{
							evaluate=true;
							sameRoll=numbersRoll[j];
						}
				}
			}
			if (evaluate==true)
				System.out.println(" We will start over as "+sameRoll+ " was rolled by a as well.\n ");
		}while(evaluate);

		System.out.println(players[indice].getName()+" go first ");
		PLayer [] sortPlayer= new PLayer[players.length];   // creating another Player array to store a new array sorted

		for(int i=0;i<sortPlayer.length;i++) // initializing sortPlayer array
		{
			PLayer temp=new PLayer(players[i].getName(),boardSize);
			sortPlayer[i]=temp;

		}


		for(int i=0;i<players.length;i++,indice++) // sort array
		{
			if (indice==players.length) 
			{
				indice=0;
			}
			sortPlayer[i]=players[indice];  
		}
		System.out.println("\nTime to play!!!!\r\n" + 
				"------------------\n");
		int count=0;
		do 
		{
			for(int i=0; i<players.length;i++) 	// start of the game 
			{
				if (i!=0) 
				{
					System.out.println();
				}
				boolean noPlaceTree=false;
				boolean winner=false;
				evaluate=true;
				int row;
				int column;

				dice[i].rollDice();
				numbersRoll[i]=(dice[i].getDice1()+dice[i].getDice2());
				if (numbersRoll[i]==3)    // first rule
				{
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString()+"");
					System.out.println("You must plant a tree (2x2) and a flower(1x1)");
					System.out.println(sortPlayer[i].showGarden());
					System.out.println("You have "+sortPlayer[i].HowManyFlowerPossible()+" places to plant a flower and "+sortPlayer[i].countPossibleTrees()+" places to plant a tree");
					System.out.print("Insert the coordinate for the tree 2x2 row first and column next:");
					do{
						if (sortPlayer[i].countPossibleTrees()==0) // verifying if possible to plant or skip a turn 
						{
							noPlaceTree=true;
							System.out.println("** Sorry no room left to plant a tree");
							break;
						}

						
						row=kb.nextInt();
						column=kb.nextInt();
						if((row+1)>=boardSize || (column+1)>=boardSize) // looking if it's off grid
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your tree will be off the grid. Try again ");
							continue;

						}
						else if ((row+1)<boardSize&& (column+1)<boardSize)   // looking if there's somthing else in this location
						{

							if (sortPlayer[i].whatIsPlanted(row, column)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column)!='-'&& sortPlayer[i].whatIsPlanted(row, column+1)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column+1)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: ");
								continue;
							}else // if free spot inside the grid plant
							{	
								sortPlayer[i].plantTreeInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}
						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) 
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								
								
								if (w==players.length-1) 
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}

						System.out.println("Insert the coordinate for the flower 1x1 row first and column next:"); // second thing to plant
					}while(evaluate);
					
					do 
					{
						evaluate=true;
						row=kb.nextInt();
						column=kb.nextInt();
						if((row)>=boardSize || (column)>=boardSize) // if it's off grid
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your flower will be off the grid. Try again ");
							continue;

						}
						else if ((row)<boardSize&& (column)<boardSize) // if there's something else
						{
							if (sortPlayer[i].whatIsPlanted(row, column)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: ");
								continue;
							}
							else 	// if free spot inside the grid plant
							{	
								sortPlayer[i].plantFlowerInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}

						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) // show all board at the end
							{
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) 
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								
								if (w==players.length-1) // present the winner
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}

					}while(evaluate);

				}
				else if(numbersRoll[i]==6)  //second rule
				{
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString()); // values
					System.out.println("You must plant 2 flower (1x1)\n");
					System.out.println(sortPlayer[i].showGarden());
					System.out.println("You have "+sortPlayer[i].HowManyFlowerPossible()+" places to plant a flower ");
					System.out.print("Insert the coordinate for the first flower 1x1 row first and column next:");
					do 
					{
						evaluate=true;
						row=kb.nextInt();
						column=kb.nextInt();
						if((row)>=boardSize || (column)>=boardSize)
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your flower will be off the grid. Try again ");// look if off grid
							continue;

						}
						else if ((row)<boardSize&& (column)<boardSize) 
						{
							if (sortPlayer[i].whatIsPlanted(row, column)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: "); // look for spot if free
								continue;
							}
							else // plant if all the condition met
							{	
								sortPlayer[i].plantFlowerInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}

						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{
								
								
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) // show all board
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								
								if (w==players.length-1) // present the winner
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}
					}while(evaluate);
					System.out.print("Insert the coordinate for the 2nd flower 1x1 row first and column next:"); // second object to plant
					do 
					{
						
						evaluate=true;
						row=kb.nextInt();
						column=kb.nextInt();
						if((row)>=boardSize || (column)>=boardSize)
						{
							System.out.println("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your flower will be off the grid. Try again "); // if off grid
							continue;

						}
						else if ((row)<boardSize&& (column)<boardSize) 
						{
							if (sortPlayer[i].whatIsPlanted(row, column)!='-') 
							{
								System.out.println("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: "); // free spot
								continue;
							}
							else // met all the condition plant
							{	
								sortPlayer[i].plantFlowerInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}

						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{
								 if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;
									
								}
								if(winnerIndex<players.length) // show all the board
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								if (w==players.length-1) 
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}
					}while(evaluate);
				}
				else if(numbersRoll[i]==12) // third rule
				{
					noPlaceTree=false;
					evaluate=true;
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString());
					System.out.println("You must plant 2 tree (2x2)\n");
					System.out.println(sortPlayer[i].showGarden());
					System.out.println("You have "+sortPlayer[i].countPossibleTrees()+" places to plant a tree ");
					System.out.print("Insert the coordinate for the first tree 2x2 row first and column next:");
					do{
						if (sortPlayer[i].countPossibleTrees()==0) // look for if enough space available for tree
						{
							noPlaceTree=true;
							System.out.println("** Sorry no room left to plant a tree");
							break;
						}
						
						row=kb.nextInt();
						column=kb.nextInt();
						if((row+1)>=boardSize || (column+1)>=boardSize)
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your tree will be off the grid. Try again "); //look if off grid
							continue;

						}
						else if ((row+1)<boardSize&& (column+1)<boardSize) 
						{

							if (sortPlayer[i].whatIsPlanted(row, column)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column)!='-'&& sortPlayer[i].whatIsPlanted(row, column+1)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column+1)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: "); //look for free space
								continue;
							}else // plant if all condition met
							{	
								sortPlayer[i].plantTreeInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}
						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{	
								 if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;
									
								}
								if(winnerIndex<players.length) // show boards at the end
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								
								if (w==players.length-1) // present winner
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}

					}
					while(evaluate);
					System.out.print("Insert the coordinate for the 2nd tree 2x2 row first and column next:"); //plant second object
					do{
						if (sortPlayer[i].countPossibleTrees()==0)  // if space available to plant a tree
						{
							noPlaceTree=true;
							System.out.println("** Sorry no room left to plant a tree - You miss a turn");
							break;
						}
						
						row=kb.nextInt();
						column=kb.nextInt();
						if((row+1)>=boardSize || (column+1)>=boardSize) //if off grid
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your tree will be off the grid. Try again");
							continue;

						}
						else if ((row+1)<boardSize&& (column+1)<boardSize) // if there's nothing else
						{

							if (sortPlayer[i].whatIsPlanted(row, column)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column)!='-'&& sortPlayer[i].whatIsPlanted(row, column+1)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column+1)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates: ");
								continue;
							}else 
							{	
								sortPlayer[i].plantTreeInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}
						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{
								
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) 
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");// show all board
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								if (w==players.length-1) //present the winner
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}


					}while(evaluate);	
				}
				else if(numbersRoll[i]==5 || numbersRoll[i]==10) // rabbit in action
				{
					evaluate=false;
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString());// values
					System.out.println("The rabbit will eat (1x1)\n");
					System.out.println(sortPlayer[i].showGarden());
					for(int j=0; j<boardSize;j++) 
					{
						for(int p=0;p<boardSize;p++)// look if there's something in the board or if is empty
						{

							if (sortPlayer[i].whatIsPlanted(j, p)=='-') 
							{
								continue;
							}
							else if(sortPlayer[i].whatIsPlanted(j, p)!='-') // the board is not empty break
							{
								evaluate=true;
								break;
							}
						}
						if (evaluate) 
						{
							break;
						}

					}
					if (evaluate==false) // if the board is empty
						System.out.println("Empty board the rabbit didn't found anything ");
					while (evaluate) // randomly the rabbit eat a spot
					{
						row=(int)Math.random()*boardSize;
						column=(int)Math.random()*boardSize;
						if (sortPlayer[i].whatIsPlanted(row, column)=='f'||sortPlayer[i].whatIsPlanted(row, column)=='t')
						{
							sortPlayer[i].eatHere(row, column);
							evaluate=false;
							System.out.println("The rabbit ate whatever was planted in location ("+row+","+column+")");
							System.out.println(sortPlayer[i].showGarden());
						}
						else continue;
					}
				}
				else if (numbersRoll[i]%2==0) // pair rule
				{
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString());
					System.out.println("You must plant 1 tree (2x2)\n");
					System.out.println(sortPlayer[i].showGarden());
					System.out.println("You have "+sortPlayer[i].countPossibleTrees()+" places to plant a tree ");
					System.out.print("Insert the coordinate for the tree 2x2 row first and column next:");
					do{
						if (sortPlayer[i].countPossibleTrees()==0) 
						{
							noPlaceTree=true;
							System.out.println("** ** Sorry no room left to plant a tree - You miss a turn");// miss a turn no space
							break;
						}
						
						evaluate=true;
						row=kb.nextInt();
						column=kb.nextInt();
						if((row+1)>=boardSize || (column+1)>=boardSize)
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your tree will be off the grid. Try again");
							continue;

						}
						else if ((row+1)<boardSize&& (column+1)<boardSize) 
						{

							if (sortPlayer[i].whatIsPlanted(row, column)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column)!='-'&& sortPlayer[i].whatIsPlanted(row, column+1)!='-'&& sortPlayer[i].whatIsPlanted(row+1, column+1)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a '"+sortPlayer[i].whatIsPlanted(row, column)+"' Please enter a new set of coordinates:");
								continue;
							}else 
							{	
								sortPlayer[i].plantTreeInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}
						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{	
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) 
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								if (w==players.length-1) 
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
						}


					}while(evaluate);
				}
				else // if numberRoll is odd
				{
					System.out.println(""+sortPlayer[i].getName()+" you rolled "+numbersRoll[i] +" "+dice[i].toString());
					System.out.println("You must plant 1 flower (1x1)\n");
					System.out.println(sortPlayer[i].showGarden());
					System.out.println("You have "+sortPlayer[i].HowManyFlowerPossible()+" places to plant a flower ");
					System.out.print("Insert the coordinate for the flower 1x1 row first and column next:");

					do 
					{
						
						evaluate=true;
						row=kb.nextInt();
						column=kb.nextInt();
						if((row)>=boardSize || (column)>=boardSize)
						{
							System.out.print("Sorry either the row or column is not in the range of 0 to "+boardSize+" or your flower will be off the grid. Try again");
							continue;

						}
						else if ((row)<boardSize&& (column)<boardSize) 
						{
							if (sortPlayer[i].whatIsPlanted(row, column)!='-') 
							{
								System.out.print("** Sorry that location is already taken up by a "+sortPlayer[i].whatIsPlanted(row, column)+" Please enter a new set of coordinates: ");
								continue;
							}
							else 
							{	
								sortPlayer[i].plantFlowerInGarden(row, column);
								System.out.println(sortPlayer[i].showGarden());
								evaluate=false;

							}

						}
						if(sortPlayer[i].isGardenFull()) 		// look for a winner
						{
							System.out.println("FINAL RESULTS\r\n" + 
									"-------------");

							System.out.println("Here are the gardens after "+count+" rounds.");
							for(int w=0,winnerIndex=i;w<=players.length;w++) 
							{
								if (winnerIndex+1>players.length) 
								{
									winnerIndex=0;	
								}
								if(winnerIndex<players.length) 
								{
									System.out.println(sortPlayer[winnerIndex].getName()+"'s garden");
									System.out.println(sortPlayer[winnerIndex].showGarden());
								}
								
								if (w==players.length-1) 
								{
								System.out.println("And the winner is ....."+ sortPlayer[i].getName()+"!!!!!\r\n" + 
										"What a beautiful garden you have.\n");
								System.out.println("Hope you had fun!!!!");
								kb.close();
								System.exit(0);
								}
								winnerIndex=winnerIndex+1;
							}
							
						}
					}while(evaluate); 
				}
				count=count+1; // count the number of round
			}
		}while(true);// if the system did not exit there's no winner go back on top and do again the for loop
		
	}
}
