#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */


// int execl(const char *path, const char *arg0, ..., NULL);
// int execlp(const char *file, const char *arg0, ..., NULL);
// int execv(const char *path, char *const argv[]);
// int execvp(const char *file, char *const argv[]);

int main(int argc,char *argv[]){
	int i;
	for(i = 1; i != argc; i++){
		if(fork() == 0){
			execvp(argv[1], &argv);
			_exit(0);
		}
	}
	return 0;
}