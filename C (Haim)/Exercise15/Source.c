#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define R 5
#define C 4

void setMat(int* mat, int row, int col);
void printMat(int* mat, int row, int col);

void main() {
	int mat[R][C];
	srand(time(NULL));

	setMat(*mat, R, C);

	printMat(*mat, R, C);

}
void setMat(int *mat, int row, int col) {

	int j, i, max = 99, min = 10;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			*(mat + i * col + j) = rand() % (max - min + 1) + min;
		}
	}
}

void printMat(int *mat, int row, int col) {
	int i, j;

	for ( i = 0; i < row; i++)
	{
		for ( j = 0; j < col; j++)
		{
			printf("%2d ", *(mat + i * col + j));
		}
		printf("\n");
	}
}