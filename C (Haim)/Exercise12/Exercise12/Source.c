#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ROW 6
#define COL 5

void main() {
	int mat[5][5];
	srand(time(NULL));

	int h, k, max = 99, min = 10;
	for (int h = 0; h < 5; h++)
	{
		for (k = 0; k < 5; k++)
		{
			mat[h][k] = rand() % (max - min + 1) + min;
		}
	}
	int sum = 0;
	for ( k = 0; k < 5; k++)
	{
		sum+= mat[k][k];
		sum += mat[k][5 - k - 1];
	}
	if()
	printf("sum: %d\n", sum);

	//-------------------
	/*int mat[ROW][COL];
	srand(time(NULL));

	int h, k,max = 99, min = 10;
	for (int h = 0; h < ROW; h++)
	{
		for (k = 0; k < COL; k++)
		{
			mat[h][k] = rand() % (max - min + 1) + min;
		}
	}*/

	int i, j;
	int sumCol = 0, sumRow = 0;

	//for (i = 0; i < ROW - 1; i++)
	//{
	//	sumCol += mat[i][0];
	//	for (j = 0; j < COL - 1; j++)
	//	{
	//		sumRow += mat[i][j];
	//	}
	//	mat[i][j] = sumRow;
	//	mat[j][i] = sumCol;
	//	sumCol = sumRow = 0;
	//}


	for (int i = 0; i < 5; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			printf("%2d ", mat[i][j]);
		}
		printf("\n");
	}
}