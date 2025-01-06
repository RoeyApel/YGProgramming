#define _CRT_SECURE_NO_WARNINGS;

#include <stdio.h>
#include <stdlib.h>

typedef struct {
	long id;
	char name[30];
	int faculty;
}StudentItemType, * StudentItemPtr;

typedef struct {
	long id;
	char name[30];
	int faculty;
	int fails;
	float avg;
}StudentType, * StudentPtr;

typedef struct NodeD {
	StudentPtr student;
	struct NodeD* prev, * next;
}DNodeType, * DNodePtr;

typedef struct Node {
	DNodePtr pstudent;
	struct Node* next;
} NodeType, * NodePtr;

typedef struct {
	long id;
	int subNum;
	int grade;
}GradeType, * GradePtr;

typedef struct NodeR {
	GradeType grade;
	struct NodeR* next;
} RNodeType, * RNodePtr;

// functions definitions

void main() {

}

void add_sorted_dNode(DNodePtr* p, long id) {
	DNodePtr newNode = (DNodePtr)malloc(sizeof(DNodeType));
	newNode->
}