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
	int i;
	for(i=0;i<10;i++){
		if(fork() == 0){
			printf("O meu pid: %d O pid do meu pai: %d\n", getpid(),getppid());
			_exit(i+1);
		}
		int s;
		wait(&s);
		if(WIFEXITED(s)){
			printf("O filho terminou com: %d\n",WEXITSTATUS(s));
		}
	}
	return 0;
}