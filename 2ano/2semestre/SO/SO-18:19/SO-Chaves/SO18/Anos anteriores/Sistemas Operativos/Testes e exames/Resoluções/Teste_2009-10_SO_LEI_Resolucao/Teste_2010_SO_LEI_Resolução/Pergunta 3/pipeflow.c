#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>


int fluxo = 0;

void fluxo_alarm(){
	printf("Fluxo : %d\n",fluxo);
	fluxo = 0;
	alarm(1);
}

int main (int argc , char **argv){

	int fd1[2];
	int fd2[2];
	pipe(fd1);
	pipe(fd2);
	char buf[512];

	signal(SIGALRM,fluxo_alarm);

	alarm(1);

	if(!fork()){
		dup2(fd1[1],1);
		close(fd1[0]);
		execlp(argv[1],argv[1],NULL);
		close(fd1[1]);
		exit(EXIT_FAILURE);
	}
	close(fd1[1]);
	while(read(fd1[0],buf,1) != 0){
			write(fd2[1] , buf , 1);
			fluxo++;
		}
		
	if(!fork()){
		dup2(fd2[0],0);
		close(fd2[1]);
		execlp(argv[2],argv[2],NULL);
		close(fd2[0]);
		exit(EXIT_FAILURE);
	}
	close(fd1[0]);
	close(fd2[0]);
	close(fd2[1]);
	wait(0);
	wait(0);
	return 1;
}