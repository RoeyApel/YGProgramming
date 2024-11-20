#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "Game.h"

void main() {
	Game game;
	init(&game, 4, 6);

	printBoard(&game);

	char userInput;
	printf("Type (w/a/s/d) to move or (e) to exit: ");
	scanf("%c", &userInput);

	while (userInput != 'e') {
		direction direction = tolower(userInput);
		step(&game, direction);

		clearScreen();


		printBoard(&game);

		printf("Type (w/a/s/d) to move or (e) to exit: ");
		scanf("%c", &userInput);
	}

	freeGame(&game);
}
