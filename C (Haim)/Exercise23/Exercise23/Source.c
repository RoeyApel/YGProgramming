#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int countOns(unsigned int num);
int zerosInARow(unsigned int num);
void printCZR(int* arr, int size);
void putBitZ(int* arr, unsigned short num1, unsigned short num2);

void main() {
	int output = zerosInARow(0xffff8e80);
	printf("%d", output);
}

void putBitZ(int* arr, unsigned short num1, unsigned short num2) {
	int i, mask = 0x1;

	for (i = 0; i < 16; i++)
	{
		*(arr + i) = !((num1 & mask) ^ (num2 & mask));
		num1 >>= 1;
		num2 >>= 1;
	}
}

void printCZR(int* arr, int size) {
	int maxOns = -1, maxOnsNum;
	int maxZR = -1, maxZRNum;

	int i, num, c, r;

	for (i = 0; i < size; i++)
	{
		num = *(arr + i);
		c = countOns(num);
		r = zerosInARow(num);

		if (c > maxOns) {
			maxOns = c;
			maxOnsNum = num;
		}
		if (r > maxZR) {
			maxZR = r;
			maxZRNum = num;
		}

	}

	printf("%d, %d", maxOnsNum, maxOns);
	printf("%d, %d", maxZRNum, maxZR);
}

int countOns(unsigned int num) {
	int mask = 0x1;

	int i, count = 0;

	for (i = 0; i < sizeof(num) * 8; i++)
	{
		count += num & mask;
		num >>= 1;
	}
	return count;
}

int zerosInARow(unsigned int num) {
	int mask = 0x1;

	int i, count = 0, max = -1;

	for (i = 0; i < sizeof(num) * 8; i++)
	{
		if (!(num & mask)) {
			count++;
		}
		else {
			if (count > max) {
				max = count;
			}
			count = 0;
		}
		num >>= 1;
	}
	return (count > max) ? count : max;
}