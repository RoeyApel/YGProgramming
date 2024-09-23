#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int perfectNum(int num);
int countPerfect();

void main2() {
	printf("%d",countPerfect()); 
}

int countPerfect() {
	int i, num, count = 0;

	for (i = 0; i < 10; i++)
	{
		scanf("%d", &num);
		count += perfectNum(num);
	}
	return count;
}

int perfectNum(int num) {
	int sum = 1;
	int i;
	for (i = 2; i * i < num; i++)
	{
		if (num % i == 0) {
			sum += i + num / i;
		}
	}
	if (i * i == num) {
		sum += i;
	}
	return sum == num;
}