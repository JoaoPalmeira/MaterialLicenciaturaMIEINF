#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <string.h>
char *filename;
int pid;
void alrmHandler(int signum){
	kill(pid,SIGSTOP);
	if(fork()==0){
		execlp("logrotate","logrotate",filename,"NULL");
	}
	wait(0);
	alarm(10);
	kill(pid,SIGCONT);
}

int main(int argc, char *argv[]){
	if(argc<2){
		perror("falta de argumentos");
		exit(-1);
	}
	signal(SIGALRM,alrmHandler);
	filename=(char*)malloc((strlen(argv[1])+strlen("/exame0910/log/"))*sizeof(char));
	asprintf(&filename,"/exame0910/log/%s.txt",argv[1]);
	int fd=open(filename,O_CREAT|O_RDWR|O_APPEND,0777);
	if(fd==-1){
		perror("erro na abertura do ficheiro");
		exit(-1);
	}
	if((pid=fork())==0){
		dup2(fd,1);
		execlp(argv[1],argv[1],"NULL");
		exit(EXIT_FAILURE);
	}
	wait(NULL);
	close(fd);
	return 0;
}
