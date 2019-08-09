void listToBTree (LInt l, ABin *a){
	*a=NULL;
	if(l==NULL) {
		*a=NULL;
	}
	while (l!=NULL){
		*a=newABin(l->valor,NULL,NULL);
	    a=&((*a)->dir);
	    l=l->prox;
	}
}
