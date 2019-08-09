#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */


// int execl(const char *path, const char *arg0, ..., NULL);
// int execlp(const char *file, const char *arg0, ..., NULL);
// int execv(const char *path, char *const argv[]);
// int execvp(const char *file, char *const argv[]);

int main(int argc,char* argv[]){	
	execv(argv[1],&argv[1]);
	return 1;
}

/*
int main(int argc,char* argv[]){
	pid_t filho = fork();

	if(filho == -1){
		perror("Não consegui criar o filho");
	}

	if(filho == 0){
		execv(argv[1],&argv[1]);
		perror("Nâo consegui executar");
		_exit(0);
	}

	wait(NULL);
	printf("Sou o pai\n");
}
*/