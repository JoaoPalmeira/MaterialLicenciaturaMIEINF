typedef struct lligada {
    int valor;
    struct lligada *prox;
} *LInt;

LInt newLInt (int v, LInt t){
    LInt new = (LInt) malloc (sizeof (struct lligada));
    
    if (new!=NULL) {
        new->valor = v;
        new->prox  = t;
    }
    return new;
}

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


int testaord(LInt *l){
    if(*l==NULL) return 1;
    while(*l!=NULL){
        if((*l)->prox==NULL) return 1;
    else if((*l)->valor<=(*l)->prox->valor){
        l=&((*l)->prox);
    }else{
        return 0;
    }
    }
}


int deProcura (ABin a){
    LInt *l;
    inorder(a,l);
    return testaord(l);
}