#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

#define N 4
#define G 6

typedef struct {
	char name[30];
	long id;
	int grades[G];
	float avg;
}Talmid, * TalmidPtr;

float calcTalmidAvg(Talmid* talmid, int numOfGrades);
float calcClassAvg(TalmidPtr parr, int size);
float calcSchoolAvg(Talmid* (mat)[N], int rows, int cols, float* avgs);
void findBestTalmidim(Talmid(*p)[30], int rows, int cols, Talmid** parr);

void mainx() {
	Talmid yossi;
	TalmidPtr p = &yossi;

	Talmid talmidim[N] = {
	 {"Alice", 1001, {85, 90, 78, 92, 88, 79}, 0.0},
	 {"Bob", 1002, {76, 82, 91, 68, 84, 77}, 0.0},
	 {"Charlie", 1003, {95, 88, 90, 93, 87, 92}, 0.0},
	 {"David", 1004, {70, 75, 80, 65, 72, 78}, 0.0}
	};

	printf("%.2f", calcClassAvg(talmidim, N));
}

float calcTalmidAvg(Talmid* talmid, int numOfGrades) {
	int i;

	for (i = 0, talmid->avg; i < numOfGrades; i++)
	{
		talmid->avg += *(talmid->grades + i);
	}
	talmid->avg /= numOfGrades;

	return talmid->avg;
}

float calcClassAvg(TalmidPtr parr, int size) {
	int i = 0, count = 0;;
	float sum = 0;


	while ((parr + i)->id != 0 && i < size) {
		sum += calcTalmidAvg(parr + i, G);
		i++;
		count++;
	}
	return sum / count;
}

float calcSchoolAvg(Talmid* (mat)[N], int rows, int cols, float* avgs) {
	int i, j = 0, sum = 0, count = 0;

	for (i = 0; i < rows; i++)
	{
		*(avgs + i) = calcClassAvg(*(mat + i), cols);
	}
}

void findBestTalmidim(Talmid(*p)[30], int rows, int cols, Talmid** parr) {
	int i, j, max;
	float avg = 0;
	TalmidPtr tp = *p;

	for (i = 0; i < rows; i++)
	{
		max = -1;
		for (j = 0; j < cols; j++)
		{
			avg = calcTalmidAvg(*(p + i) + j, G);

			if (avg > max) {
				max = avg;
				tp = *(p + i) + j;
			}
			
		}
		*(parr + i) = tp;
	}
}