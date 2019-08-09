int length (LInt l)
{
    int conta=0;
    if(l==NULL) conta=0;
    else
    {
        conta++;
        while(l->prox!=NULL)
        {
            conta++;
            l=l->prox;
        }
    }
    return conta;
}

void freeL (LInt l)
{
	LInt pt;
	while (l!=NULL)
	{
		pt = l;
		l = l->prox;
		free(tmp);
	}
}

void imprimeL (LInt l)
{
	while (l!=NULL)
	{
		printf("%d\n", l->valor);
		l = l->prox;
	}
}

LInt reverseL (LInt l){
    LInt r,tmp;
    r=NULL;
    while(l!=NULL)
    {
        tmp=l;
        l=l->prox;
        tmp->prox=r;
        r=tmp;
    }
    return r;
}

void insertOrd (LInt *l, int x){
    while(((*l)!=NULL)&&((*l)->valor<x))
        l=&((*l)->prox);
    *l=newLInt(x,*l);
}

int removeOneOrd (LInt *l, int x)
{
    LInt* pt = &((*l)->prox);
    while(((*l)!=NULL)&&((*l)->valor<x))
    {
        l=&((*l)->prox);
        pt=&((*pt)->prox);
    }
    if((*l)==NULL) return 1;
    if((*l)->valor==x) {*l=*pt;return 0;}
}

void merge (LInt *r, LInt a, LInt b){
    *r=NULL;
    while(a!=NULL && b!= NULL){
        if(a->valor<=b->valor){
            (*r)=newLInt(a->valor,*r);
            a=a->prox;
        }
        else{
        (*r)=newLInt(b->valor,*r);
            b=b->prox;  
        }
        r=&((*r)->prox);
    }
    if(a==NULL){
        *r=b;
    }
    else *r=a;
}

void splitMS (LInt l, int x, LInt *mx, LInt *Mx)
{
    while (l != NULL)
	{
		if (l->valor<x) 
		{
		    *mx = l;
		    mx = &((*mx)->prox);
		}
		else  
		{
		    *Mx = l; 
		    Mx = &((*Mx)->prox);
		}
		l = l->prox;
	}
	*mx = NULL;
}

LInt parteAmeio (LInt *l)
{
    LInt* tmp = l;
    LInt r = *l;
    int conta=0;
    while(*tmp!=NULL)
    {
        conta++;
        tmp = &((*tmp)->prox);
    }
    conta = conta/2;
    tmp=l;
    while(conta>0)
    {
        conta--;
        tmp = &((*tmp)->prox);
        *l = (*l)->prox;
    }
    if (*l==r) r = NULL;
	else *tmp = NULL;
    return r;
}

int removeAll (LInt *l, int x)
{
    int conta=0;
    LInt tmp;
    while(*l!=NULL)
    {
        if((*l)->valor==x)
        {
            tmp = *l;
            *l = (*l)->prox;
            conta++;
            free(tmp);
        }
        else l=&((*l)->prox);
    }
    return conta;
}

int existeL(LInt *l, int x)
{
    int res=0;
    while(*l != NULL)
    {
        if((*l)->valor==x) res=1;
        l = &((*l)->prox);
    }
    return 1;
}

int removeDups (LInt *l)
{
    int conta=0;
    while(*l != NULL)
    {
        conta+=removeAll(&((*l)->prox),(*l)->valor);
        l = &((*l)->prox);
    }
    return conta;
}

int removeMaiorL (LInt *l)
{
    int res=0;
    LInt tmp=*l;
    while(tmp!=NULL)
    {
        if(tmp->valor>res) res = tmp->valor;
        tmp = tmp->prox;
    }
    while((*l)->valor!=res)
    {
        l = &((*l)->prox);
    }
    tmp = *l;
	*l = (*l)->prox;
	free(tmp);
    return res;
}

void init (LInt *l)
{
    while ((*l)->prox != NULL)
    {
        l = &((*l)->prox);
    }
    free(*l);
	*l = NULL;
}

void appendL (LInt *l, int x)
{
    while(*l!=NULL)
    {
        l = &((*l)->prox);
    }
    *l = (LInt) malloc(sizeof(struct lligada));
    (*l)->valor = x;
	(*l)->prox = NULL;
}

void concatL (LInt *a, LInt b)
{
    while(*a!=NULL)
    {
		a=&((*a)->prox);
    }
	*a = b;
}

LInt cloneL (LInt)
{
	LInt r;
	if(l==NULL) r=NULL;
	else
	{
		r=(LInt)malloc(sizeof(struct lligada));
		r->valor=l->valor;
		r->prox=cloneL(l->prox);
	}
	return r;
}

LInt cloneRev (LInt l)
{
    LInt r,tmp;
    r=NULL;
    while(l!=NULL)
    {
        tmp=r;
        r=(LInt)malloc(sizeof(struct lligada));
        r->valor=l->valor;
        r->prox=tmp;
        l=l->prox;
    }
    return r;
}

int maximo (LInt l)
{
    int max=0;
    while(l!=NULL)
    {
        if(l->valor>max) max=l->valor;
        l=l->prox;
    }
    return max;
}

int take (int n, LInt *l)
{
    int conta=0;
    while(*l!=NULL && conta<n)
    {
        l = &((*l)->prox);
        conta++;
    }
    LInt *tmp;
    while(*l!=NULL)
    {
        tmp = l;
        l = &((*l)->prox);
        free(*tmp);
        *tmp=NULL;
    }
    return conta;
}

int drop (int n, LInt *l)
{
    int conta=0;
    while(*l!=NULL && conta<n)
    {
        free(*l);
        *l = (*l)->prox;
        conta++;
    }
    return conta;
}

LInt NForward (LInt l, int N)
{
    while(l!=NULL && N>0)
    {
        l=l->prox;
        N--;
    }
    return l;
}

int listToArray (LInt l, int v[], int N)
{
    int tamanho=0;
    while(l!=NULL && tamanho<N)
    {
        v[tamanho++]=l->valor;
        l=l->prox;
    }
    return tamanho;
}

LInt arrayToList (int v[], int N)
{
    int i;
    LInt res=NULL;
    for(i=0;i<N;i++)
    {
        appendL(&res,v[i]);
    }
    return res;
}

LInt somasAcL (LInt l) 
{
    int conta;
    LInt res=NULL;
    for(conta=0;l!=NULL;l=l->prox)
    {
        conta+=l->valor;
        append(&res,conta);
    }
    return res;
}

void remreps (LInt l)
{
    LInt tmp,pt;
    while(l!=NULL)
    {
        tmp=l->prox;
        if(tmp!=NULL && tmp->valor==l->valor)
        {
            pt=tmp;
            tmp=tmp->prox;
            l->prox=tmp;
            free(pt);
        }
        else
        {
            l=l->prox;
        }
    }
}

LInt rotateL (LInt l)
{
    LInt pt;
    if(l==NULL) return l;
    else if(l->prox==NULL) return l;
    else
    {
        for(pt=l;pt->prox!=NULL;pt=pt->prox);
        pt->prox=l;
        pt=l;
        l=l->prox;
        pt->prox=NULL;
        return l;
    }
}

LInt parte (LInt l)
{
    LInt res=NULL, *n, *e;
    n = &res;
	e = &l;
	int pos=0;
	while(*e != NULL)
	{
	    if (pos%2!=0)
	    {
			*n = *e;
			n = &((*n)->prox);
			*e = (*e)->prox;
		}
		else
		{
			e = &((*e)->prox);
		}
	    pos++;
	}
	*n = NULL;
    return res;
}

int altura (ABin a)
{
    int conta=0;
    if(a==NULL) conta+=0;
    else
    {
        conta++;
        if(a->dir!=NULL && a->esq!=NULL) conta+=altura(a->dir);
        if(a->dir!=NULL && a->esq==NULL) conta+=altura(a->dir);
        if(a->dir==NULL && a->esq!=NULL) conta+=altura(a->esq);
    }
    return conta;
}

ABin cloneAB (ABin a) 
{
    ABin res;
    if(a==NULL) res=NULL;
    else
    {
        res=(ABin)malloc(sizeof(struct nodo));
        res->valor=a->valor;
        res->dir=cloneAB(a->dir);
        res->esq=cloneAB(a->esq);
    }
    return res;
}

void mirror (ABin *a) 
{
    if(*a!=NULL)
    {
        ABin tmp;
        tmp=(*a)->esq;
        (*a)->esq=(*a)->dir;
        (*a)->dir=tmp;
        ABin *pt;
        if((*a)->dir!=NULL) 
        {
            pt=&((*a)->dir);
            mirror(pt);
        }
        if((*a)->esq!=NULL)
        {
            pt=&((*a)->esq);
            mirror(pt);
        }
    }
}

LInt inorderAux(ABin a, LInt r)
{
	if(a!=NULL)
	{	
		r = inorderAux(a->dir,r);
		r = newLInt(a->valor,r);
		r = inorderAux(a->esq,r);
	}
	return r;
}
void inorder(ABin a, LInt *l)
{
	*l = inorderAux (a,NULL);
}

LInt preorderAux(ABin a, LInt r)
{
	if(a!=NULL)
	{	
		r = preorderAux(a->dir,r);
		r = preorderAux(a->esq,r);
		r = newLInt(a->valor,r);
	}
	return r;
}
void preorder (ABin a, LInt * l) 
{
    *l = preorderAux(a,NULL);
}

LInt posorderAux(ABin a, LInt r)
{
	if(a!=NULL)
	{	
	    r = newLInt(a->valor,r);
		r = posorderAux(a->dir,r);
		r = posorderAux(a->esq,r);
	}
	return r;
}
void posorder (ABin a, LInt * l)
{
    *l = posorderAux(a,NULL);
}

int minEsp(int x,int y)
{
	if (x<=0 && y<=0) return -1;
	else if (y>0 && (x>=y || x<=0)) return y;
	else return x;
}
int depth (ABin a, int x)
{
    int r=0;
	if(a!=NULL)
	{
		if (a->valor == x) r=1;
		else r = 1 + minEsp(depth(a->esq,x),depth(a->dir,x));
	}
	if (r==0) return -1;
	else return r;
}

int freeAB (ABin a)
{
    int conta=0;
    if(a!=NULL)
    {
        ABin b=a;
        free(b);
        conta++;
        conta+=freeAB(a->dir);
        conta+=freeAB(a->esq);
    }
    return conta;
}

int iguaisAB (ABin a, ABin b)
{
    int valido = 1;
    if(a==NULL && b==NULL) return 1;
    else if(a!=NULL && b==NULL) return 0;
    else if(a==NULL && b!=NULL) return 0;
    else if((a->valor==b->valor)&&(iguaisAB(a->dir,b->dir)==1 && iguaisAB(a->esq,b->esq)==1)) valido=1;
    else valido=0;
    return valido;
}
 
int pruneAB (ABin *a, int l)
{
    int nivel=l;
    int conta=0;
    if(*a==NULL) return 0;
    else if(l==0)
    {
       conta+=freeAB(*a);
       *a = NULL;
    }
    else
    {
        conta+=pruneAB(&((*a)->esq),nivel-1);
        conta+=pruneAB(&((*a)->dir),l-1);
    }
    return conta;
}

LInt concat (LInt a, LInt b)
{
	if (a==NULL) a=b;
	else 
	{
	    LInt aux = a; 
		while (aux->prox!=NULL) aux=aux->prox;
		aux->prox = b;
	}
	return a;  
}

LInt nivelL (ABin a, int n)
{
	LInt r, e, d;
	if (a==NULL) r=NULL;
	else if (n==1) r = newLInt(a->valor,NULL);
		else
		{
		    e = nivelL(a->esq,n-1);
			d = nivelL(a->dir,n-1);
			r = concat (e,d);
		}
	return r;
}

int nivelV (ABin a, int n, int* v)
{
    int i=0;
	if(a!=NULL)
	{
	    if(n==1)
	    {
	        v[i]=a->valor;
	        i=1;
	    }
	    else
	    {
	        i+=nivelV(a->esq,n-1,&v[i]);
	        i+=nivelV(a->dir,n-1,&v[i]);
	    }
	}
	return i;
}

int dumpAbin (ABin a, int v[], int N)
{
    int i=0;
    if(a!=NULL)
    {
        i+=dumpAbin(a->esq,&v[i],N);
        if(i<N)
        {
            v[i]=a->valor;
            i++;
            i+=dumpAbin(a->dir,&v[i],N-i);
        }
    }
    return i;
}

int intcabeca (ABin a)
{
    if (a == NULL) return 0;
    else return a->valor;
}

ABin somasAcA (ABin a)
{
    ABin b;
    if (a == NULL) return NULL;
    b = (ABin) malloc (sizeof(struct nodo));
    b->esq = somasAcA(a->esq);
    b->dir = somasAcA(a->dir);
    b->valor = a->valor + intcabeca(b->esq) + intcabeca(b->dir);
    return b;
}

int contaFolhas (ABin a)
{
    int res=0;
    if(a==NULL) res+=0;
    else
    {
        if(a->dir==NULL && a->esq==NULL) res+=1;
        else
        {
            res+=contaFolhas(a->esq);
            res+=contaFolhas(a->dir);
        }
    }
    return res;
}

ABin cloneMirror (ABin a)
{
    ABin res;
    res=cloneAB(a);
    mirror(&res);
    return res;
}

int addOrd (ABin *a, int x)
{
	int r=0;
	while (*a!=NULL) 
	{
		if ((*a)->valor == x) return 1;
		else if ((*a)->valor > x) a = &((*a)->esq);
			else a = &((*a)->dir);
	}
	*a = newABin(x,NULL,NULL);
	return r;
}

int lookupAB (ABin a, int x)
{
    int valido=0;
    if(a!=NULL)
    {
        if(a->valor==x) valido=1;
        else if(lookupAB(a->dir,x) || lookupAB(a->esq,x)) valido=1;
    }
    return valido;
}

int depthOrd (ABin a, int x)
{
    int res=0;
    if(a!=NULL)
    {
        if(a->valor==x) res+=1;
        else if(a->valor>x) res+=1+depthOrd(a->esq,x);
        else res+=1+depthOrd(a->dir,x);
    }
    if(res==0) res=-1;
    return res;
}

int maiorAB (ABin a)
{
    int res=-1;
    if(a!=NULL)
    {
        while(a->dir!=NULL) a=a->dir;
        res=a->valor;
    }
    return res;
}

void removeMaiorA(ABin *a)
{
    if(*a!=NULL)
    {
        if((*a)->dir==NULL && (*a)->esq==NULL) free(*a),*a=NULL;
        else if((*a)->dir==NULL && (*a)->esq!=NULL) *a=(*a)->esq;
        else removeMaiorA(&(*a)->dir);
    }
}

int quantosMaiores (ABin a, int x)
{
    int res=0;
    if(a!=NULL)
    {
        if(a->valor>x) res+=1;
        if(a->esq!=NULL) res+=quantosMaiores(a->esq,x);
        if(a->dir!=NULL) res+=quantosMaiores(a->dir,x);
    }
    return res;
}

void listToBTree (LInt l, ABin *a)
{
    ABin b;
    if (l == NULL) *a = NULL;
    while(l!=NULL)
    {
        b = (ABin) malloc (sizeof (struct nodo));
        b->valor = l->valor;
        b->esq = NULL;
        b->dir = NULL;
        *a = b;
        a = &((*a)->dir);
        l = l->prox;
    }
}

int deProcura (ABin a)
{
    int valido;
    if(a==NULL) valido=1;
    else if((a->dir==NULL && a->esq==NULL)
          ||(a->dir!=NULL && a->esq==NULL && a->valor<a->dir->valor && deProcura(a->dir))
          ||(a->dir==NULL && a->esq!=NULL && a->valor>a->esq->valor && deProcura(a->esq))
          ||(a->dir!=NULL && a->esq!=NULL && a->valor>a->esq->valor && a->valor<a->dir->valor && deProcura(a->esq) && deProcura(a->dir)))
          valido=1;
    else valido=0;
    return valido;
}