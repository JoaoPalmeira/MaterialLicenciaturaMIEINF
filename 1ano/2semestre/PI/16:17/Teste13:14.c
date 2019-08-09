#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//------------------------------------------------- EXERCICIO 1
typedef struct slist {
	int valor;
	struct slist *prox;
} *LInt;

LInt fromArray (int v[], int N){
	LInt resultado=NULL;
	while(N>=0){
		resultado = newLInt(v[N], resultado);
		N--;
	}
	return resultado;
}

//------------------------------------------------- EXERCICIO 2
#define BSize 100
 
typedef struct larray{
    int valores[BSize];
    struct larray *prox;
} *LArrays;
 
typedef struct stack{
    int sp;
    LArrays s;
} Stack;

void push (Stack *st, int x){}

//------------------------------------------------- EXERCICIO 3
typedef struct spares {
	int x, y;
	struct spares *prox;
} Par, *LPares;
typedef struct slist {
	int valor;
	struct slist *prox;
} Nodo, *LInt;

LPares zip (LInt a, LInt b, int *c){
	int i=0;
	LInt aponta_a, aponta_b;
    LPares lista, aponta;
    aponta_a = a;
    aponta_b = b;
    lista = (LInt) malloc(sizeof(Nodo));
    aponta = lista;

	if(a==NULL) return NULL;
	else if(b==NULL) return NULL;
	else{
		while(a && b){
			(lista->x) = (a->valor);
			a = (a->prox);
			(lista->y) = (b->valor);
			b = (b->prox);
			lista = (lista->prox);
        	lista = (LInt) malloc(sizeof(Nodo));
			i++;
		}
	}

	a = aponta_a;
    b = aponta_b;
    lista->prox = NULL;
    &(*c)=i;
    return aponta;
}

//------------------------------------------------- EXERCICIO 4
typedef struct no{
    int value;
    struct no *esq, *dir, *pai;
} No, *Tree;

// (a)

void calculaPais(Tree t){
	if(t==NULL) return NULL;
	else{
		if(t!=NULL){
			t=t->value;
			calculaPais(t->dir);
			calculaPais(t->esq);
		}
		else if(t->dir){
			(t->dir->pai)=t;
		}
		else if(t->esq){
			(t->esq->pai)=t;
		}
	}
	calculaPais(t->dir);
	calculaPais(t->esq);
}

// (b)

Tree next(Tree t){

}















