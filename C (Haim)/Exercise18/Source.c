#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>

void main() {
	int len;
	printf("len: ");
	scanf("%d", &len);

	int* arr = malloc(len * sizeof(int));

	int i;
	for (i = 0; i < len; i++)
	{
		printf("num: ");
		scanf("%d", arr + i);
	}

	for (i = 0; i < len; i++)
	{
		printf("%d, ", *(arr + i));
	}

	int newLen;
	printf("len: ");
	scanf("%d", &newLen);

	int* arr2 = malloc(newLen * sizeof(int));

	int lenc;
	if (newLen <= len) {
		lenc = newLen;
	}
	else {
		lenc = len;
	}

	for (i = 0; i < lenc; i++)
	{
		*(arr2 + i) = *(arr + i);
	}

	if (newLen > len) {
		lenc = newLen - len;
	}
	else {
		lenc = 0;
	}

	for (i = len; i < newLen; i++)
	{
		printf("num: ");
		scanf("%d", arr2 + i);
	}

	for (i = 0; i < newLen; i++)
	{
		printf("%d, ", *(arr2 + i));
	}
}