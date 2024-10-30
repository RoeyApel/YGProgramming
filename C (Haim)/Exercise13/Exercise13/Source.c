#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 5

void main() {
	char color1[] = "\033[0;36m";
	char color2[] = "\033[0;32m";
	char color3[] = "\033[0;33m";
	char reset[] = "\033[0m";

	int mat[N][N];
	srand(time(NULL));

	int j, i, max = 7, min = 1;
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
				printf("%s%2d %s", color3, mat[i][j], reset);
			}
			else if (i == j) {
				printf("%s%2d %s", color1, mat[i][j], reset);
			}
			else if (j == N - i - 1) {
				printf("%s%2d %s", color2, mat[i][j], reset);
			}
			else {
				printf("%2d ", mat[i][j]);
			}
		}
		puts("");
	}

	int sumAboveMain = 0, sumBelowMain = 0, sumAboveSecond = 0, sumBelowSecond = 0;

	for (i = 1; i < N; i++)
	{
		for (j = 0; j < i; j++)
		{
			sumAboveMain += mat[i][j];
		}
	}

	for (i = 0; i < N - 1; i++)
	{
		for (j = 0; j < N - i - 1; j++)
		{
			sumBelowMain += mat[i][j];
		}
	}

	for (i = 0; i < N - 1; i++)
	{
		for (j = 0; j < N - i - 1; j++)
		{
			sumAboveSecond += mat[i][j];
		}
	}

	for (i = 1; i < N; i++)
	{
		for (j = 0; j < i; j++)
		{
			sumBelowSecond += mat[i][j];
		}
	}
	printf("SumAM: %d, SumBM: %d\n", sumAboveMain, sumBelowMain);
	printf("SumAS: %d, SumBS: %d\n", sumAboveSecond, sumBelowSecond);
}