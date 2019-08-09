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

void handler(int s){
	alarm(s);
}


int main(int argc, char const *argv[]){
	pid_t pid[argc-1];
	int status;
	char* c = (char*) malloc(sizeof(char));
	char* d = (char*) malloc(sizeof(char));
	int n, a, j, k, count=0;


	for (int i = 1; i < argc; i++){
		if ((pid[i]=fork())==0){
			int fd = open("/proc/load",O_RDONLY,0666);
			while((n=read(fd,c,1))>0){
				j=0;
			}
			j=atoi(c);
			if(j>100){
				kill(pid[i],SIGSTOP);
				alarm(1);
				while(j>100){
					int fd2 = open("/proc/load",O_RDONLY,0666);
					while((k=read(fd2,d,1))>0){
						j=0;
					}
					j=atoi(d);
				}
				kill(pid[i],SIGCONT);
			}
			execlp(argv[i],argv[i],NULL);
			_exit(i);
		}
	}
	for (int i = 1; i < argc; i++){
		wait(&status);
		if(WIFEXITED(status)!=0){
			kill(pid[i],SIGKILL);
		}
		else{
			count++;
		}
	}
	if(count==argc-1){
		return 0;
	}
	else return -1;
}