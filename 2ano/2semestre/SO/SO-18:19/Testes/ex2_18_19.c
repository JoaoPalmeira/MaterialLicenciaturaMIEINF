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

int main(int argc, char* argv[]){
	signal(SIGALRM,handler);
	char* buf = (char*) malloc(sizeof(char)*10);
	char* mail = (char*) malloc(sizeof(char)*23);
	int pd[2];
	char* file = strdup(argv[1]);
	int fd = open(file,O_RDONLY,0777);
	int nBytes = 0;
	int status;
	int count=0;

	while((nBytes = read(fd,buf,10))>0){
		char* token = strtok(buf," ");
		sprintf(mail,"%s@alunos.uminho.pt",token);
		pipe(pd);
		if(count<=10){
			alarm(1);
			kill(getpid(),SIGCONT);
			if(fork()==0){
				count++;
				close(pd[1]);
				dup2(pd[0],0);
				execlp("mail","-s","Sistemas_Operativos",mail,NULL);
				_exit(0);
			}
			wait(&status);
			count--;
			close(pd[0]);
			write(pd[1],buf,strlen(buf));
			close(pd[1]);
		}
		else{
			kill(getpid(),SIGSTOP);
		}
	}
	return 0;
}

/*
int i;

for(i=0; i<10 || ((nBytes = read(fd,buf,10))>0) ; i++){
	char* token = strtok(buf," ");
	sprintf(mail,"%s@alunos.uminho.pt",token);
	pipe(pd);

	close(pd[0]);
	write(pd[1],buf,strlen(buf));
	close(pd[1]);

	if(fork()==0){
		count++;
		close(pd[1]);
		dup2(pd[0],0);
		execlp("mail","-s","Sistemas_Operativos",mail,NULL);
		_exit(0);
	}
}


for(j=0; j<10 ; j++){
	wait(&status);
	i--;
}*/







