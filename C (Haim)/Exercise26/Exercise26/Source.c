#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
	int num;
}dataType;

typedef struct nodeType {
	dataType data;
	struct nodeType* next;
} nodeType, * nodePtr;

typedef struct List {
	int sub_num;
	int grade;
	struct list* next;
}ListType, * ListPtr;

typedef struct {
	long id;
	int sub_num;
	int grade;
}GradeItemType, * GradeItemPtr;

typedef struct {
	long id;
	char name[30];
	float avg;
	int fails;
	ListPtr list;
} StudentType, * StudentPtr;

typedef struct {
	long id;
	char name[30];
}StudentItemType, * StudentItemPtr;

// functions declerations
int func(nodePtr list);
void* getPositive(nodePtr* head);
void flip(nodePtr* list);

void main() {
}

void create_grades_lists(char* filename, StudentType* arr, int size) {
	FILE* fgrades = fopen(filename, "rb");

	if (!(fgrades)) {
		exit(1);
	}

	StudentType key = { 0 };
	StudentPtr student;
	GradeItemType gradeItem;
	ListPtr list = NULL;

	while (fread(&gradeItem, sizeof(gradeItem), 1, fgrades)) {
		key.id = gradeItem.id;
		student = (StudentPtr)bsearch(&key, arr, size, sizeof(StudentType), cmpById);
		add_node_sorted(&student->list, gradeItem.sub_num, gradeItem.grade);
	}
}

void add_node_sorted(ListType** list, int sub_num, int grade) { // not done!!
	ListPtr temp, newNode;
	newNode = malloc(sizeof(ListType));
	newNode->sub_num = sub_num;
	newNode->grade = grade;

	if (!(list)) {
		*list = newNode;
	}
	
	for (temp = *list ; ;)
	{

	}
}

StudentPtr create_student_arr(char* filename, int* size) {
	*size = 0;
	StudentItemType studentItem;
	StudentType student = { 0 };
	StudentType* arr;

	FILE* fstudents = fopen(filename, "rb");

	if (!(fstudents)) {
		exit(1);
	}

	while (fread(&studentItem, sizeof(StudentItemType), 1, fstudents)) {
		arr = (StudentPtr)realloc(arr, ++(*size) * sizeof(StudentType));
		student.id = studentItem.id;
		strcpy(student.name, studentItem.name);

		*(arr + (*size) - 1) = student;
	}
	qsort(arr, *size, sizeof(StudentType), cmpById);
	return arr;
}

int cmpById(void* a, void* b) {
	StudentPtr sa = (StudentPtr)a;
	StudentPtr sb = (StudentPtr)b;

	return (int)(sa->id - sb->id);
}

int func(nodePtr list) {
	nodePtr temp;
	int sum = 0, i = 1;

	for (temp = list; temp->next; temp = temp->next)
	{
		sum += i * temp->data.num;
		i *= -1;
	}
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
	}

	for (temp = *head; temp->next; temp = temp->next)
	{
		if (temp->next->data.num < 0) {
		}
	}
}