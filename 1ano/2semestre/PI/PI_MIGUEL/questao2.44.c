int addOrd (ABin *a, int x){
	while (*a!=NULL){
	if((*a)->valor ==x) return 1;
	else if((*a)->valor >x){
        a=&((*a)->esq);
	}
	else{
        a=&((*a)->dir);
	}
}
*a=newABin(x,NULL,NULL);
return 0;
}