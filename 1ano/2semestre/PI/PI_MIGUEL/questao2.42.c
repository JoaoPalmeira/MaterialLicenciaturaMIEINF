int contaFolhas (ABin a){
	if(a==NULL) return 0;
	if(a->dir==NULL && a->esq==NULL) return 1;
	return (contaFolhas(a->esq)+contaFolhas(a->dir));
}