#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

int intPharse(char* str);
int toString(int num, char* str);
void addSolution(char* str);

void main() {
	char str[100] = "159%10=";
	puts(str);
	addSolution(str);
	puts(str);
}

void addSolution(char* str) {
	int num1 = 0, num2 = 0;

	while (*str >= '0' && *str <= '9')
	{
		num1 = (num1 * 10) + (*str - '0');
		str++;
	}

	char op = *str;
	str++;

	while (*str >= '0' && *str <= '9')
	{
		num2 = (num2 * 10) + (*str - '0');
		str++;
	}

	int result;

	if (op == '+') {
		result = num1 + num2;
	}
	else if (op == '-') {
		result = num1 - num2;
	}
	else if (op == '*') {
		result = num1 * num2;
	}
	else if (op == '/') {
		result = num1 / num2;
	}
	else if (op == '%') {
		result = num1 % num2;
	}

	char resultStr[100] = { 0 };
	toString(result, resultStr);

	strcat(str, resultStr);
}

int toString(int num, char* str) {
	int digits = 0;

	if (num < 10) {
		*(str + digits) = num + '0';
		return 1;
	}
	digits = toString(num / 10, str);
	*(str + digits) = (num % 10) + '0';
	return digits + 1;
}

int intPharse(char* str) {
	int count = 0;
	int num = 0;

	while (*str)
	{
		num = (num * 10) + (*str - '0');
		count++;
		str++;
	}
	return num;
}