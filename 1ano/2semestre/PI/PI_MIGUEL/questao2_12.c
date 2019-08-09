int removeMaiorL (LInt *l){
	LInt *aux;
	int a=(*l)->valor;
	aux=l;
	while((*l)!=NULL){
       if((*aux)->valor<(*l)->valor){
          aux=l;
          a=(*l)->valor;
       }
l=&((*l)->prox);   
 }
 (*aux)=(*aux)->prox;
	return a;
}