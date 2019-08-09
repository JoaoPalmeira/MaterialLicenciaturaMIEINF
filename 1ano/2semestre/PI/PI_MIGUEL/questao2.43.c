ABin cloneMirror (ABin a){
    ABin b=NULL;
    if(a!=NULL){
	b=newABin(a->valor,cloneMirror(a->dir),cloneMirror(a->esq));
	return b;}
	
}