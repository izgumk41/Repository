#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <string.h>
#include "LinkedList.h"
#include "BattleShip.h"
#include "GameBoard.h"
#include "Missile.h"

int main(int argc,char* argv[])
{
	/*variable declarations*/
	/*file variables*/
	FILE* board;
	FILE* missile;
	/*gameboard variable*/
	/*GameBoard is a struct which contains 2d array of tile struct variable "board", an int variable "width", an int variable "height" and an int variable "shipCount"*/
	GameBoard gBoard;
	/*menu variables*/
	int choice = 0;
	/*used to store the number of missiles*/
	int missileCount = 0;
	/*coordanites stores user input inside menu*/
	char coordinates[4];
	/* i variable is declared for a for loop, gameOver is used to intialize the return value for gameOverFinal method*/
	int i = 0, gameOver = 0;
	/*check if used to initialize the return value for readMissile and readBoardSize method*/
	int check = 0;
	/*loop is used to determine if the game has been played before*/
	int loop = FALSE;
	/*missle variables*/
	LinkedList* missileList = createLinkedList();
	/*ship variables*/
	LinkedList* shipList = createLinkedList();
	/*an array of battleship struct, which is a struct that has 5 variables
	xy - is a char array variable
	direction - is a char variable
	length - is a int variable
	name - is a char array variable
	index - is a 2d int array variable*/
	battleShip* shipArray;

	/*invalid number of parameters.*/
	if(argc != 3)
	{
		printf("Enter 3 parameters.\nUsage: %s <board file> <missle file>",argv[0]);
	}
	else
	{
		/*open board.txt*/
		board = fopen(argv[1],"r");
		/*if unable to open board.txt*/
		if(board == NULL)
		{
			/*throw error*/
			perror("unable to open file");
		}/*endif*/
		else
		{
			/*open missle.txt*/
			missile = fopen(argv[2],"r");
			/*if unable to open missle.txt*/
			if(missile == NULL)
			{	
				/*throw error*/
				fclose(board);
				perror("unable to open missle file");
			}/*endif*/
			else
			{			
				/*get height and width of board located in GameBoard.c*/
				/*check is initialized to the return value of readboard*/
				/*check will either be 0 or 1 depending on if the file has valid width and height*/
				check = readBoardSize(board,&gBoard);
				/*if board size is valid*/
				if(check)
				{
					/*read in missles and add to linkedlist*/
					/*check is initialized to the return value of readmissile*/
					/*check will either be 0 or 1 dependding on if the file has valid missiles*/
					check = readMissile(missile,missileList,&missileCount);
					
					/*if missile file is valid*/
					if(check)
					{		
						/*starting menu*/
						printf("\nlets begin!\n----------\n");
						do
						{	
							/*outputs option to terminal*/
							printf("========================\n1. Play the game.\n-----------------------\n2. list all missiles.\n-----------------------\n3. create Board File\n-----------------------\n4. create Missile File\n-----------------------\n0. exit.\n");
							scanf(" %d", &choice);
							
							if(choice == 1)
							{
								/*if game has already been played, reset the gameboard.*/
								if(loop)
								{
									/*need to reopen file streams and intialize linkedlist*/
									board = fopen(argv[1],"r");
									missile = fopen(argv[2],"r");
									missileList = createLinkedList();
									shipList = createLinkedList();
									/*reset missle count*/
									missileCount = 0;
									/*add missles into new linkedlist*/
									readMissile(missile,missileList,&missileCount);
									/*re initialize board size*/
									readBoardSize(board,&gBoard);
								}
								
								/*malloc 2d struct array*/	
								/*tile is a struct which contains two char variables, gameBoard and mainboard*/
								/*it is used to create 2 simultanous 2d char arrays*/
								gBoard.board = (tile**)malloc(gBoard.height*sizeof(tile*));
								/*loop through rows of array and malloc struct arrays to eavch row*/
								for(i = 0; i < gBoard.height; i++)
        						{
									/*initialize each index to be a tile struct*/
        		    				*(gBoard.board+i) = (tile*)malloc(gBoard.width*sizeof(tile));
       	 						}				

								/*intialize board variables*/
 								initializeBoard(&gBoard);

								/*read battleship file and add to board.*/
								readBattleShip(board, &gBoard,shipList);

								/*malloc shiparray*/
								shipArray = (battleShip*)malloc(gBoard.shipCount*sizeof(battleShip));

								/*add battleship to board*/
								insertBattleShip(shipList,&gBoard,shipArray);
								
								/*initialize gameboard*/
								gameBoard(&gBoard);

								/*displays gameboard*/
								displayGameBoard(&gBoard);

								do
								{
									/*checks if all ships tiles have been hit*/
									gameOver = gameOverFinal(shipArray,gBoard.shipCount);
									/*if there is still ships on gameboard*/
									if(gameOver != 1)
									{
										/*remove first node from linklist*/
										/*a missle struct is a struct which has 3 variables, missle which is a char array, missilePtr which is a function pointer 
										and definition which is a 2d char array*/
										Missile* gameMissile = removeStart(missileList);

										/*print how many missles remain*/
										printf("\nmissiles left: %d\n",missileCount);

										/*print missles name*/
	 									printf("current missile: %s\n",gameMissile->missile);

										/*output to terminal to enter in coordinates*/
										printf("\nEnter next target:\n");

										scanf("%s",coordinates);

										/*if user enters "help"*/
										if(strcasecmp(coordinates,"help") == 0)
										{
											/*do while user enters "help"*/
											do
											{
												/*outputs missle definition*/
												printf("%s\n",gameMissile->definition);
												/*asks user to enter coordinates*/
												printf("\nEnter next target:\n");
												scanf("%s",coordinates);

											}while(strcasecmp(coordinates,"help") == 0);
										}
										/*calls missles function pointer*/
										gameMissile->missilePtr(coordinates,&gBoard);

										/*displays new gameboard*/
										displayGameBoard(&gBoard);

										/*decreases misslecount by 1*/				
										missileCount--;

										/*checks if any battleships were destroyed*/
										battleShipCheck(shipArray,&gBoard);
										/*allocated missle struct is freed*/
										free(gameMissile);
	
									}/*end if*/

								/*continue if missles or battleships left.*/
								}while(missileCount != 0 && gameOver == 0);
						
								/*if all battleships destryoed and missles used*/
								if(gameOver == 1 && missileCount == 0)
								{
									printf("========================\nwell done! game over\n");
								}
								/*if all battleship were destroyed*/
								else if(gameOver == 1)
								{
									printf("========================\nGame over! All ships destroyed.\n");
								}
								/*if all missles were used*/
								else if(missileCount == 0)
								{
									printf("========================\nAll missiles used, Game over.\n");
								}
								/*all allocated memory is freed*/
								freeMallocs(&gBoard,shipArray,missileList,shipList,board,missile,missileCount);
								/*loop is set to 0, so the program knows to reload all methods*/
								loop = TRUE;

							}
							/*output missles*/
							else if(choice == 2)
							{
								if(missileCount == 0)
								{
									printf("Missile list empty, all missiles used in game.\n\n");
								}
                                else if(loop)
                                {
                                    printf("Missile file has not been read.\n\n");
                                }
								else
								{
									/*calls printlinklist to output missles using function pointer printmissle*/
									printLinkedList(missileList,&PrintMissile);
									printf("\n");
								}
							}
							/*create board file*/
							else if(choice == 3)
							{
								createBoardFile();
							}
							/*create missle file*/
							else if(choice == 4)
							{
								createMissileFile();
							}
							/*exit*/
							else if(choice == 0)
							{
								printf("goodbye!\n");
							}/*end if*/
							/*if choice not valid*/
							else
							{
								printf("invalid choice. goodbye\n");
								/*set choice to 0 to exit loop*/
								choice = 0;
							}

						}while(choice != 0);
					}/*end if*/
				}/*end if*/
			}/*end else*/
		}/*end else*/	
	}/*end else*/
	return 0;
}/*end main*/

/*createBoardFile
IMPORT: none
EXPORT: none
PURPOSE: to create a valid board file*/
void createBoardFile()
{
	/*variable declarations*/
	FILE* outputBoard;
	char filename[100];
	int width, height;
	char shipname[100];
	char coordinates[3];
	char direction;
	int length;
	char menu;

	/*gets user to input file name*/
	printf("Enter filename: ");
	scanf("%s", filename);	
	/*opens output stream to file*/
	outputBoard = fopen(filename,"w");
				
	/*user inputs width of board*/
	printf("\nEnter board width: ");
	scanf("%d",&width);
	/*if width is valid*/
	if(width >= 1 && width <= 12)
	{
		/*user inputs height of board*/
		printf("\nEnter board height: ");
		scanf("%d",&height);
		/*if height is valid*/
		if(height >= 1 && height <= 12)
		{
			/*write width and height to file*/
			fprintf(outputBoard,"%d,%d\n",width,height);
			
			do
			{
				/*input name of ship*/
				printf("\nenter ship name: ");
				scanf("%s",shipname);
				/*input coordinates*/
				printf("\nenter %s coordinates: ",shipname);
				scanf("%s",coordinates);
				/*input direction*/
				printf("\nenter %s direction: ",shipname);
				scanf(" %c",&direction);
				/*input length*/
				printf("\nenter %s length: ",shipname);
				scanf(" %d",&length);
				/*write battleship to file*/
				fprintf(outputBoard,"%s %c %d %s\n",coordinates,direction,length,shipname);
				/*if user wants to add another ship*/
				printf("\nadd another ship?\n (y,n)\n");
				scanf(" %c",&menu);
			
			}while(menu == 'y');
		}
		/*if height is invalid*/
		else
		{
			printf("invalid height\n");
		}
	}
	/*if width is invalid*/
	else
	{
		printf("invalid width\n\n");
	}
			
	if(ferror(outputBoard))
    {
    	perror("error occured somewhere while writing board file.");
   	}
				   
	/*close file stream*/
	fclose(outputBoard);
	printf("\nfinished! saving to file: %s\n\n",filename);
}

/*createMissleFile
IMPORT: none
EXPORT: none
PURPOSE: to create a valid missle file*/
void createMissileFile()
{
	/*variable declarations*/
	FILE* outputBoard;
	char filename[100];
	int wMissile;
	int missileType;
	int i = 0;

	/*gets user to input file name*/
	printf("Enter filename: ");
	scanf("%s", filename);	
	/*opens output stream to file*/
	outputBoard = fopen(filename,"w");
	/*user enters amount of missles*/
	printf("Enter the amount of missles: ");
	scanf("%d",&wMissile);

	/*loop to amount of missles*/
	for(i = 0; i < wMissile; i++)
	{	
		/*outputs options*/
		printf("Choose a missile type:\n(1). single\n(2). splash\n(3). V-Line\n(4). H-Line\nchoice: ");
		scanf(" %d", &missileType);
		/*matches selection with missle and outputs to file*/
		if(missileType == 1)
		{
			fprintf(outputBoard,"single\n");
		}
		if(missileType == 2)
		{
			fprintf(outputBoard,"splash\n");
		}
		if(missileType == 3)
		{
			fprintf(outputBoard,"V-Line\n");
		}
		if(missileType == 4)
		{
			fprintf(outputBoard,"H-Line\n");
		}
	}/*end for*/
				
	if(ferror(outputBoard))
    {
    	perror("error occured somewhere while reading board file.");
	}
	/*close file stream*/
	fclose(outputBoard);
	printf("\nfinished! saving to file: %s\n\n",filename);
}

/*freeMalloc
IMPORT: gBaord,shipArray,missleList,shipList,board,missle
EXPORT: none
PURPOSE: to free all allocated memory*/
void freeMallocs(GameBoard* gBoard,battleShip* shipArray, LinkedList* missileList, LinkedList* shipList, FILE* board, FILE* missile, int missileCount)
{
	int i = 0;
	/*free malloced board*/
	for(i = 0; i < gBoard->height; i ++)
	{		
		free(gBoard->board[i]);
	}
	/*free board*/
	free(gBoard->board);
	
	/*free malloced ship array*/
	free(shipArray);

	/*free linkedlists*/
	if(missileCount == 0)
	{
		/*if missileList is empty*/
		/*this is to avoid double free*/
		freeLinkedList(missileList);
	}
	else
	{
		/*if missleList is not empty*/
		freeLinkedList2(missileList);
	}
	/*shipList will always be empty*/
	freeLinkedList(shipList);


	/*if error occcured reading board file*/
 	if(ferror(board))
   	{
       	perror("error occured somewhere while reading board file.");
	}
	/*if error occcured reading missle file*/
	if(ferror(missile))
   	{
    	 perror("error occured somewhere while reading missle file.");
    }	
	/*close file streams*/
	fclose(missile);
	fclose(board);
}
