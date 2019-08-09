ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}
void mirror (ABin *a){
	if(*a!=NULL){
	mirror (&((*a)->esq));
	mirror (&((*a)->dir));	   
	ABin b=(*a)->dir;
	(*a)->dir=(*a)->esq;
	(*a)->esq=b;

}
}