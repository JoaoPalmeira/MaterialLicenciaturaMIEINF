#include <stdio.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */


// pid_t getpid(void);
// pid_t getppid(void);
// pid_t fork(void);
// void _exit(int status);
// pid_t wait(int *status);
// pid_t waitpid(pid_t pid, int *status, int options);
// int WIFEXITED(int status); /* macro */
// int WEXITSTATUS(int status); /* macro */

int main(){
	pid_t p;
	int i;
	for(i = 0; i<10;i++){
		p = fork();
		if(p){
			wait(0);
			break;
		}
	}
	printf("Pai: %d\nFilho: %d\n",getppid(),getpid());
	return 0;
}