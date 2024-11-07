#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

#define SIZE 20

typedef struct missile {
	char model[SIZE];
	int range, quantity;
	float price;
}Missile;

void mainx() {

}

void missileReport(Missile* missiles, int size, char model[]) {
	int i, count = 0;
	float cost = 0, totalCost = 0;

	for ( i = 0; i < size; i++)
	{
		if ((missiles + i)->model == model) {
			count++;
			cost += (missiles + i)->price;
		}
		totalCost += (missiles + i)->price;
	}

	printf("count: %d\n", count);
	printf("cost: %.2f\n", cost);
	printf("total cost: %.2f\n", totalCost);
}