#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 5
#define BLUE "\033[0;36m"
#define GREEN "\033[0;32m"
#define YELLOW "\033[0;33m"
#define RESET "\033[0m"

void main() {
	int mat[N][N];
	srand(time(NULL));

	int j, i, max = 99, min = 10;
	for (int i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			mat[i][j] = rand() % (max - min + 1) + min;
		}
	}

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			if (i == N / 2 && j == N / 2) {
				printf("%s%2d %s", YELLOW, mat[i][j], RESET);
			}
			else if (i == j) {
				printf("%s%2d %s", BLUE, mat[i][j], RESET);
			}
			else if (j == N - i - 1) {
				printf("%s%2d %s", GREEN, mat[i][j], RESET);
			}
			else {
				printf("%2d ", mat[i][j]);
			}
		}
		puts("");
	}

	int sumAboveMain = 0, sumBelowMain = 0, sumAboveSecond = 0, sumBelowSecond = 0;

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			if (i < j) {
				sumAboveMain += mat[i][j];
			}
			if (i > j){
				sumBelowMain += mat[i][j];
			}
			if (i < N - j - 1) {
				sumAboveSecond += mat[i][j];
			}
			if (i > N  - j - 1) {
				sumBelowSecond += mat[i][j];
			}
		}
	}

	printf("--------------------\n");
	printf("SumAM: %d, SumBM: %d\n", sumAboveMain, sumBelowMain);
	printf("SumAS: %d, SumBS: %d\n", sumAboveSecond, sumBelowSecond);
	printf("--------------------\n");

}