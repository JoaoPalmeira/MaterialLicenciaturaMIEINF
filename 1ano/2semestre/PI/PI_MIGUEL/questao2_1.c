Typedef struct slist{
	int valor;
	struct slist* prox;
}Nodo,*LInt



int length (LInt l){
int res=0;
     while (l!=NULL) {
         res++;
         l=l->prox;
     }
return res;     

}