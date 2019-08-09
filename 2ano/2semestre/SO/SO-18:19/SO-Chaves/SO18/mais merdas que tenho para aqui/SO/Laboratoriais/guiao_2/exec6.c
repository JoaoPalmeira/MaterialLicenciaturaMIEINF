#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<fcntl.h>
#include<string.h>
#include<time.h>
#define LINHAS 6
#define COLUNAS 100000

int main(int argc,char* argv[]){
	
	
	int matriz[LINHAS][COLUNAS];
	int i,j,f,r,status;
	for( i=0;i<LINHAS ; i++)
		for(int j=0;j<COLUNAS;j++)
			matriz[i][j] = rand(); // ele faz o srand de 0 por omissão


	int aux[LINHAS-1];
        int arg = strtol(argv[1],0,10);
	
	i=0;
		clock_t temp1 = clock();
	while(i<LINHAS){
					
			f = fork();
			aux[i] = f; 
				if(!f){
					for(j=0;j<COLUNAS;j++)
						if(matriz[i][j] == arg) _exit(1);
					_exit(0);
				}
				i++;

	}
	i=0;
	while(i<LINHAS){
			waitpid(aux[i],&status,0);
			//if(status){r++; printf("Foi encontrado o valor na linha %d\n",i);}
			i++;
		 		

	}
		clock_t temp2=clock();
	//	printf("No total foram encontrados %d valores\n",r);
	
		double time = (temp2-temp1) * 1000.0 / CLOCKS_PER_SEC;
		printf("tempo : %g ms\n ",time);
 _exit(0);
}

	






