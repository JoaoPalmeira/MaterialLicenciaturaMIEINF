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
	pid_t  pid;
	pid = fork();
	if(pid == 0){
		printf("Sou o filho: pid = %d, ppid = %d\n", getpid(),getppid());	
		return -1;
	}
	if(pid == -1){
		perror("fork");
		return -1;
	}
	else{
		printf("Sou o Pai: pid = %d, ppid = %d, filho = %d\n", getpid(),getppid(),pid);
	}
	return 0;
}

