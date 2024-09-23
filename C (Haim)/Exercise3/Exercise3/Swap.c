#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

void swap(int* a, int* b);

void main() {
	int a = 9, b = 4;
	swap(&a, &b); 
	printf("a: %d b: %d", a, b);
}

void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}

