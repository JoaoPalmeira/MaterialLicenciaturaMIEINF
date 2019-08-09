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



int main(int argc, char* argv[]){
	char* buf = (char*) malloc(sizeof(char));
	int n, i, status;
	int pd[2];
	pipe(pd);
	int counter = 0;

	for(i=0; i<8; i++){
		if(fork()==0){
			close(pd[0]);
			dup2(pd[1],1);
			execlp("./patgrep","./patgrep",argv[1],NULL);
			_exit(0);
		}
	}

	for(i=0;i<8;i++){
		wait(&status);
		close(pd[1]);
		while((n=read(pd[0],buf,1))>0){
			counter++;
		}
	}
	return counter;
}