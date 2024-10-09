#define _CRT_SECURE_NO_WARNINGS
#define _CRT_NONSTDC_NO_WARNINGS

#include <stdio.h>
#include <conio.h>
#include<ctype.h>

#define N 20

int bSearch(int* arr, int size, int num);
void swap(int* a, int* b);
void marge(int* pa, int sizeA, int* pb, int sizeB, int* pc);
void bubleSortByIndex(int** arr, int size);

void main() {

}

void bubleSortByIndex(int** arr, int size) {
	int i, j;
	for (i = size - 1; i > 0; i--)
	{
		for (j = 0; j < i; j++)
		{
			if (**(arr + j) > **(arr + j + 1)) {
				swap((arr + j), (arr + j + 1));
			}
		}
	}
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}

void marge(int* pa, int sizeA, int* pb, int sizeB, int* pc) {
	int i = 0, j = 0, k = 0;

	while (i < sizeA && j < sizeB) {
		*(pc + k) = *(pa + i) > *(pb + j) ? *(pb + j++) : *(pa + i++);
		k++;
	}

	for (j; j < sizeB; j++, k++)
	{
		*(pc + k) = *(pb + j);
	}

	for (i; i < sizeA; i++, k++)
	{
		*(pc + k) = *(pa + i);
	}

}

int bSearch(int* arr, int size, int num) {
	int first = 0, last = size - 1, mid;

	while (first <= last)
	{
		mid = (first = last) / 2;
		if (mid == num) {
			return 1;
		}
		if (num < mid) {
			last = mid - 1;
		}
		if (num > mid) {
			first = mid + 1;
		}
	}
	return 0;
}