#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
int delete_node(Node_type** p, int num);
int delete_node_sorted(Node_type** p, int num);
void print_node(Node_type* p);

typedef struct {
	int num;
} Data;

typedef struct Node {
	Data data;
	struct Node* next;
}Node_type, *Node_ptr;

void main() {

}

void print_node(Node_type* p) {
	Node_ptr temp; 

	for (temp = p; temp; temp = temp->next) {
		printf("%d", temp->data.num);
	}
}

int delete_node(Node_type** p, int num) {
	if (!(*p)) {
		return 0;
	}

	Node_ptr deleted;

	if ((*p)->data.num == num) {
		deleted = *p;
		*p = (*p)->next;
		free(deleted);
	}

	Node_ptr temp;

	for (temp = *p; temp->next && temp->next->data.num != num; temp = temp->next);

	if (!temp->next) {
		return 0;
	}
	deleted = temp->next;

	temp->next = temp->next->next;
	free(deleted);

	return 1;
}

int delete_node_sorted(Node_type** p, int num) {
	if (!(*p) || (*p)->data.num > num) {
		return 0;
	}
	Node_ptr deleted;

	if ((*p)->data.num == num) {
		deleted = *p;
		*p = (*p)->next;
		free(deleted);
	}
	Node_ptr temp;

	for (temp = *p; temp->next && temp->next->data.num < num; temp = temp->next);

	if (!temp->next) {
		return 0;
	}

	if (temp->next->data.num != num) {
		return 0;
	}
	deleted = temp->next;

	temp->next = temp->next->next;
	free(deleted);

	return 1;
}
