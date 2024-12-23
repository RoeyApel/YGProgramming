#define _CRT_SECURE_NO_WARNINGS

#include "binNode.h"
#include <stdio.h>

int countNodes(BinNode tree);
int sumOfEven(BinNode tree);
int count2SonDads(BinNode tree);
int countBigDads(BinNode tree);
int sumOnlyChild(BinNode tree);
int isLeaf(BinNode tree);
int countLeafs(BinNode tree);
int numOfOccurences(BinNode tree, int x);
int countNodesOnLevelX(BinNode tree, int x);
int sumNodesOnLevelX(BinNode tree, int x);
int isAllNodesPositive(BinNode tree);
int hasZeroOnlyChild(BinNode tree);
int hasNode(BinNode tree, int x);
int hasSpecificNode(BinNode tree, int x, int y);
int hasChildNodeX(BinNode tree, int x);
void printLeftInOrder(BinNode tree);

int main() {
	BinNode tree = initBinNode();
	tree = buildBinNode(NULL, NULL, -2);
	tree->left = buildBinNode(NULL, NULL, 2);
	tree->right = buildBinNode(NULL, NULL, 2);
	//printf("%d", isAllNodesPositive(tree));

	return 0;
}

int countNodes(BinNode tree) {
	if (!tree) {
		return 0;
	}
	return countNodes(tree->left) + countNodes(tree->right) + 1;
}

int sumOfEven(BinNode tree) {
	if (!tree) {
		return 0;
	}
	if (tree->data % 2 == 0) {
		return sumOfEven(tree->left) + sumOfEven(tree->right) + tree->data;
	}
	return sumOfEven(tree->left) + sumOfEven(tree->right);
}

int sumOfLeafs(BinNode tree) {
	if (!tree) {
		return 0;
	}
	if (!tree->left && !tree->right) {
		return tree->data;
	}
	return sumOfLeafs(tree->left) + sumOfLeafs(tree->right);
}

int count2SonDads(BinNode tree) {
	if (!tree) {
		return 0;
	}
	if (!tree->left && !tree->right) {
		return 0;
	}
	if (tree->left && tree->right) {
		return count2SonDads(tree->left) + count2SonDads(tree->left) + 1;
	}
	return count2SonDads(tree->left) + count2SonDads(tree->left);
}

int countBigDads(BinNode tree) {
	if (!tree) {
		return 0;
	}
	int isBig = 0;
	if (!tree->left && !tree->right) {
		return 0;
	}
	if (tree->left) {
		if (tree->left->data < tree->data) {
			isBig++;
		}
	}
	if (tree->right) {
		if (tree->right->data < tree->data) {
			isBig++;
		}
	}
	if (tree->left && !tree->right || !tree->left && tree->right) {
		return countBigDads(tree->left) + countBigDads(tree->right) + isBig;
	}
	return countBigDads(tree->left) + countBigDads(tree->right) + (isBig == 2);
}

int sumOnlyChild(BinNode tree) {
	if (!tree) {
		return 0;
	}
	if (!tree->left && !tree->right) {
		return 0;
	}
	if (tree->left && !tree->right) {
		return sumOnlyChild(tree->left) + sumOnlyChild(tree->right) + tree->left->data;

	}
	if (!tree->left && tree->right) {
		return sumOnlyChild(tree->left) + sumOnlyChild(tree->right) + tree->right->data;
	}
	return sumOnlyChild(tree->left) + sumOnlyChild(tree->right);
}

int isLeaf(BinNode tree) {
	return !tree->left && !tree->right;
}

int countLeafs(BinNode tree) {
	if (!tree) {
		return 0;
	}
	if (isLeaf(tree)) {
		return 1;
	}
	return countLeafs(tree->left) + countLeafs(tree->right);
}

int numOfOccurences(BinNode tree, int x) {
	if (!tree) {
		return 0;
	}
	if (tree->data == x) {
		return numOfOccurences(tree->left, x) + numOfOccurences(tree->right, x) + 1;
	}
	return numOfOccurences(tree->left, x) + numOfOccurences(tree->right, x);
}

int countNodesOnLevelX(BinNode tree, int x) {
	if (!tree) {
		return 0;
	}
	if (x == 0) {
		return 1;
	}
	return countNodesOnLevelX(tree->left, x - 1) + countNodesOnLevelX(tree->right, x - 1);
}

int sumNodesOnLevelX(BinNode tree, int x) {
	if (!tree) {
		return 0;
	}
	if (x == 0) {
		return tree->data;
	}
	return sumNodesOnLevelX(tree->left, x - 1) + sumNodesOnLevelX(tree->right, x - 1);
}

int isAllNodesPositive(BinNode tree) {
	if (!tree) {
		return 1;
	}
	if (tree->data <= 0) {
		return 0;
	}
	return isAllNodesPositive(tree->left) && isAllNodesPositive(tree->right);
}

int hasZeroOnlyChild(BinNode tree) {
	if (!tree) {
		return 1;
	}
	if (tree->left && !tree->right || tree->right && !tree->left) {
		return 0;
	}
	return hasZeroOnlyChild(tree->left) && hasZeroOnlyChild(tree->right);
}

int hasNode(BinNode tree, int x) {
	if (!tree) {
		return 0;
	}
	if (tree->data == x) {
		return 1;
	}
	return hasNode(tree->left, x) || hasNode(tree->right, x);
}

int hasSpecificNode(BinNode tree, int x, int y) {
	if (!tree) {
		return 0;
	}
	if (tree->data == y && hasChildNodeX(tree, x)) {
		return 1;
	}
	return hasSpecificNode(tree->left, x, y) || hasSpecificNode(tree->right, x, y);
}

int hasChildNodeX(BinNode tree, int x) {
	if (tree->left && tree->left->data == x) {
		return 1;
	}
	if (tree->right && tree->right->data == x) {
		return 1;
	}
	return 0;
}

void printLeftInOrder(BinNode tree) {
	if (tree) {
		printLeftInOrder(tree->left);
		printf("%d ", tree->data);
	}
}

void dadSumOfSons(BinNode tree) {
	if (!tree) {
		return;
	}
	if (!tree->left && !tree->right) {
		return;
	}
	int sum = 0;

	if (tree->left) {
		sum += tree->left->data;
	}
	if (tree->right) {
		sum += tree->right->data;
	}
	tree->data = sum;
	dadSumOfSons(tree->left);
	dadSumOfSons(tree->right);
}

