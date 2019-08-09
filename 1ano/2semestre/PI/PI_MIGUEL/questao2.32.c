ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

LInt newLInt (int x,LInt l);

void preorderAux (ABin a , LInt *l){
     if(a!=NULL){
    	preorderAux(a->dir,l);
        preorderAux(a->esq,l);
        *l=newLInt(a->valor,*l);
  }
}

void preorder(ABin a, LInt *l){
    *l=NULL;
    preorderAux(a,l);
}

