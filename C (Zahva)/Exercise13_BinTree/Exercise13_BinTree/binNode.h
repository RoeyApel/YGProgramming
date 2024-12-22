#pragma once
#ifndef BINNODE_H
#define BINNODE_H

typedef struct NodeType
{
    int data;
    struct NodeType* left, * right;
}
NodeType;

typedef NodeType* BinNode;

BinNode initBinNode(void);

BinNode buildBinNode(BinNode left, BinNode right, int data);

BinNode getLeft(BinNode bn);

BinNode getRight(BinNode bn);

void setLeft(BinNode* bn, BinNode bnNew);

void setRight(BinNode* bn, BinNode bnNew);

int getRoot(BinNode bn);

void setRoot(BinNode* bn, int data);

void delete(BinNode* bn);

int isEmpty(BinNode bn);

#endif //BINNODE_H

