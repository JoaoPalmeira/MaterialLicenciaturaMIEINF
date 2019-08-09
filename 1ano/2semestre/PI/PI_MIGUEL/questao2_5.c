
Nodo*newNode(int x,LInt l){
	Nodo* res=(Nodo*)malloc(sizeof(Nodo));
	res->valor=x;
	res->prox=l;
	return res;
}

void inserOrd (LInt *l,int){
	LInt aux;
	if(l==NULL||l->valor>=x){
		newNode(x,*l);
	}
	for(aux=l;aux->prox!=NULL&& aux->prox->valor<x ; aux=aux->prox);
		aux->prox=newNode(aux->prox,x);
	return l;

}
/*
void insertOrd (LInt *l, int x)  {
    while ((*l)!=NULL) {
        if (x<((*l)->valor)) {
            break;
        } 
        l=&((*l)->prox);
    }
    *l=newLInt (x, *l);
}
*/

