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


ssize_t readln(int fildes,char *buf, size_t nbyte){
	char* buf1 = (char*) malloc (sizeof(char));
	int n;
	int t = 0;
	while((n = read(fildes,buf1,1))>0 && strcmp(buf1,"\n")){
		strcpy(&buf[t],buf1);
		t++;
	}
	return t;
}

int main(int argc, char* argv[]){
	int nBytes;
	char* buf = (char*) malloc(sizeof(char));
	int par=0;

	for(int i=2; i<argc;i++){
		if(fork()==0){
			char* nome = (char*) malloc(sizeof(char)*100);
			sprintf(nome,"processo_%d",i);
			int fifo = mkfifo(nome,0777);
			int fd = open(nome,O_WRONLY,0777);
			dup2(fd,1);

			while((nBytes=readln(fd,buf,1))>0){
				if(par%2 == 0){
					sleep(atoi(buf));
				}
				else write(1,buf,nBytes);
				par++;
			}
			execlp(argv[i],argv[i],NULL);
			_exit(0);
			}
		wait(NULL);
	}
	return 0; 
}