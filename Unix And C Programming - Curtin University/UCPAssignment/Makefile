cc = gcc
CFLAGS = -Wall -pedantic -ansi -Werror -g
OBJ = assignment.o GameBoard.o BattleShip.o Missile.o LinkedList.o
EXEC = assignment

$(EXEC): $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

ifdef DEBUG 
CFLAGS += -D DEBUG
DEBUG : clean $(EXEC)
endif

ifdef MONO
CFLAGS += -D MONO
MONO: clean $(EXEC)
endif

assignment.o: assignment.c assignment.h LinkedList.h
	$(CC) -c assignment.c $(CFLAGS) 

BattleShip.o: BattleShip.c BattleShip.h
	$(CC) -c BattleShip.c $(CFLAGS)


Missile.o: Missile.c Missile.h
	$(CC) -c Missile.c $(CFLAGS)

GameBoard.o: GameBoard.c GameBoard.h
	$(CC) -c GameBoard.c $(CFLAGS)

LinkedList.o: LinkedList.c LinkedList.h
	$(CC) -c LinkedList.c $(CFLAGS)

clean:
	rm -f $(EXEC) $(OBJ)




