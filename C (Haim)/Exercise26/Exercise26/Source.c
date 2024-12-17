#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node node;
struct node {
	int id;
	char* name;
	node* next;
	node* hop;
};

typedef struct {
	int num;
}dataType;

typedef struct nodeType {
	dataType data;
	struct nodeType* next;
} nodeType, * nodePtr;

// functions declerations
int func(nodePtr list);
void* getPositive(nodePtr* head);
void flip(nodePtr* list);

void main() {
}

int func(nodePtr list) {
	nodePtr temp;
	int sum = 0, i = 1;

	for (temp = list; temp->next; temp = temp->next)
	{
		sum += i * temp->data.num;
		i *= -1;
	}
	return sum;
}

void flip(nodePtr* list) {
	nodePtr current = *list, next, prev = NULL;

	while (current)
	{
		next = current->next;
		current->next = prev;
		prev = current;
		current = next;
	}
	*list = prev;
}

void* getPositive(nodePtr* head) {
	nodePtr temp, deleted;

	if (!(*head)) {
		return NULL;
	}
	if ((*head)->data.num < 0) {
		deleted = *head;
		*head = (*head)->next;
		free(deleted);
		return *head;
	}

	for (temp = *head; temp->next; temp = temp->next)
	{
		if (temp->next->data.num < 0) {
			deleted = temp->next;
			temp->next = temp->next->next;
			free(deleted);
		}
	}
	return *head;
}