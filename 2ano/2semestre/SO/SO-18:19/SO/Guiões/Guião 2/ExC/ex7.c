#include <stdio.h>
#include <stdlib.h>
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

// Dúvida -> pq que terminam todos com o mesmo status ??


int c = 4;
int l = 10000; 

int main(int argc, char* argv[]){
	int Mat[c][l];
	int i,j,status;

	for(i = 0; i<c;i++){
		for(j = 0;j<l;j++){
			Mat[i][j] = -1;
		}
	}

	Mat[1][5000] = Mat[2][1000] = Mat[0][9999] = Mat[2][5] = 7;
	int x = atoi(argv[1]);
	pid_t filhos[c];

	for(i = 0; i < c; i++){
		filhos[i] = fork();
		if(filhos[i] == -1){
			perror("Não consegui criar o filho");
		}

		if(filhos[i] == 0){
			for(j = 0; j<l;j++){
				if(Mat[i][j] == x){
					printf("Encontrei na linha %d e coluna %d o numero %d\n", i,j, x);
				}
			}
			_exit(i);
		}
	}

	for(i = 0; i< c;i++){
		waitpid(filhos[i],&status,0);
		if(WIFEXITED(status)){
			printf("O filho acabou com %d\n", WEXITSTATUS(&status));
		}
	}
}