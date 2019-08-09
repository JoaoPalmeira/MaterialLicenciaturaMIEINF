ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

LInt aux (ABin a,int n , int i , LInt l);


LInt nivelL (ABin a, int n){
	LInt l=NULL;
	l= aux ( a, n , 0, &l);
    return l;
}

void concatL (LInt *a, LInt b){
	while ((*a)!=NULL){
		a=&((*a)->prox);
	}
	*a=b;
	
}

LInt aux (ABin a,int n , int i , LInt l){
	if(a!=NULL && i==n){
       l=newLInt (a->valor,l);
       return l;
	}else if(a==NULL){
		return l;
	}else if(a!=NULL && i!=n){
		concatL(aux(a->esq,n,i+1,&l),aux(a->dir,n,i+1,&l));
	}
return l;	
}
// afonso e bem feita --'

void nivelaux(ABin a, int n, LInt *l){
    if (a==NULL) return;
    if (n>1){
        nivelaux(a->dir, (n-1), l);
        nivelaux(a->esq, (n-1), l);
    }
    else if(n == 1){
        *l = newLInt(a->valor, *l);
    }
}

LInt nivelL (ABin a, int n){
    LInt l = NULL;
    nivelaux(a, n, &l);
    return l;
}
