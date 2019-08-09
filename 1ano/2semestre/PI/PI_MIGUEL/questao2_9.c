int tamanho (LInt l){
	int res=0;
	while(l!=NULL){
		res++;
		l=l->prox;
	}
return res;	
}

LInt parteAmeio (LInt *l){
LInt x=*l,aux;
int a;
int b=0;
if(*l==NULL || (*l)->prox==NULL) return NULL;
a= tamanho(*l);
for (b=0;b<a/2;b++){
    aux=*l;
	*l=((*l)->prox);
}
aux->prox=NULL;
return x;
}