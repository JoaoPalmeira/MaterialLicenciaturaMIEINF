ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

int nivelV (ABin a, int n, int v[]){
	int i;
    i=aux (a,n,v,0);
    return i;

}

int aux (ABin a, int n, int v[], int i){
    
}