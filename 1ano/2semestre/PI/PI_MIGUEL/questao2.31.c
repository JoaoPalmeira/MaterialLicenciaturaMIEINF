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

void inorderAux (ABin a , LInt *l){
     if(a!=NULL){
        inorderAux(a->dir,l);
        *l=newLInt(a->valor,*l);
        inorderAux(a->esq,l);
  }
}

void inorder(ABin a, LInt *l){
    *l=NULL;
    inorderAux(a,l);
}

