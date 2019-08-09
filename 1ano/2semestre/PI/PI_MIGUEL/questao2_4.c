LInt reversL (LInt l) {
	LInt antes = Null, depois=l;
    while(l!=NULL){
    depois=l->prox;
    l->prox= antes;
    antes=l;
    l=depois;
    }
return antes;    
}