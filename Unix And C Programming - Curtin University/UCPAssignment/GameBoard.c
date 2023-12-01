#include <stdio.h>
#include <stdlib.h>
#include "GameBoard.h"

/*ReadBoardSize
IMPORTS: board, gBoard
EXPORTS: none
PURPOSE: to read width and height of board*/
int readBoardSize(FILE* board, GameBoard* gBoard)
{
	/*variable declarations*/
	int nRead = 0;
	int valid = TRUE;
	/*scan first line of board.txt*/
	nRead = fscanf(board,"%d,%d", &gBoard->width, &gBoard->height);
	/*if number of parameters are not 2*/
    if(nRead != 2)
    {
         fprintf(stderr,"Invalid number of board parameters.\n");
		 valid = FALSE;
		 
    }/*end if*/
    if(gBoard->width < 1 || gBoard->width > 12)
    {
         fprintf(stderr,"invalid width size.\n");
		 valid = FALSE;

	}/*end if*/
    if(gBoard->height < 1 || gBoard->height > 12)
    {
        fprintf(stderr,"invalid height size.\n");
		valid = FALSE;

	}/*end if*/

	/*adjust width and height to allow for other axis labels,etc*/
	gBoard->width = gBoard->width*2 + 2;
	gBoard->height = gBoard->height*2 + 1;

	return valid;

}/*end readBoardSize*/

/*initializeBoard
IMPORTS: gBoard
EXPORTS: none
PURPOSE: to add labels and spacings to board*/
void initializeBoard(GameBoard* gBoard)
{
	/*variable declarations*/
	char letterArray[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'}; 
	int i = 0, j = 1;

	/*loop through row of board*/
    for(i = 0; i < gBoard->height; i++)
    {
		/*loop through width of board*/
        for(j = 0; j < gBoard->width; j++)
        {
			/*set all index to '^'*/
            gBoard->board[i][j].mainBoard = '^';
        }/*end for*/
    }/*end for*/

	/*set j to equal 1*/
	j=1;
	/*loop through board row incrementing by 2*/
	for(i = 2; i < gBoard->height; i+=2)
    {
		/*set row label*/
        gBoard->board[i][0].mainBoard = (j) + '0';
		j++;
    }/*end for*/

	/*set j to 0*/
	j= 0;
	/*loop through board collum incrementing by 2*/
	for(i = 3; i < gBoard->width; i+=2)
	{
		/*set collum label*/
		gBoard->board[0][i].mainBoard = letterArray[j];
		j++;
		
	}/*end for*/

	/*loop through row of board incrementing by 2*/
	for (j = 1; j < gBoard->height; j+=2)
	{
		/*loop through collum of board*/
		for(i = 0; i < gBoard->width; i++)
		{
			/*set each 2nd row to equal =*/
			gBoard->board[j][i].mainBoard = '='; 
		}/*end for*/
	}/*end for*/

	/*loop through board collums incrementing by 2*/
	for (j = 2; j < gBoard->width; j+=2)
	{
		/*loop through board row*/
		for(i = 0; i < gBoard->height; i++)
		{
			/*set each 2nd collum to equal |*/
			gBoard->board[i][j].mainBoard = '|'; 
		}/*end for*/
	}/*end for*/

	/*loop through row of board incrementing by 2*/
	for (j = 1; j < gBoard->height; j+=2)
	{
		/*loop through board collums incrementing by 2*/
		for(i = 2; i < gBoard->width; i+=2)
		{
			/*set each index to +*/
			gBoard->board[j][i].mainBoard = '+';
		}
	}
	/*set first index to equal space*/
	gBoard->board[0][0].mainBoard = ' ';
	
	/*set 2nd row to equal empty to add space for 2 digit numbers*/
	for(j = 0; j < gBoard->height; j+=2)
	{
		gBoard->board[j][1].mainBoard = ' ';
	}
	
}/*end initializeBoard*/


/*displayGameBoard
IMPORTS: gBoard
EXPORTS: none
PURPOSE: to output game board to terminal*/
void displayGameBoard(GameBoard* gBoard)
{
	/*variable declarations*/
	int i  = 0, j = 0;

	/*loop through rows of board*/
	for(i = 0; i < gBoard->height; i++)
	{
		/*loop through collums of board*/
		for(j = 0; j < gBoard->width; j++)
		{		
			displayTile(i,j,gBoard);
		}/*end for*/

		/*change to new line*/
		printf("\n");

	}/*end for*/
}/*end displaygameboard*/

/*GameBoard
IMPORTS: gBoard
EXPORTS: none
PURPOSE: to initialize game board(board with is displayed on terminal)*/
void gameBoard (GameBoard* gBoard)
{
	/*variable declarations*/
	int i  = 0, j = 0;
	/*loop through rows of board*/
	for(i = 0; i < gBoard->height; i++)
	{
		/*loop through collums of board*/
		for(j = 0; j < gBoard->width; j++)
		{	
			/*initialize all gameboard variables to equal mainboard variables*/
			gBoard->board[i][j].gameBoard = gBoard->board[i][j].mainBoard;
			/*change all ship tile and empty tile to equal #*/
			if(gBoard->board[i][j].gameBoard == 'O')
			{
				gBoard->board[i][j].gameBoard = '#';
			}/*end if*/
			else if(gBoard->board[i][j].gameBoard == '^')
			{
				gBoard->board[i][j].gameBoard = '#';
			}/*end else if*/				
		}/*end for*/
	}/*end for*/
}/*end gameboard*/

/*displayBoard
IMPORTS: gBoard
EXPORTS: none
PURPOSE: to output main board to terminal*/
void displayBoard(GameBoard* gBoard)
{
	/*variable declarations*/
	int i = 0, j =0;
	/*loop through rows of board*/
    for(i = 0; i < gBoard->height; i++)
    {
		/*loop through width of board*/
    	for(j = 0; j <  gBoard->width; j++)
        {
			/*if on last collum*/
			if(j == ( gBoard->width-1))
            {
				/*output variable to terminal*/
                printf("%c\n",gBoard->board[i][j].mainBoard);
            }/*end if*/
			/*if on any other collum*/
            else
            {
				/*output variable to terminal*/
                printf("%c",gBoard->board[i][j].mainBoard);
            }/*end else*/
        }/*end for*/
    }/*end for*/
}/*end displayboard*/

/*displayTile
IMPORTS: i,j,gBoard
EXPORTS: none
PURPOSE: to print each individual tile*/
void displayTile(int i, int j, GameBoard* gBoard)
{
	/*if board is equal to a empty tile*/
	if(gBoard->board[i][j].gameBoard == '#')
	{
		displayHash(i,j,gBoard);
	}/*end if*/
	/*if tile has been hit and did not contain a ship*/
	else if(gBoard->board[i][j].gameBoard == 'X')
	{
		displayEmpty(i,j,gBoard);
	}/*end if*/
	/*if tile has been hit and contained a ship*/
	else if(gBoard->board[i][j].gameBoard == '0' && j > 2)
	{	
		displayShip(i,j,gBoard);
	}/*end if*/
	/*added fix for 2 digit chars below.*/
	/*if value equals : (10)*/
	/*output all remaining tiles*/
	else if(gBoard->board[i][j].gameBoard == ':')	
	{
		/*replace fist and second index to '1' and '0'*/
		gBoard->board[i][0].gameBoard = '1';
		gBoard->board[i][1].gameBoard = '0';
		/*output char to terminal*/
		printf("%c", gBoard->board[i][0].gameBoard);
	}
	/*if value equals ; (11)*/
	else if(gBoard->board[i][j].gameBoard == ';')
	{
		/*replace fist and second index to '1' and '1'*/
		gBoard->board[i][0].gameBoard = '1';
		gBoard->board[i][1].gameBoard = '1';
		/*output char to terminal*/
		printf("%c", gBoard->board[i][0].gameBoard);
	}
	/*if value equals ; (12)*/
	else if(gBoard->board[i][j].gameBoard == '<')
	{
		/*replace fist and second index to '1' and '1'*/
		gBoard->board[i][0].gameBoard = '1';
		gBoard->board[i][1].gameBoard = '2';
		/*output char to terminal*/
		printf("%c", gBoard->board[i][0].gameBoard);
	}
	/*output all remaining tiles*/
	else
	{
		printf("%c",gBoard->board[i][j].gameBoard);		
	}
				
}

/*displayHash
IMPORTS: i,j,gBoard
EXPORTS: none
PURPOSE: to output hash char to terminal*/
void displayHash(int i, int j, GameBoard* gBoard)
{
	/*if MONO is defined*/
	#ifdef MONO
		#ifndef DEBUG
			printf("%c", gBoard->board[i][j].gameBoard);
		#endif				
	#endif
	/*if DEBUG is defined*/
	#ifdef DEBUG
		#ifndef MONO	
			/*if main tile is empty space*/
			if(gBoard->board[i][j].mainBoard == '^')
			{
				printf(BLUE "%c" RESET, gBoard->board[i][j].gameBoard);						
			}	
			/*if main board tile contains a ship*/
			else if(gBoard->board[i][j].mainBoard == 'O')
			{
				printf(MAGENTA "%c" RESET, gBoard->board[i][j].gameBoard);
			}
		#endif					
	#endif
	/*if MONO and DEBUG are not defined*/
	#ifndef DEBUG
		#ifndef MONO 
			printf(BLUE "%c" RESET, gBoard->board[i][j].gameBoard);				
		#endif
	#endif

	#ifdef DEBUG
		#ifdef MONO
			/*if main tile is empty space*/
			if(gBoard->board[i][j].mainBoard == '^')
			{
				printf("%c", gBoard->board[i][j].gameBoard);						
			}	
			/*if main board tile contains a ship*/
			else if(gBoard->board[i][j].mainBoard == 'O')
			{
				printf(MAGENTA "%c" RESET, gBoard->board[i][j].gameBoard);
			}
		#endif
	#endif

}

/*displayEmpty
IMPORTS: i,j,gBoard
EXPORTS: none
PURPOSE: to output 'X' char to terminal*/
void displayEmpty(int i, int j, GameBoard* gBoard)
{
	/*if MONO defined*/
	#ifdef MONO
		printf("%c",gBoard->board[i][j].gameBoard);				
	#endif
	/*if MONO not defined*/
	#ifndef MONO
		printf(RED "%c" RESET, gBoard->board[i][j].gameBoard);
	#endif
}

/*displayShip
IMPORTS: i,j,gBoard
EXPORTS: none
PURPOSE: to output '0' char to terminal*/
void displayShip(int i, int j, GameBoard* gBoard)
{

	/*if MONO defined*/
	#ifdef MONO
		printf("%c", gBoard->board[i][j].gameBoard);				
	#endif
	/*if MONO not defined*/
	#ifndef MONO
		printf(GREEN "%c" RESET, gBoard->board[i][j].gameBoard);
	#endif
}
