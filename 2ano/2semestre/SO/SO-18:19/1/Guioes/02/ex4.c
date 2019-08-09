#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	pid_t filho;
	int i;
	int status;

	for(i=0;i<10;i++){

		filho = fork();

		if(filho==0){
			printf("Filho[%d] eu sou o filho %d\n",i,getpid());
			printf("Filho[%d] o meu pai é %d\n",i,getppid());
			_exit(i+1);
		}
	}

	for(i=0;i<10;i++){

		filho = wait(&status);

		printf("Pai[%d] eu sou pai %d\n",i,getppid());
		printf("Pai[%d] O meu filho é %d\n",i,filho);
		printf("Pai[%d] O meu filho diz %d\n",i,WEXITSTATUS(status));
	}


	return 0;
}