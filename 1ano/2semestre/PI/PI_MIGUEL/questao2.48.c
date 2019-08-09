

void removeMaiorA (ABin *a){
	//ABin b =*a;
	if(*a==NULL)return ;
	while(((*a)->dir)!=NULL){
        a=&((*a)->dir);
    }
    (*a)=(*a)->esq;
    //*a=b
}