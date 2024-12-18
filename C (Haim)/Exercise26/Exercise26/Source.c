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

typedef struct {
	int start;
	int finish;
}dataType2;

typedef struct nodeType2 {
	dataType2 data;
	struct nodeType2* next;
} nodeType2, * nodePtr2;

// functions declerations
int func(nodePtr list);
void* getPositive(nodePtr* head);
void flip(nodePtr* list);
void deleteValue(nodePtr* list, int num);
int findBigValue(nodePtr list);
void moveSmallNodeFirst(nodePtr* list);
nodePtr split(nodePtr* list);
nodePtr2 selection(nodePtr2* list);

void main() {
}

nodePtr2 selection(nodePtr2* list) {
	if (!(*list)) {
		puts("The list is empty idiot");
	}

	nodePtr2 temp = *list, newList = NULL, moved;

	while (temp->next) {
		if (temp->data.finish > temp->next->data.start) {
			moved = temp->next;
			temp->next = moved->next;
			moved->next = newList;
			newList = moved;
		}
		else {
			temp = temp->next;
		}
	}
	return newList;	
}

nodePtr split(nodePtr* list) {
	if (!(*list)) {
		puts("stop no please no the list is empty as your brain");
		return;
	}

	nodePtr list2 = NULL, temp, moved;
	
	if ((*list)->data.num < 0) {
		moved = (*list);
		*list = (*list)->next;
		moved->next = list2;
		list2 = moved;
	}

	for (temp = *list; temp->next; temp = temp->next) {
		if (temp->next->data.num < 0) {
			moved = temp->next;
			temp->next = moved->next;
			moved->next = list2;
			list2 = moved;
		}
	}
	return list2;
}

void moveSmallNodeFirst(nodePtr* list) {
	if (!(*list)) {
		puts("stop no please no the list is empty as your brain");
		return;
	}

	nodePtr temp, beforeSmall,small;
	int min = (*list)->data.num;

	for (temp = *list; temp->next; temp = temp->next) {
		if (temp->next->data.num < min) {
			min = temp->next->data.num;
			beforeSmall = temp;
		}
	}
	if ((*list)->data.num == min) {
		return;
	}
	small = beforeSmall->next; 
	beforeSmall->next = small->next;
	small->next = *list;
	*list = small;
}

void deleteValue(nodePtr* list, int num) {
	if (!(*list)) {
		return;
	}
	nodePtr temp, deleted;

	if ((*list)->data.num == num) {
		deleted = *list;
		*list = (*list)->next;
		free(deleted);
	}

	for (temp = *list; temp->next; temp = temp->next) {
		if (temp->next->data.num == num) {
			deleted = temp->next; 
			temp = temp->next->next;
			free(deleted);
		}
	}
}

int findBigValue(nodePtr list) {
	if (!list) {
		return -1;
	}
	nodePtr temp; 
	int max = list->data.num; 
	
	for (temp = list->next; temp; temp = temp->next) {
		if (temp->data.num > max) {
			max = temp->data.num;
		}
	}
	return max;
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