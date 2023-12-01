#ifndef ASSIGNMENT_H
#define  ASSIGNMENT_H

#include "LinkedList.h"

typedef struct
{
	char mainBoard;
	char gameBoard;
}
tile; /*struct used to declare two char variables, struct was used as these two variables are compared constantly*/

typedef struct 
{
	tile** board; /*2d array which will be used to declare 2 simultanious 2d char arrays*/
	int height; /*stores height of board*/
	int width; /*stores widt hof board*/
	int shipCount; /*number of ships on the board*/
}GameBoard;

typedef struct
{
	char xy[3]; /*stores coordinates of ship*/
	char direction; /*stores direction of ship*/
	int length; /*stores length of ship*/
	char name[100]; /*stores name of ship*/
	int index[12][2]; /*stores row and collum numbers of where the ship was placed*/
	
}battleShip;

void gameMenu(GameBoard* gBoard, int missleCount, LinkedList* missleList,battleShip* shipArray);
void createMissileFile();
void createBoardFile();
void rerun(FILE* board, FILE* missle,LinkedList* missileList,char** argv,int* missileCount, GameBoard* gBoard);
void freeMallocs(GameBoard* gBoard,battleShip* shipArray, LinkedList* missileList, LinkedList* shipList, FILE* board, FILE* missile, int missileCount);
#endif
