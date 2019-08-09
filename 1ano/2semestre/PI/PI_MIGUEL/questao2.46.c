int depthOrd (ABin a, int x){
    ABin b=a;
    int i=0;
	while (a!=NULL){
	i++;	
	if(a->valor ==x) return i ;
	else if(a->valor >x){
        a=a->esq;
	}
	else{
        a=a->dir;
	}
}
a=b;
return -1;
}
