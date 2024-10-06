#define _CRT_SECURE_NO_WARNINGS
#define N 10

#include <stdio.h>

void shiftToStart(int* arr, int size, int shifts);

void main() {
	int arr[N] = { 1,2,3,4,5,6,7,8,9,10 };
	shiftToStart(arr, N, 5);

	for (int i = 0; i < N; i++)
	{
		printf("%d ", *(arr + i));
	}
}

void shiftToStart(int* arr, int size, int shifts) {
	int i;
	int sarr[100] = { 0 };

	for (i = 0; i < shifts; i++)
	{
		*(sarr + i) = *(arr + i);
	}

	for (i = shifts; i < size; i++)
	{
		*(arr + i - shifts) = *(arr + i);
	}

	int k;
	for (k = 0, i = size - shifts; i < size; k++, i++)
	{
		*(arr + i) = *(sarr + k);
	}
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}