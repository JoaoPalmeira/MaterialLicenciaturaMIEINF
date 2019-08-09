LInt rotateL (LInt l){
	LInt aux=l;
	if(l==NULL || l->prox == NULL){
		return l;
	}
	LInt res=l->prox;
	while (l->prox!=NULL){
       l=l->prox;
	}
	aux->prox=NULL;
	l->prox=aux;
	return res;
}