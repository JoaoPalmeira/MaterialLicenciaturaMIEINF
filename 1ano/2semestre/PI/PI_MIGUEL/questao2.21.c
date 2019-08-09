LInt NForward (LInt l, int N){
	int i=0;
	while(i<N){
		l=l->prox;
		i++;
	}
return l;
}