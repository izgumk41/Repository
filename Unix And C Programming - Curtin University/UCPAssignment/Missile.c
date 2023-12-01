#include <stdio.h>
#include <stdlib.h>
#include "Missile.h"
#include <string.h>
#include <ctype.h>
#include <strings.h>


/*readMissle
IMPORTS: missle,missleList,missleCount
EXPORTS: none
PURPOSE: to read from missle file and then add missles to linkedlist*/
int readMissile(FILE* missile,LinkedList* missileList,int* missileCount)
{
	/*variable declarations*/
	int i = 0, nRead = 0;
	char* splash = "splash";
	char* single = "single";
	char* vline = "V-Line";
	char* hLine = "h-Line";
	int count = TRUE;

	do
	{
		/*dynamically allocate a missile struct*/
		Missile* missileAdd = (Missile*)malloc(sizeof(Missile));

		/*read line of missle.txt file*/
		nRead = fscanf(missile,"%s", missileAdd[i].missile);
		/*if line is not empty*/
		if(nRead != EOF)
		{
			/*calls missleCheck method to check missle type*/
			if(missileCheck(missileAdd[i].missile))
			{
				/*checks the missle type, then assign the corresponding pointer function*/
				if(strcasecmp(missileAdd[i].missile,splash) == 0)
				{
					missileAdd[i].missilePtr = &splashMissile;
					missileAdd[i].definition ="This missile will destroy a 3x3 square of tiles with the center being the entered coordinates.";
				}/*end if*/
				else if(strcasecmp(missileAdd[i].missile,single) == 0)
				{
					missileAdd[i].missilePtr = &singleMissile;
					missileAdd[i].definition = "This missile will destory a single tile on the board. choose your coordinates wisely.";
				}/*end if*/
				else if(strcasecmp(missileAdd[i].missile,vline) == 0)
				{
					missileAdd[i].missilePtr = &vLineMissile;
					missileAdd[i].definition = "This missile will destory the entire row of the entered coordinates.";
				}/*end if*/
				else if(strcasecmp(missileAdd[i].missile,hLine) == 0)
				{
					missileAdd[i].missilePtr = &hLineMissile;
					missileAdd[i].definition = "This missile will destory the entire collum of the entered coordinates.";
				}/*end if*/
				/*inserts missile struct into linkedlist*/
				insertLast(missileList,&missileAdd[i]);
				/*increments number of missles*/
				*missileCount += 1;
			}/*end if*/
			else
			{
				printf("Missle %s is invalid and has been removed.\n",missileAdd[i].missile);
				count = FALSE;
			}		
		}/*end if*/
		else
		{
			free(missileAdd);
		}

	}while(nRead != EOF);

	return count;
}/*end read missile*/

/*PrintMissile
IMPORTS: data
EXPORTS: none
PURPOSE: to be used as a function pointer for printlinkedlist*/
void PrintMissile(void* data)
{
	char* missle;
	/*declare a missle struct pointer*/
	Missile* missileTemp = (Missile*)malloc(sizeof(Missile));
	/*typecast data to missle struct then assign to missleTemp*/
	missileTemp = (Missile*) data;
	/*store name of missle into variable*/
	missle = missileTemp->missile;
	/*output to terminal*/
	printf("%s\n",missle);
}/*end printmissle*/

/*missleCheck
IMPORTS: missleArray
EXPORTS: none
PURPOSE: checks if missle is valid*/
int missileCheck(char* missileArray)
{
	/*declare variable check*/
	int check = FALSE;
	/*if missle type is equal to H-Line,V-Line,Single or splash check will equal true*/
	if (strcasecmp(missileArray,"H-Line") == 0 || strcasecmp(missileArray,"single") == 0 || strcasecmp(missileArray,"V-Line") == 0 ||  strcasecmp(missileArray,"splash") == 0)
	{
		check = TRUE;
	}/*end if*/
	/*returns check*/
	return check;
}/*end missle check*/

/*singleMissile
IMPORTS: coordinates,gBoard
EXPORTS: none
PURPOSE: change single char variable on board*/
void singleMissile (char* coordinates, GameBoard* gBoard)
{
	/*variable declarations*/
	int i =0, j = 0;

	/*stores first index of corodanites array into variable x*/
	char x,y,z;
	
	getCoordinates(coordinates,&x,&y,&z);
	findPosition(&i,&j,&x,&y,&z,gBoard);

	if(i != 0 && j != 0)
	{
		changeTile(gBoard,i,j);
		
	}/*end if*/
	else
	{
		printf("invalid coordinate.\n");
	}
	

}/*end singlemissle*/

/*vLineMissile
IMPORTS: coordinates,gBoard
EXPORTS: none
PURPOSE: change row of char variables on board*/
void vLineMissile (char* coordinates, GameBoard* gBoard)
{
	/*variable declarations*/
	int i =0, j = 0, k = 0;

	/*stores first index of corodanites array into variable x*/
	char x,y,z;
	
	getCoordinates(coordinates,&x,&y,&z);
	findPosition(&i,&j,&x,&y,&z,gBoard);

	if(i != 0 && j != 0)
	{
		for(k = 1; k < gBoard->height; k++)
		{
			changeTile(gBoard,k,j);
		}/*end for*/
	}
	else
	{
		printf("invalid coordinate.\n");
	}
	
}/*end vLineMissle*/

/*vLineMissile
IMPORTS: coordinates,gBoard
EXPORTS: none
PURPOSE: change row of char variables on board*/
void hLineMissile (char* coordinates, GameBoard* gBoard)
{

	/*variable declarations*/
	int i =0, j = 0, k = 0;

	/*stores first index of corodanites array into variable x*/
	char x,y,z;
	
	getCoordinates(coordinates,&x,&y,&z);
	findPosition(&i,&j,&x,&y,&z,gBoard);

	if(i != 0 && j != 0)
	{
		/*k will loop through collums of gameboard while i will be used a constant for row number*/
		for(k = 1; k < gBoard->width; k++)
		{
			changeTile(gBoard,i,k);
		}
	}
	else
	{
		printf("invalid coordinate.\n");
	}

}/*end hlinemissle*/

/*splashMissile
IMPORTS: coordinates,gBoard
EXPORTS: none
PURPOSE: change 3x3 char variables on board*/
void splashMissile (char* coordinates, GameBoard* gBoard)
{
	/*variable declarations*/
	int i =0, j = 0, m = 0, l = 0;

	/*stores first index of corodanites array into variable x*/
	char x,y,z;
	
	getCoordinates(coordinates,&x,&y,&z);
	findPosition(&i,&j,&x,&y,&z,gBoard);

	if(i != 0 && j != 0)
	{
		/*check is splash missle is being used on last row.*/
		if(i == (gBoard->height-1))
		{
			/*declare m to increment between rows to 2 incrementations*/
			for(m = -2; m < 2; m+=2)
			{
				/*declare l to increment between collums to 3 incrementations*/
				for(l = -2; l < 4; l+=2)
				{
					changeTile(gBoard,(i+m),(j+l));
				}
			}
		}
		else
		{
			/*declare m to increment between rows to 3 incrementations*/
			for(m = -2; m < 4; m+=2)
			{
				/*declare l to increment between collums to 3 incrementations*/
				for(l = -2; l < 4 ; l+=2)
				{
					changeTile(gBoard,(i+m),(j+l));
				}/*end for*/
			}/*end for*/
		}
	}
	else
	{
		printf("invalid coordinate.\n");
	}
				
}/*end splashmissle*/

/*getCoordinates
IMPORTS: coordinates,x,y,z
EXPORTS: none
PURPOSE: to split coordinates to x and y char*/
void getCoordinates(char* coordinates, char* x, char* y, char* z)
{

	/*stores first index of corodanites array into variable x*/
	*x = coordinates[0];
	
	/*stores second index of coordinates array into variable y*/
	*y = coordinates[1];

	if(coordinates[2] == '0' || coordinates[2] == '1' || coordinates[2] == '2')
	{
		*z = coordinates[2];	
	}
	else
	{
		*z = ' ';
	}

	/*converts variable to upperCase*/
	*x = toupper(*x);
}

/*findPosition
IMPORTS: i,j,x,y,z,gBoard
EXPORTS: none
PURPOSE: to convert x and y char to int index numbers*/
void findPosition(int* i, int* j, char* x, char* y, char* z, GameBoard* gBoard)
{
	int ii = 0, jj = 0;
	for(ii = 0; ii < gBoard->height; ii++)
	{
		/*if y coordinate matches row index on board*/
		if(*y == gBoard->board[ii][0].gameBoard && *z == gBoard->board[ii][1].gameBoard)
		{
			/*loops through collums of gameBoad*/
			for(jj = 0; jj < gBoard->width; jj++)
			{
				/*if x coordinate matches collum index on board*/
				if(*x == gBoard->board[0][jj].gameBoard)
				{
					*i = ii;
					*j = jj;
				}
			}
		}
	}
}

/*ChangeTile
IMPORTS: gBoard,k,j
EXPORTS: none
PURPOSE: to change char variable on board*/
void changeTile(GameBoard* gBoard, int k, int j)
{
	/*if mainboard index variable is an empty*/
	if(gBoard->board[k][j].mainBoard == '^')
	{
		/*change gameboard to X to display empty tile*/
		gBoard->board[k][j].gameBoard = 'X';
	}/*end if*/
	/*if mainboard index variable contains a ship*/
	if(gBoard->board[k][j].mainBoard == 'O')
	{
		/*change gameboard to 0 to display ship tile*/
		gBoard->board[k][j].gameBoard = '0';
	}/*end if*/
}
