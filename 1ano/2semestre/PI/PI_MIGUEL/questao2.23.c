LInt arrayToList (int v[], int N){
	int i;
	LInt *l,aux;
	if(N>0){
	(aux)=newLInt(v[0],NULL);
	l=&aux;
	for(i=1;i<N;i++){
	    l=&((*l)->prox);
		*l=newLInt(v[i],*l);
	}
}else {
	(aux)=NULL;
}
return aux;
}