#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX 10000

void lelinha(int valor, int i, int matriz[5][MAX]){
	int j, flag=0;
	for(j=0; j<MAX; j++){
		if(matriz[i][j]==valor){
                   printf("Encontrado\n");
		   flag=1;
		}
	}
	if(!flag){ printf("Nao encontrado nesta linha\n");}
}

int main(int agrc, char** argv){
	int i,p;
	int matriz[5][MAX];
	matriz[3][678]=atoi(argv[1]);
	for(i=0; i<5; i++){
		p=fork();
		if(p==0){
			//filho
			lelinha(atoi(argv[1]), i, matriz);
			_exit(0);
		}
	}
	//pai
	for(i=0; i<5; i++)
		wait(NULL);
}
