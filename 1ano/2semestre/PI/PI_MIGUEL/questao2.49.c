
int conta (ABin a){
    if(a==NULL)return 0;
    return 1+conta(a->esq)+conta(a->dir);
}
int quantosMaiores (ABin a, int x){
	int c=0;
	if(a==NULL) return 0;
	while(a!=NULL){
		if(a->valor==x) return c+conta(a->dir);
		else if(a->valor >x){
			c=c+1+conta(a->dir);
			a=a->esq;
		}else{
            a=a->dir;
		}
	}
return c;	
}