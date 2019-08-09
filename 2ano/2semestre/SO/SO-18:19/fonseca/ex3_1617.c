#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>

int* pids=NULL;
int count=0;
char* first=NULL;
char* last=NULL;

void handler(int signum){
	if(fork()==0){
		int fildes[2];
		pipe(fildes);
		if(fork()==0){
			close(fildes[1]);
			dup2(fildes[0],0);
			close(fildes[0]);
			execlp(last,last,NULL);
		}else{
			close(fildes[0]);
			dup2(fildes[1],1);
			close(fildes[1]);
			execlp(first,first,NULL);
		}
	}
	for(int i=0;i<count;i++)
		kill(pids[i],SIGTERM);
}

int main(int argc, char const *argv[]){
	signal(SIGUSR1,handler);
	count=0;
	first=strdup(argv[1]);
	last=strdup(argv[argc-1]);
	pids=malloc(sizeof(int)*argc-1);
	for(int i=1;i<argc;i++,count++){
		int fildes[2];
		int prev;
		pipe(fildes);
		pids[i-1]=fork();
		if(pids[i-1]==0){
			if(i>1){
				dup2(prev,0);
				close(prev);
			}
			close(fildes[0]);
			if(i<argc-1)
				dup2(fildes[1],1);
			close(fildes[1]);
			execlp(argv[i],argv[i],NULL);
		}else{
			if(i>1)
				close(prev);
			prev=fildes[0];
			close(fildes[1]);
		}
	}
	return 0;
}