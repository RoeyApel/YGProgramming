#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverse(char* str);
void swapCh(char* a, char* b);

void main() {
	char str[] = "hellyo";
	reverse(str);
	puts(str);
}

void reverse(char* str) {
	int i, len = strlen(str);
	for (i = 0; i < len / 2; i++)
	{
		swapCh(str + i, str + len - 1 - i);
	}
}

void swapCh(char* a, char* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}