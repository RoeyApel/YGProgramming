#include <stdio.h>

typedef struct {
	char id[8];
	char lastName[21];
	int pasam;
}Kalabnik;

typedef struct {
	char id[8];
	char lastName[21];
	char level;
}Officer;

typedef struct {
	char id[10];
	char lastName[21];
	int salary;
	char job[16]
}Citizen;

typedef union {
	Kalabnik kalabnik;
	Citizen citizen;
	Officer officer;
}Soldier;

typedef struct {
	Soldier s;
	char code;
}Soldier;

void main() {

}

void doingSomething(Soldier* soldier, int size) {
	for (int i = 0; i < size; i++)
	{
		if (soldier->code == "c") {
			if ((soldier + i)->s.citizen.salary > 5600) {
				printf("id: %c", soldier->s.citizen.id);
			}
		}
		else if (soldier->code == "k") {
			if ((soldier + i)->s.kalabnik.pasam > 300) {
				printf("id: %c", soldier->s.kalabnik.id);
			}
		}
	}
}