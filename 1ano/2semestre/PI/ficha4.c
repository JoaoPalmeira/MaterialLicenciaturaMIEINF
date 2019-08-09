#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#define MAX 100

//1

typedef struct nodo *ABin;
struct nodo {
int valor;
ABin esq, dir;
} *ABin;

int soma(ABin t){
	int r;
	if(t==NULL) r=0;
	else r= t->valor + soma (t -> esq) + soma (t -> dir);
	return r;
}

int max(ABin t){
	int m, me, md;
	if(t==NULL) return INT_MIN;
	m = t -> valor;
	me = max (t -> esq);
	md = max (t -> dir);
	if(md>m) m = md;
	if(me>m) m = me;
	return m;
}

int max_NE(ABin t){
	int m, me, md;
	if(m = NULL) exit("error");
	m = t -> valor;
	if(m -> esq != NULL) {
		me = max (t -> esq):
		if(me > m) m = me;
	}
	if(m -> dir != NULL){
		md < max(t -> dir);
		if(md > m) m=md;
	}
	return m;
}









