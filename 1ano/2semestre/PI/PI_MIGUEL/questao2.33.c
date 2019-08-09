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

void posorderAux (ABin a , LInt *l){
     if(a!=NULL){
     	*l=newLInt(a->valor,*l);
    	posorderAux(a->dir,l);
        posorderAux(a->esq,l);
  }
}

void posorder(ABin a, LInt *l){
    *l=NULL;
    posorderAux(a,l);
}

