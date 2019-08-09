

int existe (int v[],int n,int x){
    int i;
    for(i=0;i<n;i++){
        if(v[i]==x){
            return 1;
        }
    }
    return 0;
}


int remreps (LInt *l){
	int x[200];
	int i=0,j,a=0;
	if((*l)==NULL) return 0;
	x[0]=(*l)->valor;
	while ((*l)!=NULL){
    		if(existe(x,i,(*l)->valor)){
    		    (*l)=(*l)->prox;
    		}else{
    		    x[i++]=(*l)->valor;
    		    l=&((*l)->prox);
    		}
	}
return 0;
}