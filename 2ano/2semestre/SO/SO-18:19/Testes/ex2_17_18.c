#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>

//conjunto de programas na linha de comandos, até ter código de saida 0
//Executa concorrentemente (2 fors)
//Primeira nao tem codigo 0, limita o tempo com a soma dos códigos de saida


pid_t pid[3441];
int pidc = 0;
int pidController=0;

void handler(int signal){
	for(int i=0; i<pidc; i++){
		kill(pid[i],SIGKILL);
	}
	kill(pidController,SIGKILL);
}


int main(int argc, char* argv[]){

	signal(SIGALRM,handler);
	char* buf = (char*) malloc(sizeof(char));
	int i = 0;
	int nBytes;
	int time = 0;
	int status;
	int cod_zero=0;
	int pd[2];
	pipe(pd);
	pidController = getpid();
	pidc = argc-1;

	while(!cod_zero){
			for(i=1;i<argc;i++){
				if((pid[i] = fork())==0){
					execlp(argv[i],argv[i],NULL);
					_exit(i);
				}
			}
			
			for(i=1;i<argc;i++){
				wait(&status);
				if(WIFEXITED(status)){
					if(WEXITSTATUS(status)==0){
						kill(pid[i],SIGKILL);
						cod_zero=1;
					}
					else{
						time = time + WEXITSTATUS(status);
					}
				}
			}
			alarm(time);
	}
	return 0;	
}