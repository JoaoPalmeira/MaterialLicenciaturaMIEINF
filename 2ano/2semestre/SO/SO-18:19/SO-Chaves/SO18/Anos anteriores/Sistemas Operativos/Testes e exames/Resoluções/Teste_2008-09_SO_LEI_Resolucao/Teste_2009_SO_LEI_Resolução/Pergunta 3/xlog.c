#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

int main (int argc , char **argv){
	int fd;
	int i;
	fd = open("xlog.log",WR_ONLY | OCREAT , 0666);

	for(i=1;i<argc;i++){
		if(!fork()){
			dup2(fd,1);
			execlp(argv[i],argv[i],NULL);
			close(fd);
			exit(EXIT_FAILURE);
		}
	}
	for(i=1;i<argc;i++){ wait(0);}
	exit(0);
}