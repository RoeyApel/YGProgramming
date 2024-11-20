#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#ifndef GAME_INC_FLAG
#define GAME_INC_FLAG
#include "Game.h"
#endif 


int step(Game* game, direction direction) {
	int stepX = 0, stepY = 0;
	if (direction == LEFT) {
		stepX = -1;
	}
	else if (direction == RIGHT) {
		stepX = 1;
	}
	else if (direction == TOP) {
		stepY = -1;
	}
	else if (direction == BUTTOM) {
		stepY = 1;
	}

	if (inBounds(game, game->playerX + stepX, game->playerY + stepY)) {

		*(game->board + game->playerY * game->cols + game->playerX) = EMPTY;

		game->playerX += stepX;
		game->playerY += stepY;

		*(game->board + game->playerY * game->cols + game->playerX) = PLAYER;

		return 1;
	}
	return 0;

}

int inBounds(Game* game, int x, int y) {
	return (x < game->cols && x >= 0) && (y < game->rows && y >= 0);
}

void clearScreen() {
	system("cls");
}

void init(Game* game, int rows, int cols) {
	game->board = (char*)malloc(rows * cols * sizeof(char));

	if (!game->board) {
		puts("error in alocation!");
		exit(1);
	}

	game->playerX = cols / 2;
	game->playerY = rows / 2;
	game->cols = cols;
	game->rows = rows;

	int i, j;
	for (i = 0; i < rows; i++)
	{
		for (j = 0; j < cols; j++)
		{
			*(game->board + i * cols + j) = EMPTY;
		}
	}

	*(game->board + game->playerY * cols + game->playerX) = PLAYER;
}

void printBoard(Game* game) {
	int i, j;
	for (i = 0; i < game->rows; i++)
	{
		for (j = 0; j < game->cols; j++)
		{
			printf("%c ", *(game->board + i * game->cols + j));
		}
		printf("\n");
	}
	printf("\n");
}

void freeGame(Game* game) {
	free(game->board);
	game->board = NULL;
}