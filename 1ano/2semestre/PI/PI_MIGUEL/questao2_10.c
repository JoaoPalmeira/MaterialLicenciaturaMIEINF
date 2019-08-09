int removeAll (LInt *l, int x){
	int res=0;
	while ((*l)!=NULL){
		if((*l)->valor ==x){
			(*l)=(*l)->prox;
			res++;
		}else{
			l=&((*l)->prox);
		}
	}
	return res;
}