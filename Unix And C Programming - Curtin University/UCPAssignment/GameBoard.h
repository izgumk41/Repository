#ifndef GAMEBOARD_H
#define GAMEBOARD_H

#include "assignment.h"
#define RED "\033[0;31m"
#define GREEN "\033[0;32m"
#define BLUE "\033[1;34m"
#define RESET "\033[0m"
#define MAGENTA "\033[1;35m"

#define FALSE 0
#define TRUE 1


int readBoardSize(FILE* board, GameBoard* gBoard);
void displayBoard(GameBoard* gboard);
void initializeBoard(GameBoard* gBoard);
void gameBoard(GameBoard* gBoard);
void displayGameBoard(GameBoard* gBoard);
void displayTile(int i, int j, GameBoard* gBoard);
void displayShip(int i, int j, GameBoard* gBoard);
void displayEmpty(int i, int j, GameBoard* gBoard);
void displayHash(int i, int j, GameBoard* gBoard);

#endif
