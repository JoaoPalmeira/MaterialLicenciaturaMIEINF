#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */

// int pipe(pd[2]);

int main(int argc, char* argv[]){
	int pd[2];
	pipe(pd);
	int n;
	char *buffer[1024];
	pid_t processo = fork();
	if(processo == -1){
		perror("Não consegui criar o processo filho\n");
		_exit(0);
	}
	if(processo == 0){
		// Sou o filho
		close(pd[1]);
		dup2(pd[0],0);
		close(pd[0]);
		execlp("wc","wc",NULL);
		perror("Nao consegui executar o exec");
		_exit(1);
	}
	close(pd[0]);
	while((n = read(0,buffer,sizeof(buffer))) > 0){
		write(pd[1],buffer,n);
	}
	close(pd[1]);

	return 0;
}