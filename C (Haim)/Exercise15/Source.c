#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <limits.h>

#define R 6
#define C 5
#define N 5

void setMat(int* mat, int row, int col);
void outputMat(int* mat, int row, int col);
void inputMat(int* mat, int row, int col);
int sumAround(int(*p)[C], int Rows, int Cols, int i, int j);
int sumAround2(int* p, int Rows, int Cols, int i, int j);
void swap(int* a, int* b);
void bubblesSort(int* arr, int size);
int binSearch(int* arr, int size, int num);
void pirnt(int* arr, int size, int num, int row);
void q5(int mat[R][C]);
int isSymmetric(int* mat, int size);
void sortMatLines(int(*mat)[C], int rows, int cols);
int isUnitMat(int* mat, int size);
int isSurroundingSmaller(int* mat, int rows, int cols, int x, int y);
int glowing(int(*pMat)[C], int rows, int cols, int** pAddr);
void printGlowing(int** pAddr, int size, int* startAddress, int rows, int cols);


void main() {
	int mat[R][C];
	int* addr[2 * C] = { NULL };

	setMat(*mat, R, C);
	outputMat(*mat, R, C);

	int sizeA = glowing(mat, R, C, addr);
	printGlowing(addr, sizeA, *mat, R, C);
}

void printGlowing(int** pAddr, int size, int* startAddress, int rows, int cols) {
	int i, * address, num, location, x, y;

	printf("GLOWING:\n");

	if (size == 0) {
		printf("None\n");
		return;
	}

	for (i = 0; i < size; i++)
	{
		address = *(pAddr + i);
		location = (address - startAddress);
		x = location / cols;
		y = location % cols;
		num = *address;
		printf("(%d,%d): %2d\n", x, y, num);
	}
	printf("\n");
}

int glowing(int(*pMat)[C], int rows, int cols, int** pAddr) {
	int i, j, k = 0;

	for (i = 1; i < rows - 1; i++)
	{
		for (j = 1; j < cols - 1; j += 2)
		{
			if (isSurroundingSmaller(*pMat, rows, cols, i, j)) {
				*(pAddr + k) = *(pMat + i) + j;
				k++;
			}
			else if (j + 1 < cols - 1 && isSurroundingSmaller(*pMat, rows, cols, i, j + 1)) {
				*(pAddr + k) = *(pMat + i) + j + 1;
				k++;
				j++;
			}
		}
	}
	return k;
}

int isSurroundingSmaller(int* mat, int rows, int cols, int x, int y) {
	int num = *(mat + x * cols + y);
	*(mat + x * cols + y) = INT_MIN;
	int i, j;

	for (i = x - 1; i <= x + 1; i++)
	{
		for (j = y - 1; j <= y + 1; j++)
		{
			if (num <= *(mat + i * cols + j)) {
				*(mat + x * cols + y) = num;
				return 0;
			}
		}
	}
	*(mat + x * cols + y) = num;
	return 1;
}

int isUnitMat(int* mat, int size) {
	int i, j;

	for (i = 0; i < size; i++)
	{
		for (j = 0; j < size; j++)
		{
			if (i == j) {
				if (*(mat + i * size + j) != 1)
					return 0;
			}
			else {

			}
		}
	}
}

void sortMatLines(int(*mat)[C], int rows, int cols) {
	int i, j;

	for (i = 0; i < rows; i++)
	{
		bubblesSort(*(mat + i), cols);
	}
}

int isSymmetric(int* mat, int size) {
	int i, j;

	for (i = 0; i < size; i++)
	{
		for (j = 1; i - j >= 0 && i - j < size; j++)
		{
			if (*(mat + (i - j) * size + i) != *(mat + i * size + j - 1)) {
				return 0;
			}
		}
	}
	return 1;
}

void q5(int mat[R][C]) {
	setMat(*mat, R, C);

	int i, num;
	printf("NUM: ");
	scanf("%d", &num);

	for (i = 0; i < R; i++)
	{
		pirnt(*(mat + i), C, num, i);
	}

	outputMat(*mat, R, C);
}
void pirnt(int* arr, int size, int num, int row) {
	bubblesSort(arr, size);
	int index = binSearch(arr, size, num);
	if (index == -1) {
		printf("NO!\n");
	}
	else
	{
		printf("(%d,%d)\n", row, index);
	}
}

void bubblesSort(int* arr, int size) {
	int i, j;

	for (i = size - 1; i > 0; i--)
	{
		for (j = 0; j < i; j++)
		{
			if (*(arr + j) > *(arr + j + 1)) {
				swap(arr + j, arr + j + 1);
			}
		}
	}
}

int binSearch(int* arr, int size, int num) {
	int first = 0, last = size - 1, mid;

	while (first <= last)
	{
		mid = (last + first) / 2;

		if (num > *(arr + mid))
		{
			first = mid + 1;
		}
		else if (num < *(arr + mid)) {
			last = mid - 1;
		}
		else {
			return mid;
		}
	}
	return -1;

}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}

int sumAround(int(*p)[C], int Rows, int Cols, int i, int j) {
	int k, h, sum = 0;

	for (k = i - 1; k < i + 2; k++)
	{
		for (h = j - 1; h < j + 2; h++)
		{
			sum += (k < 0 || h < 0 || k > Rows || h > Cols) ? 0 : *(*(p + k) + h);
		}
	}
	return sum - *(*(p + i) + j);
}

int sumAround2(int* p, int Rows, int Cols, int i, int j) {
	int k, h, sum = 0;

	for (k = i - 1; k < i + 2; k++)
	{
		for (h = j - 1; h < j + 2; h++)
		{
			sum += (k < 0 || h < 0 || k > Rows || h > Cols) ? 0 : *(p + k * Cols + h);
		}
	}
	return sum - *(p + i * Cols + j);
}

void setMat(int* mat, int row, int col) {
	srand(time(NULL));

	int j, i, max = 45, min = 10;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			*(mat + i * col + j) = rand() % (max - min + 1) + min;
		}
	}
}

void inputMat(int* mat, int row, int col) {
	int i, j;

	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			printf("(%d,%d): ", i, j);
			scanf("%d", mat + i * col + j);
		}
	}
}

void outputMat(int* mat, int row, int col) {
	int i, j;

	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			printf("%2d ", *(mat + i * col + j));
		}
		printf("\n");
	}
	printf("-----------\n");
}