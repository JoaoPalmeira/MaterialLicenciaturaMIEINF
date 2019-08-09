int maximo (LInt l){
	int i=l->valor;
	while(l!=NULL){
	    if(i<l->valor){
	        i=l->valor;
	    }
	    l=l->prox;
	}
	return i;
}