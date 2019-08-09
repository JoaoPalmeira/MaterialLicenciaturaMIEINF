int dumpAbin (ABin a, int v[], int N){
	int e,d;
	if(a==NULL || N<1) return 0;
	e=dumpAbin(a->esq, v,N);
	if (e==N) return e;
	*(v+e)=a->valor;
	d=dumpAbin(a->dir,v+e+1,N-e-1);
	return e+d+1;
	
}