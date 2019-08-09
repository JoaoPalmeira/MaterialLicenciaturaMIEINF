int drop (int n, LInt *l){
	int i;
	LInt tmp;
	if((*l)==NULL){return 0;}
	for(i=0;i<n;i++){
		if((*l)==NULL){return i;}
		else{
		tmp = *l	
		*l=(*l)->prox;
		free(tmp);
	}
	
	}
return i;
}