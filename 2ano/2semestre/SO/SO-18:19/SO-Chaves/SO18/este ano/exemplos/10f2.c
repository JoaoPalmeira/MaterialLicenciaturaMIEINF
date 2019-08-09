#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(){
	int i=0,status,pid;
	for(;i<10;i++){
		pid=fork();
		if(pid>0){
			wait(NULL);
			if(WIFEXITED(status)) printf("Meu filho morreu %d\n",WEXITSTATUS(status));
			exit(0);
		}
		printf("FILHO %d, PID %d PPID %d",i,getpid(),getppid());
		if(i=9) exit(0);
	}

	return 0;
}

