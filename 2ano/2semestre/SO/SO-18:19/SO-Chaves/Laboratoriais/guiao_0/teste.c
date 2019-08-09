#include <stdio.h>
#include <ctype.h>
#include "exercicio1.h"
#include <stdlib.h>



void imprime(IntV livres);

int main(){
	int control,nlugares,reservado,plugar;
	printf("Uma função para fazer testes....\n");
	printf("1--> SIM\n2--> NÃO\n");


	// cria um estádio vazio
	IntV livres = (IntV) malloc(sizeof(struct intv*));
	livres->inf = 0;
	livres-> sup = 65535;
	livres->next = NULL;

	do{
		printf("\n1-Reservar lugares\n");
		printf("2-Libertar lugares\n");
		printf("3-Imprimir\n");
		printf("4-Sair do Menu testes\n\n");
		scanf("%d",&control);

		switch(control){

			case 1: printf("\nQuantos lugares quer reservar?\n");
					scanf(" %d",&nlugares);
					reserva(livres,nlugares,&reservado);
					if(reservado == -1) printf("Foi impossivel reservar lugares\n\n");
					else printf("A reserva foi efetuada:\n lugar %d\n\n",reservado);
					
					imprime(livres);

					break;
			

			case 2: printf("\nIntroduza o primeiro lugar :\n");
					scanf(" %d",&plugar);
					printf("Quantos quer libertar?\n");
					scanf(" %d",&nlugares);
					livres = liberta(livres,plugar,nlugares);
				
					imprime(livres);
					break;
			


			case 3:	imprime(livres);
					break;
			default : control = 4;


		}

	}while(control != 4);

	return 1;
}




void imprime(IntV livres){

	if(!livres) printf("Não existem lugares livres!!!!! \n\n");


	else{
			while(livres){
			
					printf("\n--------------\n");
					printf("| [ %d , %d ]   |\n",livres->inf,livres->sup);
					printf("-------|--------\n");
					printf("       v        ");

					
				livres=livres->next;
					
			}



		}

}

