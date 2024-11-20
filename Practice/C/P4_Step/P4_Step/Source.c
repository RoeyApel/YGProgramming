#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

#define PLAYER '0'
#define EMPTY '#'

typedef struct {
	char* board;
	int row;
	int col;
	int playerX;
	int playerY;
} Game;

void init(Game* game, int row, int col);
void printBoard(Game game);

void main() {
	Game game;
	init(&game,3,5);
	printBoard(game);
}

void init(Game* game, int row, int col) {
	game->board = (char*)malloc(row * col * sizeof(char));

	if (!game->board) {
		puts("error in alocation!");
		exit(1);
	}

	game->playerX = col / 2;
	game->playerY = row / 2;
	game->col = col;
	game->row = row;

	int i, j;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			*(game->board + i * col + j) = '#';
		}
	}

	*(game->board + game->playerX * col + game->playerY) = PLAYER;
}

void printBoard(Game game) {
	int i, j;
	for (i = 0; i < game.row; i++)
	{
		for (j = 0; j < game.col; j++)
		{
			printf("%c ", *(game.board + i * game.col + j));
		}
		printf("\n");
	}
}