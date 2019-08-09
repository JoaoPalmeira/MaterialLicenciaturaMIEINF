ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

// feito pelo ricardo

int pruneAB (ABin *a, int l) {

    int i=0;
    
    if (*a != NULL) {
    
	    if (l == 0) {

	        i = freeAB(*a);
	        *a = NULL;
	    }
	    
	    else {
	    	i = (pruneAB(&((*a)->esq),l-1) + pruneAB(&((*a)->dir),l-1));
	    }

    }
	return i;
}


// feito por mim

 
int freeAB (ABin a){
    int x;
    x=aux(a,0);
return x;
}
int pruneAB (ABin *a, int l){
	int x=pruneABaux ( (*a),l,0);
	return x;
}

int pruneABaux (ABin *a, int l,int i){
	int x=0;
	if (a==NULL) return x;
	if (i>l){
		x=(aux ((*a)->dir,0)+aux((*a)->esq,0))+1+x;
	}else{
        x= pruneABaux ( (*a)->dir,l,i+1)+pruneABaux ( (*a)->esq,l,i+1);
	}
	return x;


}


int aux (ABin a, int x){
    if(a!=NULL){
	ABin tmp=a;
	x=(aux (a->dir,0)+aux(a->esq,0))+1;
	free (tmp);
}
return x;
}