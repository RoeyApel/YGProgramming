#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

#define MANAGER 1
#define SECRETARY 2
#define WORKER 3

#define SIZE 3

typedef struct {
	int id;
	char name[25];
	int years;
	int vetec;
	int departmentId;
	int salary;
}Manager;

typedef struct {
	int id;
	char name[25];
	int vetec;
	int salary;
}Secretary;

typedef struct {
	int id;
	int vetec;
	int departmentId;
	int salary;
}Worker;

typedef union {
	Manager manager;
	Secretary secretary;
	Worker worker;
}P;

typedef struct {
	P info;
	int type;
}Person;

void scanPersonArr(Person* person, int size);
void printVaticWorkers(Person* person, int size);
void printRichSecretaries(Person* person, int size);

void main() {
	Person people[SIZE];
	scanPersonArr(people, SIZE);
	printRichSecretaries(people, SIZE);
	printVaticWorkers(people, SIZE);
}
void scanPersonArr(Person *person, int size) {
	Worker worker;
	Manager manager;
	Secretary secretary;

	for (int i = 0; i < size; i++)
	{
		printf("type (1/2/3): ");
		scanf("%d", &(person + i)->type);

		switch ((person + i)->type) {
		case WORKER:
			worker = (person + i)->info.worker;
			printf("id: ");
			scanf("%d", &worker.id);
			printf("vetec: ");
			scanf("%d", &worker.vetec);
			printf("departmentId: ");
			scanf("%d", & worker.departmentId);
			printf("salary: ");
			scanf("%d", & worker.salary);
			break; 
		case SECRETARY:
			secretary = (person + i)->info.secretary;
			printf("id: ");
			scanf("%d", &secretary.id);
			printf("vetec: ");
			scanf("%d", &secretary.vetec);
			printf("name: ");
			scanf("%s", &secretary.name);
			printf("salary: ");
			scanf("%d", &secretary.salary);
			break;
		case MANAGER:
			manager = (person + i)->info.manager;
			printf("id: ");
			scanf("%d", &manager.id);
			printf("vetec: ");
			scanf("%d", &manager.vetec);
			printf("name: ");
			scanf("%s", &manager.name);
			printf("salary: ");
			scanf("%d", &manager.salary);
			printf("years: ");
			scanf("%d", &manager.years);
			printf("departmentId: ");
			scanf("%d", &manager.departmentId);
			break;
		default:
			printf("error");
		}
		
	}
}

void printVaticWorkers(Person* person, int size) {
	Worker worker;
	for (int i = 0; i < size; i++)
	{
		if ((person + i)->type == WORKER) {
			worker = (person + i)->info.worker;
			if (worker.vetec > 5) {
				printf("id: %d\n", worker.id);
				printf("vetec: %d\n", worker.vetec);
				printf("departmentId: %d\n", worker.departmentId);
				printf("salary: %d\n", worker.salary);
			}
		}
	}
}

void printRichSecretaries(Person* person, int size) {
	Secretary secretary;
	int countS = 0, sumS = 0;
	for (int i = 0; i < size; i++)
	{
		if ((person + i)->type == SECRETARY) {
			secretary = (person + i)->info.secretary;
			sumS += secretary.salary;
			countS++;
		}
	}
	float average = (float) sumS / countS;
	for (int i = 0; i < size; i++)
	{
		if ((person + i)->type == SECRETARY) {
			secretary = (person + i)->info.secretary;
			if (secretary.salary > average) {
				printf("id: %d\n", secretary.id);
				printf("name: %s\n", secretary.name);
				printf("vetec: %d\n", secretary.vetec);
				printf("salary: %d\n", secretary.salary);
			}
		}
	}
}
