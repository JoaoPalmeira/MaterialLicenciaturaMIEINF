// sem free, não o consigo usar.

void remreps (LInt l){
	LInt baux=l,aux,tmp;
	if(l!=NULL){
	aux=l->prox;
	while(l!=NULL && aux!=NULL){
		if(l->valor==aux->valor){
			aux=aux->prox;
			l->prox=aux;

		}else{
		    l=aux;
			aux=aux->prox;
		}
	}
}
l=baux;

}