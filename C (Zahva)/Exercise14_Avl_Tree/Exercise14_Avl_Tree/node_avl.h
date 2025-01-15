typedef int nodeType;

typedef struct node {
	nodeType data;
	struct node* left, * right;
}Node, *NodePtr;

void add_node(NodePtr tree);

int height(NodePtr tree);

int differ(NodePtr tree);

void balance(NodePtr* tree);

void RLRotation(NodePtr* tree);
void LLRotation(NodePtr* tree);
void LRRotation(NodePtr* tree);
void RRRotation(NodePtr* tree);
