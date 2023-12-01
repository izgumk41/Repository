#ifndef BATTLESHIP_H
#define BATTLESHIP_H

#define TRUE 1
#define FALSE 0

#include "assignment.h"
#include "LinkedList.h"

#define GREEN "\033[0;32m"
#define RESET "\033[0m"

void readBattleShip(FILE* board,  GameBoard* gBoard, LinkedList* shipList);
int gameOverCheck(GameBoard* gBoard);
void battleShipCheck(battleShip* ship,GameBoard* gBoard);
void insertBattleShip(LinkedList* shipList, GameBoard* gBoard, battleShip* shipArray);
int gameOverFinal(battleShip* shipArray, int shipCount);

/*this is a function pointer which points to the various addShip functions*/
typedef void (*shipFunc)(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);


void getShipCoordinates(battleShip* ship,char* x,char* y);
void getPosition(int* i, int* j, char* y, char* x, GameBoard* gBoard);
void addNorthShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
void addEastShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
void addWestShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
void addSouthShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
void removeBattleShipWest(int i, int j, int* k, int* intersected,GameBoard* gBoard);
void removeBattleShipEast(int i, int j, int* k, int* intersected,GameBoard* gBoard);
void removeBattleShipSouth(int i, int j, int* k, int* intersected,GameBoard* gBoard);
void removeBattleShipNorth(int i, int j, int* k, int* intersected,GameBoard* gBoard);
#endif
