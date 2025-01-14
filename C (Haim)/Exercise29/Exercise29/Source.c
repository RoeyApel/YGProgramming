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
void free_mat(NodePtr* mat, int* rows, int* cols);
void print_mat_by_cols(NodePtr mat);
void print_mat_by_rows(NodePtr mat);
NodePtr add_two_mat(NodePtr mat1, NodePtr mat2);

void main() {
	int rows, cols;
	NodePtr matrix = NULL;

	init_sparse_mat(&matrix, &rows, &cols);
}

void rotate_mat(NodePtr mat) {
	NodePtr prow = mat->nextRow->nextCol, pcol = mat->nextCol->nextRow;

	while (pcol->nextCol != mat) {
		while (pcol->i != -1 && prow->j != -1) {
			if (pcol->i == prow->j && pcol->j == prow->i) {
				pcol->data.num ^= prow->data.num;
				prow->data.num ^= pcol->data.num;
				pcol->data.num ^= prow->data.num;
			}
			else if (pcol->i < prow->j) {
				insert_node(mat, prow->i, pcol->i, pcol->data);
				delete_node(mat, pcol->i, pcol->j);
			}
			else if (prow->j < pcol->i) {
				insert_node(mat, prow->j, pcol->j, pcol->data);
				delete_node(mat, prow->i, prow->j);
			}
			pcol = pcol->nextRow;
			prow = prow->nextCol;

		}
		if (pcol->i == -1) {
			while (prow->j != -1) {
				insert_node(mat, prow->j, prow->i, prow->data);
				delete_node(mat, prow->i, prow->j);
				prow = prow->nextCol;
			}
		}
		else if (prow->j == -1) {
			while (pcol->i != -1) {
				insert_node(mat, pcol->j, pcol->i, pcol->data);
				delete_node(mat, pcol->i, pcol->j);
				pcol = pcol->nextRow;
			}
		}
		pcol = pcol->nextCol->nextRow;
		prow = prow->nextRow->nextCol;
	}
}

NodePtr add_two_mat(NodePtr mat1, NodePtr mat2) {
	NodePtr prow1 = mat1->nextRow, pcol1;
	NodePtr prow2 = mat2->nextRow, pcol2;

	pcol1 = prow1->nextCol;
	pcol2 = prow2->nextCol;
	while (prow1->nextRow != mat1) {
		if (pcol1->nextCol == prow1) {
			prow1 = prow1->nextRow;
			if (prow1->nextRow == mat1) {
				break;
			}
			prow2 = prow2->nextRow;
			pcol1 = prow1->nextCol;
			pcol2 = prow2->nextCol;
		}

		while (pcol1->j < pcol2->j && pcol1->nextCol != prow1) {
			pcol1 = pcol1->nextCol;
		}

		if (pcol1->j == pcol2->j) {
			pcol1->data.num + pcol2->data.num;
			pcol1 = pcol1->nextCol;
			pcol2 = pcol2->nextCol;
		}
		else if (pcol1->j < pcol2->j) {
			while (pcol2->nextCol != pcol2) {
				insert_node(mat1, pcol2->i, pcol2->j, pcol2->data);
				pcol1 = pcol2->nextCol;
			}
		}
		else if (pcol1->j > pcol2->j) {
			insert_node(mat1, pcol2->i, pcol2->j, pcol2->data);
		}
	}
	return mat1;
}



void print_mat_by_cols(NodePtr mat) {
	if (!mat) {
		puts("Mat Empty!!");
		return;
	}

	NodePtr prow, pcol = mat;
	for (pcol = mat->nextCol; pcol->nextCol != mat; pcol = pcol->nextCol) {
		for (prow = pcol->nextRow; prow->nextRow != pcol; prow = prow->nextRow) {
			printf("%d ", prow->data.num);
		}
		printf("\n");
	}
}

void print_mat_by_rows(NodePtr mat) {
	if (!mat) {
		puts("Mat Empty!!");
		return;
	}

	NodePtr prow = mat, pcol;
	for (prow = mat->nextRow; prow->nextRow != mat; prow = prow->nextRow) {
		for (pcol = prow->nextCol; pcol->nextCol != prow; pcol = pcol->nextCol) {
			printf("%d ", pcol->data.num);
		}
		printf("\n");
	}
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