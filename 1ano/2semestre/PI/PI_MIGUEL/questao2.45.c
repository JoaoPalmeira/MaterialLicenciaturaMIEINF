int lookupAB (ABin a, int x){
    ABin b=a;
	while (a!=NULL){
	if(a->valor ==x) return 1;
	else if(a->valor >x){
        a=a->esq;
	}
	else{
        a=a->dir;
	}
}
a=b;
return 0;
}