#include <stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<fcntl.h>
#include<time.h>
#define LINHAS 6
#define COLUNAS 100000
int main(int argc, char* argv[]){

	
	int matriz[LINHAS][COLUNAS];
	

	int i,j;
	for(i = 0; i<LINHAS ; i++)
		for(j=0;j<COLUNAS;j++)
			matriz[i][j] = rand();

int control;
	if(argc > 1 ){
		int aux = strtol(argv[1],0,10);
		clock_t temp1 = clock();		
		for(i=0;i<LINHAS;i++){
			control = 1;
			for(j=0; j<COLUNAS && control; j++)
				if(matriz[i][j] == aux){ 
							 control = 0;
								
							}}
		clock_t temp2 = clock();
		double time = (temp2-temp1)*1000.0 / CLOCKS_PER_SEC;

		printf("tempo : %g ms\n", time);
	}
	
	
	return 1;
																												

}

