#ifndef btree_h
#define btree_h


typedef struct btree *BTree, Node;
typedef int Value;


int Size(BTree);
int Height(BTree);
int IsBalanced(BTree);
BTree Add(BTree, Value);
int Search(BTree, Value);
Value Max(BTree);
void Destroy(BTree);


#endif
