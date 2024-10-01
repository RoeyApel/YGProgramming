#define _CRT_SECURE_NO_WARNINGS
#define N 10
#include <stdio.h>

int sumArr(int* p, int size);
void doAndPrint();
int* maxArr(int* arr, int size);
void reverseArr(int* parr, int size);
void printArr(int* parr, int size);
void swap(int* a, int* b);
void bubbleSort(int* parr, int size);

void main() {
	int arr[N] = { 1,2,3,4,5,6,7,8,9,10 };

	printf("%d\n", sumArr(arr, N));
	printf("%d\n", *maxArr(arr, N));

	reverseArr(arr, N);
	printArr(arr, N);

	bubbleSort(arr, N);
	printArr(arr, N);
}

void bubbleSort(int* parr, int size) {
	int i, j;
	for (i = size - 1; i > 0; i--)
	{
		for (j = 0; j < i; j++)
		{
			if (*(parr + j) > *(parr + j + 1)) {
				swap(parr + j, parr + j + 1);
			}
		}
	}
}

void printArr(int* parr, int size) {
	for (int i = 0; i < size; i++)
	{
		printf("%d ", *(parr + i));
	}
	printf("\n");
}

void doAndPrint() {
	int arr[10] = { 0 };
	int size = sizeof(arr) / sizeof(arr[0]);
	int sum = 0;

	for (int i = 0; i < 5; i++)
	{
		printf("num %d: ", i);
		scanf("%d", &arr[i]);
		sum += arr[i];
	}

	arr[size - 1] = sum;

	for (int i = 0; i < size; i++)
	{
		printf("%d ", arr[i]);
	}
}

int sumArr(int* p, int size) {
	int sum = 0;

	for (int i = 0; i < size; i++)
	{
		sum += *(p + i);
	}
	return sum;
}

int* maxArr(int* arr, int size) {
	int* max = arr;

	for (int i = 1; i < size; i++)
	{
		if (*(arr + i) > *max) {
			max = (arr + i);
		}
	}
	return max;
}

void reverseArr(int* parr, int size) {
	for (int i = 0; i < size / 2; i++)
	{
		swap(parr + i, parr + size - 1 - i);
	}
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}
