LInt elimina2 (LInt l){
    LInt aux=l->prox;
    l->prox=aux->prox;
    return l;
    
}
LInt parte (LInt l){
	int i=1;
	LInt aux=NULL,*aux2,aux3,aux4;
	aux3=l;
	aux2=&aux;
	if(l==NULL||l->prox==NULL){
	}else{
	while (l!=NULL){
		if(i%2==0){
           (*aux2)=newLInt(l->valor,(*aux2));
           aux2=&((*aux2)->prox);
		}
		l=l->prox;
		i++;
	}
	l=aux3;
	aux4=(l->prox);	
	while(l!=NULL && aux4!=NULL){
	    if(aux4->prox==NULL){
	    l=elimina2(l);
	    break;     
	    }else{
	    l=elimina2(l);     
	    l=l->prox;    
	    aux4=l->prox;
       
}
	}
	}
l=aux3;
return aux;
}


// resolução do quim

LInt parte (LInt l){
    LInt *i,*p,r=NULL;
    int c=1;
    p=&r;
    i=&l;
    while(*i!=NULL){
        if(c%2==0){
            *p=newLInt((*i)->valor,*p);
            p=&((*p)->prox);
            *i=(*i)->prox;
        }
        else{
            i=&((*i)->prox);
        }
        c++;
    }
    return r;
}