#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	int i;
	pid_t filho;
	int status;

	for(i=0 ; i<10; i++){

		filho = fork();

		if(filho==0){
			printf("FILHO[%d] eu sou o filho %d \n",i,getpid());
			printf("FILHO[%d] o meu pai tem o id %d\n",i,getppid());
			_exit(i+1);
		}
		
		filho = wait(&status);

		printf("Pai[%d] eu sou o pai %d\n",i,getpid());
		printf("Pai[%d] o meu filho é %d\n",i,filho);
		printf("Pai[%d] o meu filho diz %d\n",i,WEXITSTATUS(status));
		
	}
	
	return 0;
}