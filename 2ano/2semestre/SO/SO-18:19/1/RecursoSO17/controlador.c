#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <signal.h>

int flag = 0;

void sigHandler(int sinal){
	if(sinal == SIGUSR1){
		flag = 1;
	}
}
 
int main(int argc, char *argv[])
{
	int p = atoi(argv[1]);
	int c = atoi(argv[2]);
	int i,j;
	int status;
	int pd[2];

	pipe(pd);

	for(j= 0; j<c ; j++){

		for(i=0 ; i<p ; i++){
			close(pd[0]);
			dup2(pd[1],1);
			//produtor();
			kill(getpid(),SIGUSR1);
			_exit(0);
		}

		wait(&status);
		close(pd[1]);
		signal(SIGUSR1,sigHandler);

		if(flag == 0){
		
		}
	}


	return 0;
}