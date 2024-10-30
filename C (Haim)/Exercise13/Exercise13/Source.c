#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 5

void main() {
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
			printf("%2d ", mat[i][j]);
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
	printf("SumAM: %d, SumBM: %d", sumAboveMain, sumBelowMain);
}