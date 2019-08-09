void concatL (LInt *a, LInt b){
	while ((*a)!=NULL){
		a=&((*a)->prox);
	}
	*a=b;
}