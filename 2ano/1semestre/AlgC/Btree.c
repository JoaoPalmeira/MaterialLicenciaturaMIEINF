#include <stdio.h>
#include <stdlib.h>

typedef struct btree {
	int value;
	struct btree *left, *right;
} Node, *BTree;

//----1
//--a

int size (BTree a) {
	if(a==NULL) return 1;
	return 1+size(a->left)+size(a-right);
}

//--b
int altura (BTree a){
	if(a==NULL) return 1;
	else{ 
		if(altura(a->left)>altura(a->right)) return 1+altura(a->left);
		else return 1+altura(a-<right);
	}
}

//--c

BTree add (Btree a, Value b){
	if(a==NULL){
		a=malloc(sizeof(Node));
		a->value = b;
		a->left = NULL;
		a->right = NULL;
	}
	else if(b<(a->value)){
		a->right = add((a->right), value);
	}
	else if(b>(a->value)){
		a->left = add((a->left), value);
	}
	return a;
}

//--d

int search (BTree a, Value b){
	int existe = 0;
	if(a!=NULL){
		if((a->value) == b){
			existe = 1;
		}
		else if((a->valor) > b){
			existe = search((a->left), b);
		}
		else if((a->valor) < b){
			existe = search((a->right), b);
		}
	}
	return existe;
}

//--e

int max (BTree a){
	int maximo = 0;
	if (a != NULL) {
		if((a->right)!= NULL){
			maximo = max(a->right);
		}
		maximo = (a->value);
	}
	return maximo;
}

//----2





















