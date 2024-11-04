#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 5
#define BLUE "\033[0;36m"
#define GREEN "\033[0;32m"
#define YELLOW "\033[0;33m"
#define RESET "\033[0m"

void swap(int* a, int* b);
void bigSwap(int* mat, int len);
void printMat(int* (mat)[N]);

void main() {
	int mat[N][N];
	srand(time(NULL));

	int j, i, max = 99, min = 10;
	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			mat[i][j] = rand() % (max - min + 1) + min;
		}
	}
	printMat(mat);

	bigSwap(*mat, N);

	printMat(mat);
}

void printMat(int mat[][N]) {
	int i, j;
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
	printf("--------------\n");
}

void bigSwap(int* mat, int len) {
	int i;

	for (i = 0; i < len; i++)
	{
		swap(mat + i * len + i, mat + i * len + len - 1 - i);
	}
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}