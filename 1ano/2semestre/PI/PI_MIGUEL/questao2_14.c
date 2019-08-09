void appendL (LInt *l,int x){
	if((*l)==NULL){
		(*l)=newLInt(x,NULL);
		}
	while((*l)!=NULL){
		if((*l)->prox ==NULL){
			(*l)->prox=newLInt(x,NULL);
			break;
		}
		else{
			l=&((*l)->prox);
		}
	}
}