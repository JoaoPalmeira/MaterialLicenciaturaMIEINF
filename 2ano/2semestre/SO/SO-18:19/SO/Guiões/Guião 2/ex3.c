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
	int i,status;
	for(i=0; i!=10; i++){
		if(fork() == 0){
			printf("Sou o filho %d: pid = %d, ppid = %d\n", i,getpid(),getppid());
			_exit(i);
		}
		wait(&status);
		if(WIFEXITED(status)){
			printf("pai: exit do filho = %d\n",WEXITSTATUS(status));
		}
		else{
			puts("o filho não terminou...");
		}

	}
	return 0;
}