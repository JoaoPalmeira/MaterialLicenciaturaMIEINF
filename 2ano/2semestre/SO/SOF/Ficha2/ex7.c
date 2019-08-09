#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX 10000

void lelinha(int valor, int i, int matriz[5][MAX]){
	int j, flag=0;
	for(j=0; j<MAX; j++){
		if(matriz[i][j]==valor){
                   printf("Linha: %d\n", i);
		   flag=1;
		   break;
		}
	}
	if(!flag){ printf("Nao encontrado nesta linha\n");}
}

int main(int agrc, char** argv){
	int i,p;
	int matriz[5][MAX];
	matriz[0][678]=atoi(argv[1]);
	matriz[1][678]=atoi(argv[1]);
	matriz[2][678]=atoi(argv[1]);
	matriz[3][678]=atoi(argv[1]);
	matriz[4][678]=atoi(argv[1]);
	for(i=0; i<5; i++){
		p=fork();
		if(p==0){
			//filho
			sleep(1);
			lelinha(atoi(argv[1]), i, matriz);
			_exit(0);
		}
	}
	//pai
	for(i=0; i<5; i++)
		wait(NULL);
}
