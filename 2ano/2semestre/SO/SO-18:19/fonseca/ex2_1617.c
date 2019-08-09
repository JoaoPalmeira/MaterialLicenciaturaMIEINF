#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>

int* pids=0;
int pointer=0;
int count=0;

void handler(int signum){
	kill(pids[pointer%count],SIGCONT);
	pointer++;
}

int main(int argc, char const *argv[]){
	signal(SIGUSR1,handler);
	int p = atoi(argv[1]);
	int c = atoi(argv[2]);
	count=c;
	int wt = open("buf.txt",O_CREAT | O_WRONLY,0777);
	int rd = open("buf.txt",O_RDONLY);
	for(int i=0;i<p;i++){
		if(fork()==0){
			dup2(wt,1);
			//produtor 
			execlp("df","df",NULL);
		}
	}
	pids=malloc(sizeof(int)*c);
	for(int i=0;i<c;i++){
		pids[i]=fork();
		if(pids[i]==0){
			kill(getpid(),SIGSTOP);
			dup2(rd,0);
			//consumidor
			execlp("wc","wc",NULL);
		}
	}
	while(1){
		kill(getpid(),SIGUSR1);
		sleep(1);
	}
	return 0;
}