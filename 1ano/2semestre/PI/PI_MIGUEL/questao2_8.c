void splitMS (LInt l, int x, LInt *mx, LInt *Mx){
	(*mx)=NULL;
	(*Mx)=NULL;
	while (l!=NULL){
		if(l->valor >= x){
           (*Mx)=newLInt(l->valor,*Mx);
           Mx=&((*Mx)->prox);
		}else{
           (*mx)=newLInt(l->valor,*mx);
           mx=&((*mx)->prox);
		}
	l=l->prox;	
	}
}