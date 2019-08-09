void merge (LInt *r, LInt a, LInt b){
    *r=NULL;
	while(a!=NULL && b!= NULL){
		if(a->valor<=b->valor){
			(*r)=newLInt(a->valor,*r);
			a=a->prox;
		}
		else{
		(*r)=newLInt(b->valor,*r);
			b=b->prox;	
		}
		r=&((*r)->prox);
	}
	if(a==NULL){
	    *r=b;
	}
	else *r=a;
}