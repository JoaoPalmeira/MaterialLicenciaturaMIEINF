#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>

int time = 0;

void handler(int signal){
	switch(signal){
		case SIGUSR1: time++;
					  break;
		case SIGUSR2: time--;
					  break;
		case SIGALRM: alarm(time);
					  break;
		default: exit(-1);
	}
}


int main(int argc, char* argv[]){
	signal(SIGUSR1,handler);
	signal(SIGUSR2,handler);
	signal(SIGALRM,handler);
	time = atoi(argv[argc-1]);
	int pd[2];
	int status;
	int nBytes=0;
	char* buf = (char*) malloc(sizeof(char));
	pipe(pd);

	if(fork()==0){
		close(pd[0]);
		dup2(pd[1],1);
		execlp("cat","cat","/etc/passwd",NULL);
		_exit(0);
	}
	wait(&status);
	close(pd[1]);
	alarm(time);
	while((nBytes=read(pd[0],buf,1))>0){
		if(!strcmp(buf,"\n")){
			pause();
		}
		write(1,buf,nBytes);
	}
	close(pd[0];)
	return 0;
}