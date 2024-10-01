#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

void shiftToStart(int* arr, int size, int shifts);

void main() {

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
		*(arr + i) = *(arr + k);
	}
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}
