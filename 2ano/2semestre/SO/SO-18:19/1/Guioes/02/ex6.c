#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int encontra(int linha[], int N, int x){
	int i;

	for(i=0 ;i<N;i++){
		if(linha[i]==x){
			return 1;
		}
	}
	return 0;
}

void ex6(int valor){

	int N=10;
	int M=1000;
	int i,j;
	int status;
	int m [N][M];
	int filho;

	for(i=0;i<N;i++){
		for(j=0;j<M;j++){
			m[i][j] = rand()%15;
		}
	}

	for(i=0;i<N;i++){

		if(fork()==0){
			int res = encontra(m[i],M,valor);
			_exit(res);
		}
	}

	int flag = 1;

	for(i=0;i<N && flag;i++){
		
		filho = wait(&status);

		if(WEXITSTATUS(status)){
			printf("O filho %d encontrou o valor %d\n",filho,valor);
			flag=0;
		}
	}
}

int main(int argc, char const *argv[])
{
	int valor = atoi(argv[1]);
	ex6(valor);
	return 0;
}
