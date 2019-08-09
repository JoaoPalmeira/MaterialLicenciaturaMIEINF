#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main(int argc, const char *argv[]){

	int res=17;
	int matrix[10][10];
	srand(time(NULL));
	int i,o,status;
	for(i=0;i<10;i++)
		for(o=0;o<10;o++)
			matrix[i][o]=rand()%100;

	for(i=0;i<10;i++){
		if(fork()==0){
		// search this column
			for(o=0;o<10;o++)
				if(matrix[i][o]==res) printf("Encontrei o num %d na linha %d, coluna %d!\n",res,i,o);
			exit(i);
		}
		else{
			wait(&status);
			printf("Terminou a pesquisa na linha %d.\n",WEXITSTATUS(status));
		}
	}


	return 0;
}
