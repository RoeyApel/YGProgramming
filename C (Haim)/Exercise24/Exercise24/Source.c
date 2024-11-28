#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>

void printDividers(int num, int n);
int recGCF(int a, int b);
int fib(int n);

void main() {
	//printDividers(24, sqrt(24));

	//printf("%d", recGCF(30, 12));

	printf("%d",fib(6));
}

int fib(int n) {
	if (n == 0) {
		return 0;
	}
	if (n == 1 || n == 2) {
		return 1;
	}
	return fib(n - 1) + fib(n - 2);
}

int recGCF(int a, int b) {
	if (a != b) {
		if (a > b) {
			return recGCF(b, a - b);
		}
		else
		{
			return recGCF(a, b - a);
		}
	}
	return a;
}


void printDividers(int num, int n) {
	if (n != 1) {
		printDividers(num, n - 1);
		if (n * n == num) {
			printf("%d", n);
		}
		else if (num % n == 0) {
			printf("%d %d ", n, num / n);
		}
	}
	else
	{
		printf("1 %d ", num);
	}
}