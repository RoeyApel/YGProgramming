#define _CRT_SECURE_NO_WARNINGs

#include <stdio.h>

typedef struct {
	int num;
} DataType;

typedef struct Node {
	DataType data;
	int i, j;
	struct Node* nextCol, * nextRow;
} NodeType, NodePtr;

void main() {

}