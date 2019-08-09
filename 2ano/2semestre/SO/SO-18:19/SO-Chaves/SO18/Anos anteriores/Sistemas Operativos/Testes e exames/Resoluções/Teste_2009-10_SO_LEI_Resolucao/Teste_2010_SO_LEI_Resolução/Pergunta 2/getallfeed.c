#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>


int main (int argc, char **argv){

	int len = 1024;
	int i = 0;
	int fd[2];
	int counter = 0;
	char buf[1024];
	pipe(fd);
	dup2(fd[0],0);
	for(i;i<argc;i++)
	{
		counter++;
		if(!fork()){			
			dup2(fd[1],1);
			close(fd[0]);
			execlp("cocoxx","cocoxx",argv[i],NULL);
		}else{
			while (getline(&buf,&len,0)){
			if(NULL == strstr(buf,"apple"))
				printf("%s",buf);
		}
		}
		if (counter>9){
			wait(0);
			counter--;
		}
	}
	return 0;
}