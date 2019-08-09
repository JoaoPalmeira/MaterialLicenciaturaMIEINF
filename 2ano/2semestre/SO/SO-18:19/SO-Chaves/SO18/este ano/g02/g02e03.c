#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, const char *argv[]){
	int i,status;
	for(i=0;i<10;i++){
		pid_t f=fork();
		if(f==0){
			printf("PROCESSO FILHO N%d\nPID: %d, PPID: %d\n",i,getpid(),getppid());
			exit(i);
		}
		else{
			wait(&status);
			printf("Terminou o filho com o N %d\n",WEXITSTATUS(status));
		}
	}

	return 0;
}
