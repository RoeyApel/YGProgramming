#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>

typedef struct {
	long id;
	char name[30];
	int enter_time;
	int exit_time;
}costumer_type;

int size;
costumer_type* parr;

void add_coutumer(costumer_type** p, long id, char* name, int enter_time);
int cmpById(void* a, void* b);
void update_coustumer(costumer_type* p, long id, int exit_time);
int find_coustumers(costumer_type* p, int enter_time, int exit_time, long** ptr);
int is_between(costumer_type* c, int enter_time, int exit_time);

void main() {

}

void add_coutumer(costumer_type** p, long id, char* name, int enter_time) {
	costumer_type c = { id, name, enter_time,0 };
	size++;
	*p = (costumer_type*)realloc(*p, size * sizeof(costumer_type));
	*(*p + size - 1) = c;

	qsort(*p, size, sizeof(costumer_type), cmpById);
}

void update_coustumer(costumer_type* p, long id, int exit_time) {
	costumer_type* c = (costumer_type*)bsearch(&id, p, size, sizeof(costumer_type), cmpById);

	c->exit_time = exit_time;
}

int find_coustumers(costumer_type* p, int enter_time, int exit_time, long** ptr) {
	int i, count = 0;

	for (i = 0; i < size; i++)
	{
		if (is_between(p + i, enter_time, exit_time)) {
			count++;
			*ptr = (int*)realloc(*ptr, count * sizeof(int));
			*(*ptr + count - 1) = p->id;
		}
	}
	return count;
}

int is_between(costumer_type* c, int enter_time, int exit_time) {
	return c->enter_time >= enter_time && c->exit_time <= exit_time;
}

int cmpById(void* a, void* b) {
	costumer_type* sa = (costumer_type*)a;
	costumer_type* sb = (costumer_type*)b;

	return (int)(sa->id - sb->id);
}

