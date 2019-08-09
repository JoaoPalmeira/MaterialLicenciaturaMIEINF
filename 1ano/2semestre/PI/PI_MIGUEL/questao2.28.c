int maior (int a , int b){
    if (a>b){
        return a;
    }
    return b;
}

int altura (ABin a){
	if(a==NULL){
		return 0;
	}
	return maior(altura(a->esq),altura(a->dir))+1;
}