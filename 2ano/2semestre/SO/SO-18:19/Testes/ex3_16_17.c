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

//pesquisa concorrente (2 fors) para determinar a existencia de determinado padrao em linhas de texto do stdin
//filtro e existe, que encadeados permitem fazer a pesquisa
//filtro -> descarta os casos mais obvios e passa o resto para o stdout
//existe -> valor exit 1 caso tenha o padrao na linha ou valor exit 0 caso nao aconteça ate ao EOF
//recebe argumento n e executar n pares (filtro e existe)
//imprimir resultado assim que possivel e terminar processos que nao estejam a fazer algo relevante

ssize_t readln(int fildes,char *buf, size_t nbyte){
	char* buf1 = (char*) malloc (sizeof(char));
	int n;
	int t = 0;
	while((n = read(fildes,buf1,1))>0 && strcmp(buf1,"\n")){
		strcpy(&buf[t],buf1);
		t++;
	}
	return t;
}

int main(int argc, char const *argv[]){
	int n = atoi(argv[1]);
	int t=0, status;
	char* buf= (char*) malloc(sizeof(char));
	int pd[2], i,count;
	while((t=readln(0, buf, sizeof(buf)))>0){
		for(i=0; i<n; i++){
			pipe(pd);
			if(fork()==0){
				close(pd[0]);
				dup2(pd[1],1);
				execlp("filtro", "filtro", NULL);
				_exit(0);
			}
			if(fork()==0){
				close(pd[1]);
				dup2(pd[0],0);
				execlp("existe", "existe", NULL);
				_exit(i);
			}
			close(pd[0]);
		}
		for(i=0;i<n;i++){
			wait(&status);
			if(WIFEXITED(status)){
      			if(WEXITSTATUS(status) == 1){
					count++;
				}
			}
			else{
				kill(getpid(),SIGKILL);
			}
		}
	}
	return count;
}