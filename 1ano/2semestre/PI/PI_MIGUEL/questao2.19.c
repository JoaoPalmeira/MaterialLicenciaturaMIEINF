int take (int n, LInt *l){
	int i;
	LInt *aux,temp;
	if((*l)==NULL){return 0;}
	for(i=0;i<n;i++){
		if((*l)==NULL){return i;}
		else{
		l=&((*l)->prox);
	}
	}
	if((*l)!=NULL){
	aux=&((*l)->prox);
	(*l)=NULL;
	while((*aux)!=NULL){
		temp=*aux;
		*aux=(*aux)->prox;
		free(temp);
	    
	}
	}
return i;