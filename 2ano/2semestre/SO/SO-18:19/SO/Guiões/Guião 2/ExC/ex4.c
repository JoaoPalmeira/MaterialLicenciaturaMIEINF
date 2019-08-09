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
	pid_t pid[10];
	int i,j;

	for(i=0;i<10;i++){
		if((pid[i] = fork()) == 0){
			printf("Pai: %d\nFilho: %d\n",getppid(),getpid());
			_exit(i+1);
		}
	}
	for(j=0; j<10;j++){
		int s;
		waitpid(pid[j],&s,0);
		if(WIFEXITED(s)){
			printf("O filho numero %d terminou com %d\n",j+1,WEXITSTATUS(s));
		}
	}
	return 0;
}