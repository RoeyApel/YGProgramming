#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

unsigned long merge(unsigned int a, unsigned int b);
unsigned long reverse(unsigned int num);

void main() {
	printf("%lu", merge(3456,89));
}

float convertToC(float tempInF) {
	return 5 * tempInF / 9 - (160.0 / 9.0);
}
float convertToF(float tempInC) {
	return tempInC * 5 / 9 + 32;
}

unsigned long merge(unsigned int a, unsigned int b) {
	int merge = 0;
	a = reverse(a);
	b = reverse(b);
	while (a != 0 || b != 0)
	{
		if (a != 0) {
			merge *= 10;
			merge += a % 10;
		}
		if (b != 0) {
			merge *= 10;
			merge += b % 10;
		}
		a /= 10;
		b /= 10;
	}
	return merge;
}

unsigned long reverse(unsigned int num) {
	int rNum = 0;
	while (num != 0) {
		rNum *= 10;
		rNum += num % 10;
		num /= 10;
	}
	return rNum;
}
