#define _CRT_SECURE_NO_WARNINGs

#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int num;
} DataType;

typedef struct Node {
	DataType data;
	int i, j;
	struct Node* nextCol, * nextRow;
} NodeType, * NodePtr;

// fanctions definitions
void init_sparse_mat(NodePtr* mat, int* rows, int* cols);
void add_row(NodePtr mat, int* row);
void add_col(NodePtr mat, int* col);
NodePtr find_above(NodePtr mat, int row, int col);
NodePtr find_before(NodePtr mat, int row, int col);

void main() {
	int rows, cols;
	NodePtr matrix = NULL;

	init_sparse_mat(&matrix, &rows, &cols);
}

NodePtr find_before(NodePtr mat, int row, int col) {
	NodePtr temp, before;
	for (temp = mat; temp->j < row; temp = temp->nextRow);

	for (before = temp; before->nextCol != temp && before->nextCol->j < col; before = before->nextCol);

	return before;
}

NodePtr find_above(NodePtr mat, int row, int col) {
	NodePtr temp, above;
	for (temp = mat; temp->j < col; temp = temp->nextCol);

	for (above = temp; above->nextRow != temp && above->nextRow->i < row; above = above->nextRow);

	return above;
}

void add_col(NodePtr mat, int* col) {
	NodePtr newNode, p;

	newNode = (NodePtr)malloc(sizeof(NodeType));
	newNode->i = -1;
	newNode->j = (*col)++; 
	newNode->nextRow = newNode;
	newNode->nextCol = mat;

	for (p = mat; p->nextCol != mat; p = p->nextCol);
	p->nextCol = newNode;
}

void add_row(NodePtr mat, int *row) {
	NodePtr newNode, p;
	newNode = (NodePtr)malloc(sizeof(NodeType));
	newNode->i = (*row)++;
	newNode->j = -1;
	newNode->nextCol = newNode;
	newNode->nextRow = mat;

	for (p = mat; p->nextRow != mat; p = p->nextRow);
	p->nextRow = newNode;
}

void init_sparse_mat(NodePtr* mat, int* rows, int* cols) {
	*mat = (NodePtr)malloc(sizeof(NodeType));
	(*mat)->nextCol = (*mat)->nextRow = *mat;
	(*mat)->i = (*mat)->j = -1;
	*rows = *cols = 0;
}