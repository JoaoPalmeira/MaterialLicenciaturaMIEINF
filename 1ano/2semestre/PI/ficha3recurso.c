#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 1024

//------2 LISTAS LIGADAS

//----1
typedef struct slist *LInt;
typedef struct slist {
int valor;
LInt prox;
} Nodo;

//a
/*LInt a, ult, novo = malloc (SIZEOF (struct slist));
(novo -> valor) = 10;
a = ult = novo;
novo = malloc(SIZEOF (struct slist));
(novo -> valor) = 5;
(ult -> prox) = novo;
ult = novo;
novo = malloc(SIZEOF (struct slist));
(novo -> valor) = 15;
(ult -> prox) = novo;
ult = novo;
(ult -> prox) = NULL;
*/
//b

//i
LInt cons (LInt l, int x){
	LInt novo = malloc (sizeof (struct slist));
	(novo -> valor) = x;
	(novo -> prox) = l;
	return novo;
}

//ii
LInt tail (LInt l){
	LInt x;
	if(l==NULL) return NULL;
	l=x;
	l=(l -> prox);
	free(l);
	return l;
}

//iii
LInt init (LInt l) {
	LInt ant, t;
	if (l==NULL) return NULL;
	if ((l -> prox)==NULL){
		free(l);
		return NULL;
	}
	t = l;
	while ((t -> prox) != NULL){
		ant = t;
		t = (t -> prox);
	}
	(ant -> prox) = NULL;
	free(t);
	return l;
}

//iv
LInt snoc (LInt l, int x){
	LInt t;
	LInt novo = malloc(sizeof(struct slist));
	(novo -> valor)=x;
	(novo -> prox)=NULL;
	if(l==NULL) return novo;
	t=l;
	if((t->prox)!=NULL){
		t=(t->prox);
	}
	(t->prox) = novo;
	return l;
}

//v
LInt concat (LInt a, LInt b){
	LInt t;
	if(a==NULL) return b;
	t=a;
	while((t->prox)!=NULL){
		t=(t->prox);
	}
	(t->prox)= b;
	return a;
}

/*
//----2
typedef struct difl {
LInt inicio, fim;
} DifList;

//a
DifList snoc (DifList l, int x){
	LInt t;
	LInt novo = malloc(sizeof(struct difl));
	(novo -> valor)=x;
	(novo -> fim)=NULL;
	if(l==NULL) return novo;
	t=l;
	while((t->fim)!=NULL)
		t=(t->fim);
	(t->fim)=novo;
	return l;
}

//b
DifList concat (DifList a, DifList b){
	if(a==NULL) return b;
	else if((a->fim)==NULL) {
		b=(a->fim);
	}
	return a;
}
*/

//----3






























