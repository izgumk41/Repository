#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "BattleShip.h"

/*readBattleShip
IMPORTS: board,gBoard,shipList
EXPORTS: none
PURPOSE: to read battleships from board file and then store into linklist*/
void readBattleShip(FILE* board, GameBoard* gBoard, LinkedList* shipList)
{

	/*variable decalarations*/
	int nRead = 0;
	gBoard->shipCount = 0;

	/*loop until end of file*/
	while((nRead != EOF))
    {
		/*malloc a new battleship*/
		battleShip* ship = (battleShip*)malloc(sizeof(battleShip));
		/*gets first variable from board.txt*/ 
		nRead = fscanf(board,"%s",ship[0].xy);
		/*if nread is not end of file*/
		if(nRead != EOF)
		{
			/*if improper number of parameters were read*/
          	if(nRead >= 0 && nRead != 1)
         	{
				/*throw error*/
             	perror("Proper number of parameters were not read in for ship coordinates. Therefore this ship will not be added to board.");   
				free(ship);

			}/*end if*/
			else
			{		
				/*scan next variable*/		
	    		nRead = fscanf(board," %c",&ship[0].direction);
				/*if improper number of parameters were read*/
        		if(nRead >= 0 && nRead != 1)
        		{
					/*throw error*/
            	 	perror("Proper number of parameters were not read in for ship direction. Therefore this ship will not be added to board.");   
				 	free(ship);

				}/*end if*/
				else
				{
					/*scan next variable*/		
    				nRead = fscanf(board,"%d",&ship[0].length);
					/*if improper number of parameters were read*/
           			if(nRead >= 0 && nRead != 1)
        			{
						/*throw error*/
    					perror("Proper number of parameters were not read in for ship length. Therefore this ship will not be added to board.");
						free(ship);   

					}/*end if*/
					else
					{
						/*if name is empty*/
						if(fgets(ship[0].name,101,board) == NULL)
						{
							/*throw error*/
							perror("Proper number of parameters were not read in for ship name. Therefore this ship will not be added to board."); 
							free(ship);
						}/*end if*/		
						else
						{		
							/*increment ship count*/
							gBoard->shipCount += 1;
							/*add ship struct to linklist*/
							insertLast(shipList,ship);
						}
					}
				}
			}
		}/*end if*/
		else
		{
			free(ship);
		}
        	
	}/*end while*/

}

/*insertBattleShip
IMPORTS: shipList,gBoard,shipArray
EXPORTS: none
PURPOSE: to add battleships from linklist into board and then store added battleships into an array*/
void insertBattleShip(LinkedList* shipList, GameBoard* gBoard, battleShip* shipArray)
{
	/*variable declarations*/
	int l = 0, i = 0, k = 0, ii = 0, j = 0;
	int staticShipCount = gBoard->shipCount;
	char x,y;
	int intersected = 1;
	char direction;
	battleShip* ship;	
	shipFunc shipMethod;

	/*loop through ship array*/
	for(l = 0; l < staticShipCount; l++)
	{
		/*reset intersected*/
		intersected = 1;
		/*reset ii to 0*/
		ii = 0;

		/*remove first node from linkedlist*/
		ship = removeStart(shipList);
		/*get x and y coordinates of ship*/
		getShipCoordinates(ship,&x,&y);
		/*convert x and y coordinates to index numbers*/
		getPosition(&i,&j,&y,&x,gBoard);

		/*if x and y coordinates were found*/
		if(i != 0 && j != 0)
		{
			/*get ship direction*/
			direction = ship[0].direction;
			/*make char upercase*/
			direction = toupper(direction);

			/*if ship is facing north*/
			if(direction == 'N')
			{
				/*initialize northship method to function pointer*/				
				shipMethod = &addNorthShip;
			}/*end */
			/*if ship is facing east*/
			else if(direction == 'E')
			{
				/*initialize eastship method to function pointer*/	
				shipMethod = &addEastShip;
			}/*end if*/
			/*if ship is facing south*/
			else if(direction == 'S')
			{	
				/*initialize southship method to function pointer*/	
				shipMethod = &addSouthShip;
			}/*end if*/
			/*if ship is facing west*/
			else if(direction == 'W')
			{
				/*initialize westship method to function pointer*/	
				shipMethod = &addWestShip;
			}/*end if*/
			else 
			{
				printf("direction is invalid. removing battleship: %s--------------------------------------------------------------------------------\n\n",ship[0].name);
				intersected = 2;
			}

			/*if direction is valid*/
			if(intersected != 2)
			{
				/*loop until k equals ship length*/
				for(k = 0; k <ship[0].length*2; k+=2)
				{
					/*call method of ship*/
					shipMethod(gBoard,ship,i,j,&k,&intersected,&ii);
				}/*end for*/
			}/*end if*/

			/*if battleship is valid*/
			if(intersected == 1)
			{
				/*pass by value ship to shiparray*/
				shipArray[l] = *ship;
				/*free ship*/
				free(ship);
			}
			/*else if battleship not valid*/
			else if(intersected == 2)
			{
				/*reduce ship count*/
				gBoard->shipCount -= 1;
				/*free ship*/
				free(ship);
			}

		}/*end if*/
		/*if coordinates were invalid*/
		else if(i == 0 && j == 0)
		{
			printf("coordinates are invalid. removing: %s--------------------------------------------------------------------------------\n\n",ship[i].name);
			free(ship);
		}
		
	}/*end for*/
}/*end readbattleship*/

/*insertBattleShip
IMPORTS: shipList,gBoard,shipArray
EXPORTS: none
PURPOSE: checks if all ships have been hit, by comparing the number of ships which have been reset to zero and the total number of ships*/
int gameOverFinal(battleShip* shipArray, int shipCount)
{
	/*variable declarations*/
	int i = 0;
	int count = 0;
	int gameOver = FALSE;
	/*loop through ships*/
	for( i = 0; i < shipCount; i++)
	{
		/*if ship length has been set to zero (ship length is set to 0 once battleship is destroyed)*/
		if(shipArray[i].length == 0)
		{
			count++;
		}

	}
	/*if count equals the total number of ships*/
	if(count == shipCount)
	{
		gameOver = TRUE;
	}

	return gameOver;

}

/*battleShipCheck
IMPORTS: shipList,gBoard,shipArray
EXPORTS: none
PURPOSE: checks if a battleship has been destroyed*/
void battleShipCheck(battleShip* ship,GameBoard* gBoard)
{
	/*variable declarations*/
	int i = 0, j = 0, count = 0;
	int x = 0;
	int y = 0;

	/*loop through ship array*/
	for(i = 0; i < gBoard->shipCount; i++)
	{
		/*ship length is not 0 (this is checked becuase if all tiles of ship have been hit the ship length will be set to 0)*/
		if(ship[i].length != 0)
		{	
			/*reset count to 0*/
			count = 0;
			/*loop through ship->index array*/
			for(j = 0; j < ship[i].length; j++)
			{
				/*intialize x and y to be index value of where ship is placed on board*/
				x = ship[i].index[j][0];
				y = ship[i].index[j][1];
				/*if ship has been hit on gameboard*/
				if(gBoard->board[x][y].gameBoard == '0')
				{
					/*increment count*/
					count++;
				}/*end if*/
			}/*end for*/

			/*if count is equal to size of ship(all tiles of ship have been hit)*/
			if(count == ship[i].length)
			{
				/*output to terminal*/
				#ifndef MONO
					printf(GREEN "\nYou've destroyed battleship: %s\n\n" RESET ,ship[i].name);
				#endif
				#ifdef MONO
					printf("\nYou've destroyed battleship: %s\n\n" ,ship[i].name);
				#endif
				/*set new length of ship to be 0*/
				ship[i].length = 0;
			}/*end if*/
		}/*end if*/
	}/*end for*/
}/*end battleshipchec*/

/*getShipCoordinates
IMPORTS: ship,x,y
EXPORTS: none
PURPOSE: gets ship coordinates from struct*/
void getShipCoordinates(battleShip* ship,char* x,char* y)
{
	int i = 0;

	/*initialize x coordinate*/
	*x = ship[0].xy[0];
	/*make char uppercase*/
	*x = toupper(*x);
	/*initialize y coordinate*/
	*y = ship[0].xy[1];
		
	/* if 10,11 or 12 was entered it will change it to corresponding char on main board*/
	if(ship[0].xy[2] == '0' && ship[0].xy[1] == '1')
	{
		*y = ':';
	}
	else if(ship[0].xy[2] == '1' && ship[0].xy[1] == '1')
	{
		*y = ';';
	}
	else if(ship[0].xy[2] == '2' && ship[0].xy[1] == '1')
	{
		*y = '<';
	}
	else
	{
		for(i = 0; i < 10; i++)
		{
			if(ship[0].xy[2] == i+'0')
			{
				*y = 0;
			}
		}
	}
}

/*getPosition
IMPORTS: i,j,y,x,gBoard
EXPORTS: none
PURPOSE: converts ships coordinates to index numbers*/
void getPosition(int* i, int* j, char* y, char* x, GameBoard* gBoard)
{
	int ii =0, jj = 0;
	for(ii = 0; ii < gBoard->height; ii++)
	{
		/*if y matches row index*/
		if(*y == gBoard->board[ii][0].mainBoard)
		{
			/*loop through collums*/
			for(jj = 0; jj < gBoard->width; jj++)
			{
				/*if x matches collym index*/
				if(*x == gBoard->board[0][jj].mainBoard)
				{
					*i = ii;
					*j = jj;
				}
			}
		}
	}

}

/*addNorthShip
IMPORTS: gBoard,ship,i,j,k,intersected,ii
EXPORTS: none
PURPOSE: adds battle ship to board*/
void addNorthShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii)
{
	/*if the index is out of bounds*/
	if((i+*k) > gBoard->height)
	{
		printf("\nBattleship is out of bounds, removing this battleship: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipNorth(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;		
	} 
	/*if battleship is colliding with another ship*/ 
	else if(gBoard->board[(i+*k)][j].mainBoard == 'O')
	{
		printf("\nBattleships intersecting, removing: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipNorth(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	else
	{
		/*change mainboard tile struct to equal O*/
		gBoard->board[(i+*k)][j].mainBoard = 'O';
		/*add index numbers of were the ship was inserted inside ship struct*/		
		ship[0].index[*ii][0] = i+*k;
		ship[0].index[*ii][1] = j;
		/*increment ii*/
		*ii = *ii + 1;
	}
}

/*addEastShip
IMPORTS: gBoard,ship,i,j,k,intersected,ii
EXPORTS: none
PURPOSE: adds battle ship to board*/
void addEastShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii)
{
	/*if index is out of bounds*/
	if((j-*k) <= 3)
	{
		printf("\nBattleship is out of bounds, removing this battleship: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipEast(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	/*if ship is colliding with another ship*/
	else if(gBoard->board[i][(j-*k)].mainBoard == 'O')
	{
		printf("\nBattleships intersecting, removing: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipEast(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	else
	{
		/*change mainboard tile struct to equal O*/
		gBoard->board[i][(j-*k)].mainBoard = 'O';
		/*add index numbers of were the ship was inserted inside ship struct*/
		ship[0].index[*ii][0] = i;
		ship[0].index[*ii][1] = j-*k;
		/*increment ii*/
		*ii = *ii + 1;
	}
}

/*addSouthShip
IMPORTS: gBoard,ship,i,j,k,intersected,ii
EXPORTS: none
PURPOSE: adds battle ship to board*/
void addSouthShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii)
{
	/*if index is out of bounds*/
	if((i-*k) <= 2)
	{
		printf("\nBattleship is out of bounds, removing this battleship: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipSouth(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;

	}
	/*if ship is colliding with another ship*/
	else if(gBoard->board[(i-*k)][j].mainBoard == 'O')
	{
		printf("\nBattleships intersecting, removing: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipSouth(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	else
	{
		/*change mainboard tile struct to equal O*/
		gBoard->board[(i-*k)][j].mainBoard = 'O';
		/*add index numbers of were the ship was inserted inside ship struct*/
		ship[0].index[*ii][0] = i-*k;
		ship[0].index[*ii][1] = j;
		/*increment ii*/
		*ii = *ii + 1;
	}
}

/*addWestShip
IMPORTS: gBoard,ship,i,j,k,intersected,ii
EXPORTS: none
PURPOSE: adds battle ship to board*/
void addWestShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii)
{
	/*if index is out of bounds*/
	if((j+*k) >= gBoard->width)
	{
		printf("\nBattleship is out of bounds, removing this battleship: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipWest(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	/*if ship is colliding with another ship*/
	else if(gBoard->board[i][(j+*k)].mainBoard == 'O')
	{
		printf("\nBattleships intersecting, removing: %s--------------------------------------------------------------------------------\n\n", ship[0].name);
		/*remove battleship from board*/
		removeBattleShipWest(i,j,k,intersected,gBoard);
		/*set k to ship length to end loop*/
		*k = ship[0].length*2;
	}
	else
	{
		/*change mainboard tile struct to equal O*/
		gBoard->board[i][(j+*k)].mainBoard = 'O';
		/*add index numbers of were the ship was inserted inside ship struct*/
		ship[0].index[*ii][0] = i;
		ship[0].index[*ii][1] = j+*k;
		/*increment ii*/
		*ii = *ii + 1;
	}
}

/*removeBattleShipWest
IMPORTS: i,j,k,intersected,gBoard
EXPORTS: none
PURPOSE: remove battleship from board*/
void removeBattleShipWest(int i, int j, int* k, int* intersected,GameBoard* gBoard)
{
	/*variable delcaration*/
	int r;
	/*initalize r to equal k (k repersents how many times the for loop has looped.)*/
	r = *k;
	/*while not on last index*/
	while(r != 0)
	{
		/*decrement r by 2*/
		r -= 2;
		/*reset main board to empty tile*/
		gBoard->board[i][(j+*k)].mainBoard = '^';
	}
	/*mark ship as invalid*/
	*intersected = 2;
}

/*removeBattleShipSouth
IMPORTS: i,j,k,intersected,gBoard
EXPORTS: none
PURPOSE: remove battleship from board*/
void removeBattleShipSouth(int i, int j, int* k, int* intersected,GameBoard* gBoard)
{
	/*variable delcaration*/
	int r;
	/*initalize r to equal k (k repersents how many times the for loop has looped.)*/
	r = *k;
	/*while not on last index*/
	while(r != 0)
	{
		/*decrement r by 2*/
		r -= 2;
		/*reset main board to empty tile*/
		gBoard->board[(i-r)][j].mainBoard = '^';
	}
	/*mark ship as invalid*/
	*intersected = 2;
}

/*removeBattleShipEast
IMPORTS: i,j,k,intersected,gBoard
EXPORTS: none
PURPOSE: remove battleship from board*/
void removeBattleShipEast(int i, int j, int* k, int* intersected,GameBoard* gBoard)
{
	/*variable delcaration*/
	int r;
	/*initalize r to equal k (k repersents how many times the for loop has looped.)*/
	r = *k;
	/*while not on last index*/
	while(r != 0)
	{
		/*decrement r by 2*/
		r -= 2;
		/*reset main board to empty tile*/
		gBoard->board[i][(j-r)].mainBoard = '^';
	}
	/*mark ship as invalid*/								
	*intersected = 2;
}

/*removeBattleShipNorth
IMPORTS: i,j,k,intersected,gBoard
EXPORTS: none
PURPOSE: remove battleship from board*/
void removeBattleShipNorth(int i, int j, int* k, int* intersected,GameBoard* gBoard)
{
	/*variable delcaration*/
	int r;
	/*initalize r to equal k (k repersents how many times the for loop has looped.)*/
	r = *k;
	/*while not on last index*/
	while(r != 0)
	{		
		/*decrement r by 2*/
		r -= 2;
		/*reset main board to empty tile*/
		gBoard->board[(i+r)][j].mainBoard = '^';
	}	
	/*mark ship as invalid*/							
	*intersected = 2;
}
