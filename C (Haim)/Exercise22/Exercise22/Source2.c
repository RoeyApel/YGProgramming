#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

typedef struct {
	int id;
	char lastName[25];
	char firstName[25];
	int monthsInPrison;
	int monthsPassed;
	char bcode;

}Asir, * PAsir;

void asirHandle(char* filenameA);
int max(int a, int b);

void main() {

}

void asirHandle(char* filenameA) {
	FILE* pfileA = fopen(filenameA, "r+");

	Asir a;

	while (fread(&a, sizeof(Asir), 1, pfileA))
	{
		if (a.bcode == 1) {
			a.monthsInPrison = max(0, a.monthsInPrison - 3);
			fseek(pfileA, -1 * (signed)sizeof(Asir), SEEK_CUR);
			fwrite(&a, sizeof(Asir), 1, pfileA);

			if (a.monthsInPrison == 0) {
				printf("%d , %s , %s\n", a.id, a.firstName, a.lastName);
			}
		}
	}
	fclose(pfileA);
}

int max(int a, int b) {
	return a > b ? a : b;
}