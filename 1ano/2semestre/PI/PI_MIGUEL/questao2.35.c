ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

 
int freeAB (ABin a) {
    if (a==NULL) {
        return 0;
    }
    free(a);
    return(1+freeAB(a->esq)+freeAB(a->dir));
}