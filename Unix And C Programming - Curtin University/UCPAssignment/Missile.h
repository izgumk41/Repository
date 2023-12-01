#ifndef MISSILE_H
#define MISSILE_H
#include "assignment.h"
#include "LinkedList.h"

#define FALSE 0
#define TRUE 1

#define RED "\033[0;31m"
#define GREEN "\033[0;32m"
#define BLUE "\033[1;34m"
#define RESET "\033[0m"

/*this is a function pointer which will be used to point to the various missile functions*/
typedef void (*MissilePtr)(char* coordinates,GameBoard* gBoard);

typedef struct
{
    char missile[100]; /*stores the name of the missile*/
    MissilePtr missilePtr; /*a function pointer to the 4 various Missile functions (singleMissile,splashMissile,vLineMissile,hLineMissile)*/
    char* definition; /*stores the definition of the missile*/

} Missile;

int readMissile(FILE* missile,LinkedList* missileList,int* missileCount);
int missileCheck(char* missileArray);
void singleMissile (char* coordinates, GameBoard* gBoard);
void splashMissile (char* coordinates, GameBoard* gBoard);
void vLineMissile (char* coordinates, GameBoard* gBoard);
void hLineMissile (char* coordinates, GameBoard* gBoard);
void PrintMissile(void* data);
void getCoordinates(char* coordinates, char* x, char* y, char* z);
void findPosition(int* i, int* j, char* x, char* y, char* z, GameBoard* gBoard);
void changeTile(GameBoard* gBoard, int k, int j);
void addSouthShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
void addWestShip(GameBoard* gBoard, battleShip* ship, int i,int j, int* k, int* intersected, int* ii);
#endif
