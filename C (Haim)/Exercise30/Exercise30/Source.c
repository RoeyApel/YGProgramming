#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void main() {

}

void writeToTxtFile(char* filename);
void removeNewLine(char* str);
void readAndPrint(char* filename);
void printBest(char* filename);
int cmpLines(void* a, void* b);
void sortFilesLines(char* filename);
int islineExists(char* filename, char* line);
int getLineOffset(char* filename, char* line);

int getLineOffset(char* filename, char* line) {
	FILE* fp = fopen(filename, "r");

	if (!fp) {
		puts("error");
		exit(1);
	}

	char buffer[100];

	fgets(buffer, 100, fp);

	while (!feof(fp) && strcmp(line, buffer)) {
		fgets(buffer, 100, fp);
	}


	if (feof(fp)) return -1;

	long offset = ftell(fp);
	fseek(fp, 0, SEEK_SET);
	offset -= ftell(fp);

	fclose(fp);

	return offset;
}


int islineExists(char* filename, char* line) {
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
		lines = (char**)realloc(lines, (++size) * sizeof(char*));
		*(lines + size - 1) = strdup(buffer);
		fgets(buffer, 100, fp);
	}

	fclose(fp);

	char* key = (char*)bsearch(line, lines, size, sizeof(char*), cmpLines);

	int i;
	for (i = 0; i < size; i++) {
		free(*(lines + i));
	}

	free(lines);

	return key;
}

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
		lines = (char**)realloc(lines, (++size) * sizeof(char*));
		*(lines + size - 1) = strdup(buffer);
		fgets(buffer, 100, fp);
	}
	fclose(fp);
	fp = fopen(filename, "w");
	if (!fp) {
		puts("error");
		exit(1);
	}

	qsort(lines, size, sizeof(char*), cmpLines);

	int i;
	for (i = 0; i < size; i++)
	{
		fprintf(fp, "%s", *(lines + i));
	}

	fclose(fp);

	for (i = 0; i < size; i++) {
		free(*(lines + i));
	}

	free(lines);
}
int cmpLines(void* a, void* b) {
	char* sa = (char*)a;
	char* sb = (char*)b;

	return strcmp(sa, sb);
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