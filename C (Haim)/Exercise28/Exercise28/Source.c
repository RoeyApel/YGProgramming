#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
	long id;
	char name[30];
	int faculty;
}StudentItemType, * StudentItemPtr;

typedef struct {
	long id;
	int subNum;
	int grade;
}GradeType, * GradePtr;

typedef struct NodeR {
	GradeType gradeInfo;
	struct NodeR* next;
} RNodeType, * RNodePtr;

typedef struct {
	long id;
	char name[30];
	int faculty;
	int fails;
	float avg;
	RNodePtr list;
}StudentType, * StudentPtr;

typedef struct NodeD {
	StudentType student;
	struct NodeD* prev, * next;
}DNodeType, * DNodePtr;

typedef struct Node {
	DNodePtr nodeAddress;
	struct Node* next;
} NodeType, * NodePtr;

// functions definitions
void add_sorted_dNode(DNodePtr* p, StudentType student);
void create_students_dList(DNodePtr* list, char* filename);
void add_sorted_rNode(RNodePtr* p, GradeType gradeInfo);
void create_grades_lists(DNodePtr* list, char* filename);
void calc_student_stats(RNodePtr p, int* fails, float* avg);
void calc_students_stats(DNodePtr* list);
void free_nodes(DNodePtr p);
int remove_quitters_from_list(DNodePtr* list, char* filename);
void add_node_last(NodePtr* p, DNodePtr nodeAddress);
void divide_by_faculties(DNodePtr list, NodePtr* faculty1, NodePtr* faculty2, NodePtr* faculty3);

void main() {

}

void divide_by_faculties(DNodePtr list, NodePtr* faculty1, NodePtr* faculty2, NodePtr* faculty3) {
	*faculty1 = *faculty2 = *faculty3 = NULL;

	int currentFaculty;

	for (; list; list = list->next) {
		currentFaculty = list->student.faculty;

		switch (currentFaculty)
		{
		case 1:
			add_node_last(faculty1, list);
			break;
		case 2:
			add_node_last(faculty2, list);
			break;
		case 3:
			add_node_last(faculty3, list);
			break;
		}
	}
}

void add_node_last(NodePtr* p, DNodePtr nodeAddress) {
	NodePtr newNode = (NodePtr)malloc(sizeof(NodeType));
	newNode->nodeAddress = nodeAddress;
	newNode->next = NULL;

	if (!(*p)) {
		*p = newNode;
		return;
	}

	NodePtr temp; 
	for (temp = *p; temp->next; temp = temp->next);

	temp->next = newNode;
}

int remove_quitters_from_list(DNodePtr* list, char* filename) {
	if (!(*list)) return 0;

	int removed = 0;
	FILE* quittersFile = fopen(filename, "rb");

	if (!quittersFile) {
		puts("error in file opening...");
		exit(1);
	}

	DNodePtr temp, prev = NULL, del = NULL;
	long currentId;

	while (fread(&currentId, sizeof(long), 1, quittersFile)) {
		for (temp = *list, prev = NULL; temp && currentId > temp->student.id; prev = temp, temp = temp->next);

		if (!temp || currentId != temp->student.id) return removed;

		del = temp;

		if (prev) {
			prev->next = temp->next;
		}

		if (temp->next) {
			temp->next->prev = prev;
		}

		if (del == *list) {
			*list = (*list)->next;
		}

		free_nodes(del);
		removed++;
	}

	fclose(quittersFile);
	return removed;
}

void free_nodes(DNodePtr p) {
	RNodePtr list = p->student.list, deleted;

	if (!list) {
		free(p);
		return;
	}

	deleted = list;
	list = list->next;
	deleted->next = NULL;
	deleted = NULL;

	while (list) {
		free(deleted);
		deleted = list;
		list = list->next;
	}

	free(deleted);
	free(p);
}

void calc_students_stats(DNodePtr* list) {
	DNodePtr temp;
	int fails;
	float avg;

	for (temp = *list; temp; temp = temp->next) {
		calc_students_stats(temp->student.list, &fails, &avg);
		temp->student.avg = avg;
		temp->student.fails = fails;
	}
}

void calc_student_stats(RNodePtr p, int* fails, float* avg) {
	*fails = 0;
	*avg = 0;

	if (!p) {
		return;
	}

	RNodePtr temp = p;
	int count = 1;

	*avg += temp->gradeInfo.grade;
	*fails += temp->gradeInfo.grade > 60 ? 0 : 1;

	for (temp = p->next; temp != p; temp = temp->next) {
		*avg += temp->gradeInfo.grade;
		*fails += temp->gradeInfo.grade > 60 ? 0 : 1;
		count++;
	}

	*avg /= count;
}

void create_grades_lists(DNodePtr* list, char* filename) {
	FILE* gradesFile = fopen(filename, "rb");

	if (!gradesFile) {
		puts("error in file opening...");
		exit(1);
	}

	GradePtr gradeInfo;
	DNodePtr temp;

	while (fread(gradeInfo, sizeof(GradeType), 1, gradesFile)) {

		for (temp = *list; temp && gradeInfo->id > temp->student.id; temp = temp->next);

		if (temp && gradeInfo->id == temp->student.id) {
			add_sorted_rNode(&temp->student.list, *gradeInfo);
		}
	}

	fclose(gradesFile);
}

void add_sorted_rNode(RNodePtr* p, GradeType gradeInfo) {
	RNodePtr newNode = (RNodePtr)malloc(sizeof(RNodeType));
	newNode->gradeInfo = gradeInfo;

	if (!(*p)) {
		newNode->next = newNode;
		*p = newNode;
		return;
	}

	if (gradeInfo.subNum >= (*p)->gradeInfo.subNum) {
		newNode->next = (*p)->next;
		(*p)->next = newNode;
		*p = newNode;
		return;
	}

	if (gradeInfo.subNum <= (*p)->next->gradeInfo.subNum) {
		newNode->next = (*p)->next;
		(*p)->next = newNode;
		return;
	}

	RNodePtr temp;
	for (temp = (*p)->next; temp->next->gradeInfo.subNum < gradeInfo.subNum; temp = temp->next);

	newNode->next = temp->next;
	temp->next = newNode;
}

void create_students_dList(DNodePtr* list, char* filename) {
	FILE* studentsFile = fopen(filename, "rb");

	if (!studentsFile) {
		puts("error in file opening...");
		exit(1);
	}

	StudentType student = { 0 };
	StudentItemPtr studentItem;

	while (fread(studentItem, sizeof(StudentItemType), 1, studentsFile)) {
		student.id = studentItem->id;
		student.faculty = studentItem->faculty;
		strcpy(student.name, studentItem->name);
		student.fails = 0;
		student.avg = 0;
		student.list = NULL;
		add_sorted_dNode(&list, student);
	}

	fclose(studentsFile);
}

void add_sorted_dNode(DNodePtr* p, StudentType student) {
	DNodePtr newNode = (DNodePtr)malloc(sizeof(DNodeType));
	newNode->student = student;

	if (!(*p) || (*p)->student.id > student.id) {
		newNode->prev = NULL;
		newNode->next = *p;
		*p = newNode;
		return;
	}

	DNodePtr temp;

	for (temp = *p; temp->next && temp->next->student.id < student.id; temp = temp->next);

	newNode->next = temp->next;
	newNode->prev = temp;

	if (temp->next) {
		temp->next->prev = newNode;
	}
}