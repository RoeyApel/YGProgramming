#include "binnode.h"

#include <stdlib.h>

BinNode initBinNode(void)
{
    return NULL;
}

BinNode buildBinNode(BinNode left, BinNode right, int data)
{
    BinNode bn = malloc(sizeof(struct NodeType));

    bn->data = data;
    bn->left = left;
    bn->right = right;

    return bn;
}

BinNode getLeft(BinNode bn)
{
    return bn->left;
}

BinNode getRight(BinNode bn)
{
    return bn->right;
}

void setLeft(BinNode* bn, BinNode bnNew)
{
    delete(&((*bn)->left));
    (*bn)->left = bnNew;
}

void setRight(BinNode* bn, BinNode bnNew)
{
    delete(&((*bn)->right));
    (*bn)->right = bnNew;
}

int getRoot(BinNode bn)
{
    return bn->data;
}

void setRoot(BinNode* bn, int data)
{
    (*bn)->data = data;
}

void delete(BinNode* bn)
{
    if (!isEmpty(*bn))
    {
        delete(&((*bn)->left));
        delete(&((*bn)->right));
        free(*bn);
    }
}

int isEmpty(BinNode bn)
{
    return (bn == NULL);
}
