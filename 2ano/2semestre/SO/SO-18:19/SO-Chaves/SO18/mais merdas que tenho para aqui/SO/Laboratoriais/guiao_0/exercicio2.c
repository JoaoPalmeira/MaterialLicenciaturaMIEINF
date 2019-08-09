#include <stdio.h>
#include <stdlib.h>

typedef intv** PtIntV;

// Exercicio 3.2
int reserva(struct intv** livres, int n){
	int ret;
	IntV* p = NULL;
	while(livres){
			if((*livres)->sup - (*livres)->inf > n)
					{
						ret = (*livres)->inf;
						(*livres)->inf +=n;
						return ret;
					}
			if((*livres)->sup - (*livres)->inf == n)
					{
						ret = (*livres)->inf;

						if(!p) {p->next = (*livres)->next;}
						else {
								free(livres);
								livres = NULL;

							}


						return ret;



					}

		p = livres;
		livres = &((*livres)->next);			

	}

	return -1;

}



void liberta(PtIntV livres, int lugar, int n){

	


}


