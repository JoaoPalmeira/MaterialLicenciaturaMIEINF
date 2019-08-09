LInt somasAcL (LInt l){
	int a=0, b;
	LInt aux=NULL,*baux;
	baux=&aux;
	while(l!=NULL){
	    b=l->valor;
		(*baux)=newLInt (b+a,*baux);
		a= (*baux)-> valor;
		baux=&((*baux)->prox);
		l=l->prox;
	}
	return aux;
}