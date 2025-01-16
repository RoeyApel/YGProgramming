#include "node_avl.h";

void balance(NodePtr* tree) {
	if (differ((*tree)->left) == 2) {
		if (differ((*tree)->left->left) == 1) {
			LLRotation(tree);
		}
		else
		{
			LRRotation(tree);
		}
	}
	else {
		if (differ((*tree)->right->left) == 1) {
			RLRotation(tree);
		}
		else
		{
			RRRotation(tree);
		}
	}
}

void RLRotation(NodePtr* tree) {
	if (differ((*tree)->left) == -2) {
		LLRotation(&(*tree)->left);
	}
	else
	{
		RRRotation(&(*tree)->right);
	}
	RRRotation(tree);
}

void LRRotation(NodePtr* tree) {
	if (differ((*tree)->left) == 2) {
		RRRotation(&(*tree)->left);
	}
	else
	{
		RRRotation(&(*tree)->right);
	}
	LLRotation(tree);
}

void LLRotation(NodePtr* tree) {
	NodePtr father, child, grandfather = *tree;
	int dir = differ(grandfather->left) == 2 ? 0 : 1;

	if (dir == 0) {
		father = grandfather->left;
		child = father->left;
		grandfather->left = child;
	}
	else
	{
		father = grandfather->right;
		child = father->left;
		grandfather->right = child;
	}

	father->left = child->right;
	child->right = father;
}

void RRRotation(NodePtr* tree) {
	NodePtr father, child, grandfather = *tree;
	int dir = differ(grandfather->left) == -2 ? 0 : 1;

	if (dir == 0) {
		father = grandfather->left;
		child = father->right;
		grandfather->left = child;
	}
	else
	{
		father = grandfather->right;
		child = father->right;
		grandfather->right = child;
	}

	father->right = child->left;
	child->left = father;
}

int differ(NodePtr tree) {
	return height(tree->left) - height(tree->right);
}

int height(NodePtr tree) {
	if (!tree) {
		return 0;
	}
	return max(height(tree->left), height(tree->right)) + 1;
}

int max(int a, int b) {
	return a > b ? a : b;
}