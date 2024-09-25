#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

void smart(int* sumptr, int* multptr, int* bigptr, int a, int b);
void sumAndDifference(int* a, int* b);
void sort3Nums(int* a, int* b, int* c);
void swap(int* a, int* b);

void main() {
	int a, b, sum, mul, bigest;
	printf("num1:");
	scanf("%d", &a);
	printf("num2:");
	scanf("%d", &b);

	smart(&sum, &mul, &bigest, a, b);

	printf("sum: %d, mul: %d, biggest: %d", sum, mul, bigest);

	sumAndDifference(&a, &b);
	printf("\n%d, %d", a, b);

	int a2, b2, c2;
	
	printf("\n\nnum1:");
	scanf("%d", &a2);
	printf("num2:");
	scanf("%d", &b2);
	printf("num3:");
	scanf("%d", &c2);

	sort3Nums(&a2, &b2, &c2);

	printf("a2 = %d, b2 = %d, c2 = %d", a2, b2, c2);
}

void sort3Nums(int* a, int* b, int* c) {
	if (*a < *b) {
		swap(a, b);
	}
	if (*a < *c) {
		swap(a, c);
	}
	if (*b < *c) {
		swap(b, c);
	}
}
void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}

void sumAndDifference(int* a, int* b) {
	int temp;
	temp = *a + *b;
	*b = *a - *b;
	*a = temp;
}

void smart(int* sumptr, int* multptr, int* bigptr, int a, int b) {
	*sumptr = a + b;
	*multptr = a * b;
	*bigptr = a > b ? a : b;
}

