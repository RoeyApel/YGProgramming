#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

void main() {

}

void writeToTxtFile(char* filename);
void removeNewLine(char* str);
void readAndPrint(char* filename);
void printBest(char* filename);

void sortFilesLines(char* filename) {
	FILE* fp = fopen(filename, "r");

	if (!fp) {
		puts("error");
		exit(1);
	}

	int size = 0;
	char** lines;
	char buffer[100];

	fgets(buffer, 100, fp);

	while (!feof(fp)) {

	}

}

void printBest(char* filename) {
	FILE* fstudents = fopen(filename, "r");
	if (!fstudents) {
		puts("error");
		exit(1);
	}

	long id, bestId;
	char name[35], bestName[35];
	float avg, maxAvg;

	fscanf(fstudents, "%l", &id);
	fscanf(fstudents, "%s", &name);
	fscanf(fstudents, "%f", &maxAvg);

	while (!feof(fstudents)) {
		fscanf(fstudents, "%l", &id);
		fscanf(fstudents, "%s", &name);
		fscanf(fstudents, "%f", &avg);
		if (avg > maxAvg) {
			maxAvg = avg;
			bestId = id;
			strcpy(bestName, name);
		}
	}
	printf("%l %s %f", bestId, bestName, maxAvg);

	fclose(fstudents);
}

void writeToTxtFile(char* filename) {
	FILE* fp = fopen(filename, "w");

	if (!fp) {
		puts("error");
		exit(1);
	}

	char str[50];

	scanf("%s", str);
	while (strcmp(str, "0")) {
		fputs(str, fp);
		scanf("%s", str);
	}

	fclose(fp);
}

void removeNewLine(char* str) {
	if (str[strlen(str) - 1] == '\n') {
		str[strlen(str) - 1] = 0;
	}
}

void readAndPrint(char* filename) {
	FILE* fp = fopen(filename, "r");

	if (!fp) {
		puts("error");
		exit(1);
	}

	char ch = fgetc(fp);

	while (ch != -1) {
		printf("%c", ch);
		ch = fgetc(fp);
	}
	fclose(fp);
}