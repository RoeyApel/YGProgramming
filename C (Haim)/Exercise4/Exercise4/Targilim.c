#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

// Functions declarations
unsigned long merge(unsigned int a, unsigned int b);
unsigned long reverse(unsigned int num);
float convertToC(float tempInF);
float convertToF(float tempInC);
void OddEven(int num);
int sumOfDigits(int num);
void maxSumOfDigits(int left, int right);
void printGodNums();
//----------------------------------------------------------------------
//								Targilim
//								--------
// 
// General : Store many unrelated functions
// 
// Input : Null 
// 
// Process : Many different random things
// 
// Output : Null
//----------------------------------------------------------------------
//----------------------------------------------------------------------
void main() {
}

void printGodNums() {
	for (int i = 10000; i < 100000; i++)
	{
		if (reverse(i) == i * 4) {
			printf("num: %d\n", i);
		}
	}
}

void maxSumOfDigits(int left, int right) {
	int max = -1, maxNum = 0;

	for (int i = left; i <= right; i++)
	{
		if (sumOfDigits(i) > max) {
			max = sumOfDigits(i);
			maxNum = i;
		}
	}
	printf("Max: %d", maxNum);
}

int sumOfDigits(int num) {
	int sum = 0;

	while (num != 0) {
		sum += num % 10;
		num /= 10;
	}
	return sum;
}

void OddEven(int num) {
	int balance = 0;

	while (num != 0) {
		balance += (num % 10) % 2 == 0 ? 1 : -1;
		num /= 10;
	}

	if (balance > 0) {
		printf("MORE EVEN");
	}
	else if (balance < 0) {
		printf("MORE ODDS");
	}
	else
	{
		printf("EQUAL");
	}
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
