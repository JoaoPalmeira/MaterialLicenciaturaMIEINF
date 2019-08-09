#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <signal.h>

int j = 0;
int flag = 0;

ssize_t readln(int fildes, char *buf, size_t nbyte) {
	int i = 0;
	
	while(i<nbyte-1 && 
	      read(fildes, buf+i, 1) > 0 &&
		  buf[i] != '\n') {		
			i++;
	}

	if(i>=nbyte)
		buf[i] = 0;
	else
		buf[i+1] = 0;

	return i;
		
}

void signalHandler(int signal){
	if(signal == SIGUSR1){
		flag = 1;
	}
}


int main(int argc, char *argv[])
{
	int fdTemp[1024];
    char* cmds[1024];
	int i=1,k=0;
	int pd[2];
	char *temp;
	int status;
	int n;
	char buffer[1024];
	char c;
	int auxFile;

	while(i<argc){
		cmds[k] = strdup(argv[i]);
		k++;
		i++;
	}

	cmds[i] = NULL;
	i=0;

	while(cmds[i]!=NULL){

		if(i!=0){

			pipe(pd);

			if(fork()==0){
				close(pd[0]);
				char *aux = (char*) malloc(sizeof(char*)*10);
				sprintf(aux,"%d.txt",j-1);

				auxFile = open(aux, O_RDONLY);

				dup2(pd[1],1);
				dup2(auxFile,0);
				execlp(cmds[i],cmds[i],NULL);
				kill(getpid(),SIGUSR1);
				exit(1);

			}

			wait(&status);
			close(pd[1]);
			signal(SIGUSR1,signalHandler);

			if(flag==0){
				sprintf(temp,"%d.txt",j);
				
				fdTemp[j] = open(temp ,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);
						
				if(fdTemp[j]!=-1){
						
					while(((n=readln(pd[0],buffer,1024)))>0){
						write(fdTemp[j], buffer, n);
						write(fdTemp[j],"\n",1);
					}	

					close(pd[0]);
					close(fdTemp[j]);
					j++;
				}
				else{
					perror("Erro ao abrir Ficheiro temporario");
					exit(-3);
				}
			}
			else{
				perror("Comando Impossivel de executar");
				_exit(-4);
			}	
		}
		else if(i==0){

			pipe(pd);

			if(fork()==0){

				close(pd[0]);
				dup2(pd[1],1);
				execlp(cmds[i],cmds[i],NULL);
				kill(getpid(),SIGUSR1);
				exit(2);
			}

			wait(&status);
			close(pd[1]);
			signal(SIGUSR1,signalHandler);

			if(flag == 0){

				sprintf(temp,"%d.txt",j);

				fdTemp[j] = open(temp,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);

				if(fdTemp[j] != -1){

					while((n=read(pd[0],buffer,1024))>0){
						write(fdTemp[j],buffer,n);
						write(fdTemp[j],"\n",1);
					}

					close(pd[0]);
					close(fdTemp[j]);
					j++;

				}

				else{
					perror("Erro ao abrir ficheiro...");
						exit(3);
					}
			}
			else{
				perror("Comando impossivel de executar...");
				exit(4);
			}
		}
		
		i++;
	}


	return 0;
}