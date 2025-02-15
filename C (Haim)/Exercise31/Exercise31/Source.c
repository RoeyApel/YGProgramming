#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
void encrypt(char* sourceFile, char* encryptFile, int key);

int main() {
	return 0;
}

void encrypt(char* source_filename, char* encrypt_filename, int key) {
	FILE* source_file = fopen(source_filename, "rt");

	if (!source_file) {
		puts("error!");
		exit(3);
	}

	char** data;

	while(fgetc(source_file))// Todo!!!
}

void create_sorted_arr(int* arr, int* size) {
	*size = 0;
	*arr = NULL;

	int num;

	puts("num: (or -1 to cancel)");
	scanf("%d", &num);

	while (num >= 0) {
		arr = (int*)realloc(arr, ++(*size) * sizeof(int));
		*(arr + *size - 1) = num;
		puts("num: (or -1 to stop)");
		scanf("%d", &num);
	}

	qsort(arr, *size, sizeof(int), int_cmp);
}

int delete_num(int* arr, int* size, int key) {
	int* pnum = bsearch(&key, arr, *size, sizeof(int), int_cmp);

	if (!pnum) return 0;

	while (*(pnum - 1) == key) {
		pnum--;
	}

	int count = 1;
	while (*(++pnum) == key) count++;

	int i;
	for (i = 0; i < *size; i++)
	{
		*(pnum - count + i) = *(pnum + i);
	}

	arr = (int*)realloc(arr, (*size - count) * sizeof(int));
	*size -= count;
	return 1;
}

int int_cmp(void* a, void* b) {
	return *((int*)a) - *((int*)b);
}