#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void criaFilhos(int n){

	int status;
	pid_t filho;

	if(n>=0){

		filho = fork();

		if(filho==0){
			printf("Eu sou o Filho[%d]: %d -> E o meu Pai é: %d\n",n+1,getpid(),getppid());
			criaFilhos(n-1);
			wait(&status);
		}
	}
	else{
		_exit(0);
	}
}

int main(int argc, char const *argv[])
{
	int N = atoi(argv[1]);
	int i=0;
	int status;

	printf("Criar %d de filhos",N);

	criaFilhos(N);

	wait(&status);

	_exit(0);
	return 0;
}