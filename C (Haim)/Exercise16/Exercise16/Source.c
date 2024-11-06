#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

#define N 4

typedef struct {
	char name[30];
	long id;
	int grades[6];
	float avg;
}Talmid, * TalmidPtr;

float avgCalc(TalmidPtr parr, int size);
float func(Talmid* (mat)[N], int rows, int cols, float* avgs);


void main() {
	Talmid yossi;
	TalmidPtr p = &yossi;

	Talmid talmidim[N] = {
	 {"Alice", 1001, {85, 90, 78, 92, 88, 79}, 0.0},
	 {"Bob", 1002, {76, 82, 91, 68, 84, 77}, 0.0},
	 {"Charlie", 1003, {95, 88, 90, 93, 87, 92}, 0.0},
	 {"David", 1004, {70, 75, 80, 65, 72, 78}, 0.0}
	};

	printf("%.2f", avgCalc(talmidim, N));
	/*gets(p->name);
	scanf("%d", &p->id);

	int i;
	for (i = 0; i < 6; i++)
	{
		printf("Z%d: ", i + 1);
		scanf("%d", p->grades + i);
	}


	printf("%.2f", p->avg);*/
}

float avgCalc(TalmidPtr parr, int size) {
	int i, k, sum = 0;
	float totalSum = 0;
	for (k = 0; k < size; k++)
	{
		sum = 0;
		for (i = 0; i < 6; i++)
		{
			sum += *((parr + k)->grades + i);
		}
		(parr + k)->avg = (float)sum / 6;
		totalSum += (parr + k)->avg;
	}
	return totalSum / size;
}

float calcAvgClass(Talmid* (mat)[N], int rows, int cols, float* avgs) {
	int i, j = 0, sum = 0, count = 0;

	for (i = 0; i < rows; i++)
	{
		count++;
		sum += avgCalc(*(mat + i), cols);
	}
	return sum / count;
}

void func(Talmid(*p)[30], int rows, int cols, Talmid** parr) {
	int i, max = -1;
	for (i = 0; i < rows; i++)
	{
		// todo
	}
}