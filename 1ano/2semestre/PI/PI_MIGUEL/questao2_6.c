int removeOneOrd (LInt *l, int x){
	int res=1;
	while((*l)!=NULL){
        if((*l)->valor ==x){
        	res=0;
        	(*l)=(*l)->prox;
        	break;
        }
        l=&((*l)->prox);
	}
	return res;
}