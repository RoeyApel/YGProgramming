#pragma once

#define PLAYER '0'
#define EMPTY '#'

typedef enum { LEFT = 'a', RIGHT = 'd', TOP = 'w', BUTTOM = 's' } direction;

typedef struct {
	char* board;
	int rows;
	int cols;
	int playerX;
	int playerY;
} Game;

void init(Game* game, int rows, int cols);
void printBoard(Game* game);
void freeGame(Game* game);
int step(Game* game, direction direction);
int inBounds(Game* game, int x, int y);
void clearScreen();