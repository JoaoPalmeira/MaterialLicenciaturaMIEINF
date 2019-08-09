#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

int pid;

void fecharprogs(int x){
	kill(pid,SIGTERM);
	exit(0);
}

int main (int argc , char **argv){
	signal (SIGINT,fecharprogs);
	int *es;
	while(1){
		if(!(pid = fork())){
			execlp("renovaveisnodia","renovaveisnodia",NULL);
		}
		else{
			wait(es);
			if(!WEXITSTATUS(es)) exit(0);
		}
	}
}