#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void strReverse(char* str);
void swapCh(char* a, char* b);
void strDelete(char* str, int place, int len);
void strJump(char* str, char* place);
void strInsert(char* str1, int place, char* str2);
void strEncrypt(char* str);

//---to be deleted---
void strcombine(char* s1, char* s2, char* s3) {
	char* temp;
	while (*s1) {
		for (temp = s2; *temp != *s1 && *(temp - 1) != 0; temp++);
		if (*temp == '\0') {
			*s3 = *s1;
			s3++;
		}
		s1++;
	}
	*s3 = 0;
}
//------------------

void main() {
	char one[] = "abflx", two[] = "baowexr", three[30] = { {0} };
	strcombine(one,two,three);
	strcombine(two, one, three + strlen(three) + 1);
	puts(three);
}

void strEncrypt(char* str) {
	char ch;

	while (*str) {
		ch = (char)*str;
		if (ch == ' ') {
			*str = '@';
			str++;
		}
		else {
			ch = toupper(ch);
			strJump(str, str + 1);
			*(str + 1) = ch;
			str += 2;
		}
	}
}

void strInsert(char* str1, int place, char* str2) {
	char* strTemp = str1 + place;
	char* str3[] = { 0 };
	strcpy(str3, strTemp);

	*(str1 + place) = '\0';
	strcat(str1, str2);
	strcat(str1, str3);
}

void strJump(char* str, char* place) {
	int len = strlen(str);
	if ((str + len) == place) {
		*(str + len) = ' ';
		*(str + len + 1) = '\0';
	}
	int i;

	for (i = len; (str + i) != place; i--)
	{
		*(str + i + 1) = *(str + i);
	}
	*(str + i + 1) = *(str + i);
	*(str + i) = ' ';
	*(str + len + 1) = '\0';
}

void strReverse(char* str) {
	int i, len = strlen(str);
	for (i = 0; i < len / 2; i++)
	{
		swapCh(str + i, str + len - 1 - i);
	}
}

void strDelete(char* str, int place, int len) {
	if (len == 0) {
		return;
	}
	int totalLen = strlen(str);

	int i;
	for (i = place + len; i < totalLen; i++)
	{
		*(str + i - len) = *(str + i);
	}
	*(str + totalLen - len) = 0;
}

void swapCh(char* a, char* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}