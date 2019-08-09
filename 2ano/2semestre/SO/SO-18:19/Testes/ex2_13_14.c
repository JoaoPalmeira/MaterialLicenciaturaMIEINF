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

int tempo=0;

void handler(int sinal){
	switch(sinal){
		case SIGALRM:
			tempo++;
			alarm(1);
			break;
        default: printf("Sinal invalido...\n");
                 exit(-1);
	}
}

int main(int argc,char* argv[]){
	
	signal(SIGALRM,handler);
	int time = atoi(argv[1]);
	int size = atoi(argv[2]);
	int pd[2], nBytes, contador=0, status;
	char buf;
	pipe(pd);
	alarm(1);

	if(fork()==0){
		close(pd[0]);
		dup2(pd[1],1);
		execvp(argv[3],argv+3);
		_exit(0);
	}
	wait(&status);
	close(pd[1]);
	while((nBytes=read(pd[0],&buf,1))>0){
		pause();
		write(1,&buf,1);
		contador++;
		if(contador>size || tempo>time){
			write(1,"\n",1);
			kill(getpid(),SIGKILL);
		}
	}
	close(pd[0]);
	return 0;
}