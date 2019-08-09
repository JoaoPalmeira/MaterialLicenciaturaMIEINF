#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 100

// DEFINIÇAO DOS TIPOS
//-----1
typedef struct stack {
int sp;
int valores [MAX];
} STACK;
//--a

void initStack (STACK *s){
	(*s).sp = 0;
	//s -> sp = 0;        alternativa
}

//--b

int isEmptyS (STACK *s){
	if (s -> sp == 0) return 1;
	else return 0;
	// return (s -> sp ==0);       alternativa
}

//--c

 int isFull (STACK *s){
	if (s -> sp == MAX) return 1;
	else return 0;
}

 int push (STACK *s, int x){
 	if(isFull (s)) return 1;
 	else {
 		(s -> valores)[s -> sp] = x; 
 		return 0; 
 		(s -> sp)++;
 	}
 }


//--d

int pop (STACK *s, int *x){
	if(isEmptyS (s)) return 1;
	else {
		*x = (s -> valores)[s -> sp-1];
		(s -> sp)--;
		return 0;
	}
}

//--d

int top (STACK *s, int *x){
	if(isEmptyS (s)) return 1;
	else {
		*x = (s -> valores)[s -> sp-1];
		return 0;
	}
}

//-----2

#define MAX 100
typedef struct queue {
int inicio, tamanho;
int valores [MAX];
} QUEUE;

//--a

void initQueue (QUEUE *q){
	(q -> tamanho) = 0;
}

//--b

int isEmptyQ (QUEUE *q){
	if((q -> tamanho)==0) return 1;
	else return 0;
}

//--c

int enqueue (QUEUE *q, int x) {
	int r;
	if((q->tamanho) == MAX) r = 1;
	else{
		r=0;
		if(((q -> inicio) + (q -> tamanho) - 1) == MAX) {
			(q -> valores)[0] = x; 
			(q -> tamanho)++;
		}
		else{
			(q -> valores) [(q -> inicio) + (q -> tamanho)] = x; 
			(q -> tamanho)++;
		}
	}
	return r;
}

/* alternativa
int enqueue (QUEUE *q, int x) {
	if ( q -> tamanho == MAX) return 1;
	(q -> valores)[(q -> inicio) + (q -> tamanho)%MAX] = x;
	(q -> tamanho)++;
	return 0;
}
*/

//--d

int dequeue (QUEUE *q, int *x){
	if(isEmptyQ (q)) return 1;
	else {
		*x = (q -> valores)[q -> inicio];
		(q -> tamanho)--;
		(q -> inicio)++;
		return 0;
	}
}

/* alternativa stor
int dequeue (QUEUE *q, int *x){
	if((q -> tamanho)==0) return 1;
	*x = (q -> valores)[q -> inicio];
	q -> inicio = (q -> inicio +1)%MAX;
	(q -> tamanho)--;
	return 0;
}

//--e

int front (QUEUE *q, int *x){
	if(isEmptyQ (q)) return 1;
	else {
		*x = (q -> valores)[q -> inicio];
		return 0;
	}
}
*/


// LISTAS LIGADAS
//-----1
typedef struct slist *LInt;
typedef struct slist {
	int valor;
	LInt prox;
} Nodo;

//--a

LInt a, novo = malloc (SIZEOF (struct slist));
(novo -> valor) = 15;
(novo -> prox) = NULL;
a = novo;
novo = malloc(SIZEOF (struct slist));
(novo -> valor) = 5;
(novo -> prox) = a;
a = novo;
novo = malloc(SIZEOF (struct slist));
(novo -> valor) = 10;
(novo -> prox) = a;
a = novo;

//--a2  exercicio do stor

LInt a, ult, novo = malloc (SIZEOF (struct slist));
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

//--b
//-i

LInt cons (LInt l, int x){
	LInt novo = malloc (SIZEOF(struct slist));
	(novo -> valor) = x;
	(novo -> prox) = l;
	return novo;
}

//-ii

LInt tail (LInt l) {
	LInt t;
	if (l==NULL) return NULL;
	t=l;
	l=(l -> prox);
	free(t);
	return l;
}

//-iii

LInt init (LInt l){
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

//-iv

LInt snoc (LInt l, int x){
	LInt novo = malloc(SIZEOF(struct slist));
	(novo -> valor)=x;
	(novo -> prox)=NULL;
	if(l==NULL) return novo;
	t=l;
	while(t -> prox != NULL)
		t = (t -> prox);
	(t -> prox) = novo;
	return l;
}

//-v

LInt concat (LInt a, LInt b){
	
}




















