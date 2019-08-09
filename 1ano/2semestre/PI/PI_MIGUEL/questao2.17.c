LInt cloneRev (LInt l){
	LInt aux = NULL;
	while (l!=NULL){
		aux=newLInt(l->valor,aux);
		l=l->prox;
	}
return aux;	
}

