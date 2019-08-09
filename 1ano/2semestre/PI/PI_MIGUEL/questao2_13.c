void init (LInt *l){
	LInt temp;
	while((*l)!=NULL){
		if((*l)->prox ==NULL){
			temp=*l;
			(*l)=NULL;
			free(temp);
		}
		else{
			l=&((*l)->prox);
		}
	}
}