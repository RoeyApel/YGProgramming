#define _CRT_SECURE_NO_WARNINGS
#define _CRT_NONSTDC_NO_WARNINGS

#include <stdio.h>
#include <conio.h>
#include<ctype.h>

#define N 20

void getArrInput(char* arr, int size);
void printArr(char* arr, int size);
void changeArr(char* arr, int size);
void changeArr2(char* arr, int size);
int intParse(char* arr, int size);
void tostring(int num, char* arr);
int strLen(char* str);
void strcpy(char* dest, char* sur);

void main() {
	char str[N] = { '0' };
	printArr(str, N);
	tostring(2344, str);
	puts("\n--");
	printArr(str, N);

}

int toString(int num, char* parr) {
	int index = 0;
	if (num / 10 == 0) {
		*parr = num + '0';
		return 1;
	}
	index = toString(num / 10, parr);
	*(parr + index) = (num % 10) + '0';
	return index + 1;

}

int strLen(char* str) {
	int i = 0;
	while (*(str + i)) {
		i++;
	}
	return i;
}

void strcpy(char* dest, char* sur) {
	int i = 0;

	while (*(sur + i))
	{
		*(dest + i) = *(sur + i);
		i++;
	}
	*(dest + i) = '\0';
}

int intParse(char* arr, int size) {
	int i, num = 0;

	for (i = 0; i < size; i++)
	{
		num = num * 10 + (*(arr + i) - '0');
	}
	return num;
}

void changeArr2(char* arr, int size) {
	char ch;

	int i;
	for (i = 0; i < size; i++)
	{
		ch = *(arr + i);
		if (isupper(ch)) {
			ch = tolower(ch);
		}
		else if (islower(ch)) {
			ch = toupper(ch);
		}
		else if (isdigit(ch))
		{
			if (ch == '9') {
				ch = '0';
			}
			else {
				ch++;
			}
		}
		*(arr + i) = ch;
	}
}

void changeArr(char* arr, int size) {
	char ch;

	int i;
	for (i = 0; i < size; i++)
	{
		ch = *(arr + i);
		if (ch >= 'A' && ch <= 'Z') {
			ch += 'a' - 'A';
		}
		else if (ch >= 'a' && ch <= 'z') {
			ch -= 'a' - 'A';
		}
		else if (ch >= '0' && ch <= '8')
		{
			ch++;
		}
		else if (ch == '9')
		{
			ch = '0';
		}
		*(arr + i) = ch;
	}
}

void getArrInput(char* arr, int size) {
	for (int i = 0; i < size; i++)
	{
		printf("Press a key to add to array!\n");

		*(arr + i) = getch();
		printf("\nYou pressed: %c\n", *(arr + i));
	}
}

void printArr(char* arr, int size) {
	for (int i = 0; i < size; i++)
	{
		putch(*(arr + i));
		putch(',');
	}
}
