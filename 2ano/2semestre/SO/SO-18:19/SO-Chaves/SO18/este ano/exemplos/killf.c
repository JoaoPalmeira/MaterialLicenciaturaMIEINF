#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <signal.h>

void handlerAlrm(int signum){
	printf("recebi um alarme, no processo %d PPID %d \n",getpid(), getppid());
}
void handlerKill(int signum){
	printf("%d is dead!!!\n", getpid());
	exit(0);
}

int main(){
	signal(SIGALRM, handlerAlrm);
	signal(SIGKILL, handlerKill);
	int i,status;
	int pids[10];
	for(i=0;i<10;i++){
		if(!(pids[i]=fork())){
			printf("Sou o filho %d, com o ID %d e PPID %d\n",i+1,getpid(),getppid());
			while(1){
				alarm(5);
				pause();
			}
		}
	}
	int j;
	for(j=0;j<10;j++){
		kill(SIGKILL,pids[j]);
	}
	wait(NULL);

	return 0;
}

