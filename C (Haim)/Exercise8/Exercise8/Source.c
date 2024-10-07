#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int bSearch(int* arr, int size, int num);

void main() {

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