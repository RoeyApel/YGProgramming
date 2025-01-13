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
int insert_node(NodePtr mat, int row, int col, DataType data);
int update_node(NodePtr mat, int row, int col, DataType data);
DataType delete_node(NodePtr mat, int row, int col);
void delete_last_col(NodePtr mat, int* cols);
void delete_last_row(NodePtr mat, int* rows);

void main() {
	int rows, cols;
	NodePtr matrix = NULL;

	init_sparse_mat(&matrix, &rows, &cols);
}

void free_mat(NodePtr* mat, int* rows, int* cols) {
	while (*rows)
	{
		delete_last_row(*mat, rows);
	}
	while (*cols)
	{
		delete_last_col(*mat, cols);
	}
	free(*mat);
	*mat = NULL;
}

void delete_last_col(NodePtr mat, int* cols) {
	NodePtr prow, pcol, prev = mat;

	for (pcol = mat->nextCol; pcol->nextCol != mat; prev = pcol, pcol = pcol->nextCol);

	for (prow = pcol->nextRow; prow != pcol; prow = pcol->nextRow) delete_node(mat, prow->i, prow->j);

	prev->nextCol = mat;
	free(pcol);
	(*cols)--;
}


void delete_last_row(NodePtr mat, int* rows) {
	NodePtr prow, pcol, prev = mat;

	for (prow = mat->nextRow; prow->nextRow != mat; prev = prow, prow = prow->nextRow);

	for (pcol = prow->nextCol; pcol != prow; pcol = prow->nextCol) delete_node(mat, pcol->i, pcol->j);

	prev->nextRow = mat;
	free(prow);
	(*rows)--;
}

DataType delete_node(NodePtr mat, int row, int col) {
	DataType data;
	NodePtr above = find_above(mat, row, col);
	NodePtr before = find_before(mat, row, col);

	if (above->nextRow != before->nextCol) {
		data.num = -1;
		puts("Error!!");
		return data;
	}

	NodePtr deleted = above->nextRow;

	above->nextRow = deleted->nextRow;
	before->nextCol = deleted->nextCol;

	data = deleted->data;
	free(deleted);
	return data;
}

int update_node(NodePtr mat, int row, int col, DataType data) {
	NodePtr above = find_above(mat, row, col);
	NodePtr before = find_before(mat, row, col);

	if (above->nextRow != before->nextCol) {
		puts("Error!!");
		return 0;
	}

	above->nextRow->data = data;
	return 1;
}

int insert_node(NodePtr mat, int row, int col, DataType data) {
	NodePtr above = find_above(mat, row, col);
	NodePtr before = find_before(mat, row, col);

	if (above->nextRow == before->nextCol) {
		puts("Error!!");
		return 0;
	}

	NodePtr newNode = (NodePtr)malloc(sizeof(NodeType));
	newNode->data = data;
	newNode->i = row;
	newNode->j = col;

	newNode->nextRow = above->nextRow;
	above->nextRow = newNode;

	newNode->nextCol = before->nextCol;
	before->nextCol = newNode;
	return 1;
}

NodePtr find_before(NodePtr mat, int row, int col) {
	NodePtr temp, before;
	for (temp = mat; temp->i < row; temp = temp->nextRow);

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

void add_row(NodePtr mat, int* row) {
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