#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

typedef struct {
	char date[25];
	int day;
	int numOfP;

}Event, * PEvent;

void printEventMaxs(char* filenameE);

void main() {

}

void printEventMaxs(char* filenameE) {
	FILE* pfileE = fopen(filenameE, "r");

	Event e;
	int maxP = -1;
	int maxE = -1;
	int day;
	int days[7] = { 0 };
	char date[25] = { 0 };

	while (fread(&e, sizeof(Event), 1, pfileE))
	{
		if (e.numOfP > maxP) {
			maxP = e.numOfP;
			strcpy(date, e.date);
		}
		(*(days + e.day - 1))++;

		if (*(days + e.day - 1) > maxE) {
			maxE = *(days + e.day - 1);
			day = e.day;
		}
	}

	printf("day: %d, numOfP: %d", day, date);
	fclose(pfileE);
}
