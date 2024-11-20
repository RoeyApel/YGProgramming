#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
void writeToFile(char* filename);
void readFromFile(char* filename);
void updateFile(char* filename);

void main() {
	char filename[100];
	printf("path+filename: ");
	gets(filename);
	writeToFile(filename);

	readFromFile(filename);

	updateFile(filename);

	readFromFile(filename);
}

void updateFile(char* filename) {
	FILE* pf = fopen(filename, "r+b");

	if (!pf) {
		puts("error opening file...");
		exit(1);
	}
	int stepSize = (signed)sizeof(int);

	int num;
	while (fread(&num, stepSize, 1, pf))
	{
		if (num % 2 == 1) {
			num++;
			fseek(pf, (-1) * stepSize, SEEK_CUR);
			fwrite(&num, stepSize, 1, pf);
		}
	}
	fclose(pf);
}
void readFromFile(char* filename) {
	FILE* pf = fopen(filename, "rb");

	if (!pf) {
		printf("error in file opening");
		exit(1);
	}

	int num;
	while (fread(&num, sizeof(int), 1, pf)) {
		printf("%d", num);
	}
	fclose(pf);
}

void writeToFile(char* filename) {
	FILE* pf = fopen(filename, "wb");

	if (!pf) {
		printf("error in file opening");
		exit(1);
	}

	int num;
	printf("num: ");
	scanf("%d", &num);

	while (num >= 0) {
		fwrite(&num, sizeof(num), 1, pf);
		printf("num: ");
		scanf("%d", &num);
	}
	fclose(pf);
}