ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

ABin cloneAB (ABin a){
	ABin b;
	if (a==NULL){
    return NULL;
	}
	b=newABin(a->valor,cloneAB(a->esq),cloneAB(a->dir));
	return b;

}