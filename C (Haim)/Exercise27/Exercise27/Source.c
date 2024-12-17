#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct List {
	int sub_num;
	int grade;
	struct List* next;
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
void calc_students_stats(StudentPtr arr, int size);
void create_grades_lists(char* filename, StudentType* arr, int size);
void add_node_sorted(ListType** list, int sub_num, int grade);
StudentPtr create_student_arr(char* filename, int* size);
int cmpById(void* a, void* b);

void main() {
}

void calc_students_stats(StudentPtr arr, int size) {
	StudentPtr student;
	ListPtr temp;

	int i;
	for (i = 0; i < size; i++)
	{
		student = arr + i;

		for (temp = student->list; temp; temp = temp->next) {
			student->avg += temp->grade;
			if (temp->grade < 60) {
				student->fails++;
			}
		}
		student->avg /= size;
	}
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

	fclose(fgrades);
}

void add_node_sorted(ListType** list, int sub_num, int grade) {
	ListPtr temp, newNode;
	newNode = malloc(sizeof(ListType));
	newNode->sub_num = sub_num;
	newNode->grade = grade;

	if (!(*list)) {
		*list = newNode;
		return;
	}

	if ((*list)->sub_num > sub_num) {
		newNode->next = *list;
		*list = newNode;
		return;
	}

	for (temp = *list; temp->next && temp->next->sub_num < sub_num; temp = temp->next);

	newNode->next = temp->next;
	temp->next = newNode;

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

	fclose(fstudents);
	return arr;
}

int cmpById(void* a, void* b) {
	StudentPtr sa = (StudentPtr)a;
	StudentPtr sb = (StudentPtr)b;

	return (int)(sa->id - sb->id);
}