ABin somasAcA (ABin a){
	ABin b;
	if(a==NULL) return NULL;
	if(a->esq==NULL && a->dir == NULL){
	b=newABin((a->valor),somasAcA(a->esq),somasAcA(a->dir));
    return b;}
    if(a->esq!=NULL && a->dir != NULL){
	b=newABin((a->valor)+((somasAcA(a->esq))->valor)+((somasAcA(a->dir))->valor),somasAcA(a->esq),somasAcA(a->dir));
    return b;}
    if(a->esq==NULL && a->dir != NULL){
	b=newABin((a->valor)+((somasAcA(a->dir))->valor),somasAcA(a->esq),somasAcA(a->dir));
    return b;}
    if(a->esq!=NULL && a->dir == NULL){
	b=newABin((a->valor)+((somasAcA(a->esq))->valor),somasAcA(a->esq),somasAcA(a->dir));
    return b;}
}
