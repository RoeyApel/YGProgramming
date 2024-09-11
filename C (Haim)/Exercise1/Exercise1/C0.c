#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>

float calculateAverage(int, int, int);
float calculateStandardDeviation(int num1, int num2, int num3, float average);
void swap(int*, int*);
void sort(int*, int*, int*);

void main() {
	int a, b, c;
	printf("num1: ");
	scanf("%d", &a);
	printf("num2: ");
	scanf("%d", &b);
	printf("num3: ");
	scanf("%d", &c);

	sort(a, b, c);
	printf("a: %d\nb: %d\nc: %d", a, b, c);

	//if (a < b) {
	//	a += b;
	//	b = a - b;
	//	a -= b;
	//}
	//if (a < c) {
	//	a += c;
	//	c = a - c;
	//	a -= c;
	//}
	//if (b < c) {
	//	b += c;
	//	c = b - c;
	//	b -= c;
	//}
	//printf("1: %d 2: %d\n", num1, num2);
	//printf("1: %d 2: %d", num1, num2);

	//float average = calculateAverage(num1, num2, num3);
	//float standardDeviation = calculateStandardDeviation(num1, num2, num3, average);

	//printf("%f\n", average);
	//printf("%f", standardDeviation);
}
float calculateAverage(int num1,int num2, int num3) {
	return (float)(num1 + num2 + num3) / 3.0;
}
float calculateStandardDeviation(int num1,int num2,int num3, float average) {
	float step1 = pow(num1 - average, 2) + pow(num2 - average, 2) + pow(num3 - average, 2);
	float step2 = step1 / 3;
	return sqrt(step2);
}
void swap(int* num1, int* num2) {
	int temp = *num1;
	*num1 = *num2;
	*num2 = temp;
}
void sort(int* a, int* b, int* c) {
	if (*a < *b) {
		*a += *b;
		*b = *a - *b;
		*a -= *b;
	}
	if (*a < *c) {
		*a += *c;
		*c = *a - *c;
		*a -= *c;
	}
	if (*b < *c) {
		*b += *c;
		*c = *b - *c;
		*b -= *c;
	}
}
