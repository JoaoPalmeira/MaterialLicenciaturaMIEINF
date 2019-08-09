ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

int aux (int x, int y) {
    if (x==-1 && y==-1) {
        return -1;
    }
    else if (x==-1 && y!=-1) {
        return y+1;
    }
    else if (y==-1 && x!=-1) {
        return x+1;
    }
    else if (x>y) {
        return y+1;
    }
    else {
        return x+1;
    }
}

int depth (ABin a, int x) {
    if (a==NULL) {
        return -1;
    }
    if ((a->valor)==x) {
        return 1;
    }
    else {
        return (aux(depth((a->dir),x),depth((a->esq),x)));
    }
    
}

