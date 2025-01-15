#include "node_avl.h";

void LLRotation(NodePtr* tree) {

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