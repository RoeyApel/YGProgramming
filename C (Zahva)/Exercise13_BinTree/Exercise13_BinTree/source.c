#define _CRT_SECURE_NO_WARNINGS

#include "binNode.h"
#include <stdio.h>

int countNodes(BinNode tree);
int sumOfEven(BinNode tree);
int count2SonDads(BinNode tree);

int main() {
	BinNode tree = initBinNode();
	tree = buildBinNode(NULL, NULL, 2);
	tree->left = buildBinNode(NULL, NULL, 3);
	//tree->right = buildBinNode(NULL, NULL, 4);
	printf("%d", sumOnlyChild(tree));
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

