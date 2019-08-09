ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

int iguaisAB (ABin a, ABin b){
	int i;
	if (a==NULL && b==NULL){
		i=1;
	}else{
		if(a==NULL){
		   return 0;
		}elseif(b==NULL){
			return 0;
		}
	if(a->valor == b->valor){
		i=0;
		i=iguais(a->dir,b->dir)+iguais(a->esq,b->esq);
	}
}
return i;	
}

/* fonseca

int iguaisAB (ABin a, ABin b) {

	if ((a == NULL) && (b == NULL)){
        return 1;
	}
	
	if (((a == NULL) && (b != NULL)) || ((b == NULL) && (a != NULL))){
        return 0;
	}
	
	if (( a->valor == b->valor ) && iguaisAB(a->esq, b->esq) && iguaisAB(a->dir, b->dir)){
		return 1;
	}

	return 0;

}
*/