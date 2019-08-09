int listToArray (LInt l, int v[], int N){
	int i=0;
	while (l!=NULL && i<N){
		v[i++]=l->valor;
		l=l->prox;
	}
return i;
}