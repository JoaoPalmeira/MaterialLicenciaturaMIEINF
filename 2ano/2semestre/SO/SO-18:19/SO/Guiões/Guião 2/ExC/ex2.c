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
	pid_t filho = fork();
	if(filho == 0){
		// Sou o filho 
		printf("Filho -> O meu pid: %d -> O pid do meu pai: %d\n",getpid(),getppid());
		return -1;
	}
	if(filho == -1){
		perror("Não consegui criar o filho!");
		return -1;
	}
	else{
		// Sou o pai
		printf("Pai -> O meu pid: %d -> O pid do meu pai: %d O pid do meu filho: %d\n",getpid(),getppid(),filho);
	}
	return 0;
}