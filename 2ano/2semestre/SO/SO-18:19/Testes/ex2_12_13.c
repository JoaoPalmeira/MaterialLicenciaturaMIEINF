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

int processos=0;
int n = 5;

void handler1(){
	processos--;
}

void handler2(){
	n--;
}

void handler3(){
	n++;
}


int main(int argc, char* argv[]){
	
	signal(SIGCHLD,handler1);
	signal(SIGUSR1,handler2);
	signal(SIGUSR2,handler3);

	while(1){
			if(processos<n){
				processo++;
				if(fork()==0){
					execvp(argv[1],argv+1);
					_exit(0);
				}
			}
	}
	return 0;
}