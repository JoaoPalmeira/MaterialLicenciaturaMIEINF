#include <stdio.h>
#include <stdlib.h>

 // Exercicio 3.1
typedef struct intv{

	int inf;
	int sup;

	struct intv *next;


}* IntV;



IntV reserva(IntV livres,int n,int* reservado)
{
	IntV p = livres;
	IntV aux = NULL;
	while(p){

		if(p->sup - p->inf > n) {
					*reservado = p->inf;
					p->inf += n;
					return livres;
				}

		if(p->sup - p->inf == n){
					*reservado = p->inf;
					
					if(aux!=NULL) {aux->next = p->next;} else {
																free(p);
																p= NULL;
														   	}
					return livres;
				}		
		

				aux=p;
				p=p->next;		

		 }

		 *reservado = -1;
		 return livres;

	}




IntV liberta(IntV livres,int lugar,int n){
	
	IntV p = livres;
	IntV aux = NULL;

	

	while(p){

		if(p->inf == lugar+n)
				{
					if(aux && aux->sup == lugar){
							aux->sup = p->sup;
							aux->next = p->next;
							free(p);
							p=NULL;
       						return livres;}
       				else{
					p->inf = lugar;
					return livres;
					}
				}
		if(p->inf > lugar + n)
				{   
				
					

					if(aux && aux->sup == lugar)	
							{
								
								 aux->sup = lugar + n;
								 return livres;
							}
					
					IntV aux2 = (IntV) malloc(sizeof(struct intv));
					aux2->inf = lugar;
					aux2->sup = lugar+n;
					aux2->next = p;
					
					if(!aux){ 
					return aux2;}
					else{ 
					
					    	aux->next = aux2;
					    	return livres;
						 }


				}
		
		aux = p;
		p=p->next;

	}

 IntV aux2 = (IntV) malloc(sizeof(struct intv));
	  aux2->inf = lugar;
	  aux2->sup = lugar+n;
	  aux2->next = p;

	  return aux2;

}











