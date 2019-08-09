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
	int list[N];
	int res=0;

	for(i=0;i<N;i++){
		for(j=0;j<M;j++){
			m[i][j] = rand()%1024;
		}
	}

	for(i=0;i<N;i++){

		if(fork()==0){
			res = encontra(m[i],M,valor);
			if(res==1)
				_exit(res);
			else
				_exit(0);
		}
	}

	for(i=0;i<N ;i++){
		
		filho = wait(&status);

		if(WEXITSTATUS(status)){
			list[i] = 1;
		}
	}

	for(i=0;i<N;i++){
		if(list[i]==1){
			printf("Encontrado na linha %d\n",i);
		}
	}
}

int main(int argc, char const *argv[])
{
	int valor = atoi(argv[1]);
	ex6(valor);
	return 0;
}