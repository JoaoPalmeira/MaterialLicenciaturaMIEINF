#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, const char *argv[]){
	int i,status;
	for(i=0;i<10;i++){
		pid_t f=fork();
		if(f==0){
			printf("FILHO %d: \nPID: %d, PPID: %d\n",i,getpid(),getppid());
			exit(i);
		}
	}

	for(i=0;i<10;i++){
		int id=wait(&status);
		printf("Terminou FN%d, wstatus %d\n",WEXITSTATUS(status),id);
	}
	return 0;
}
