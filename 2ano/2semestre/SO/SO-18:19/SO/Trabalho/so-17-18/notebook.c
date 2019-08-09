
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>
#include <ctype.h>


#define MAX_CHAR_LINE 1024
#define MAX_ARGS 100

int controlo = 0;
int j;
int ant;


void handler(int sig){
	if(sig == SIGUSR1)
		controlo = 1;
}

char *lerLinha(int fd){
	char *buffer = (char*) malloc(MAX_CHAR_LINE);
	int res, i = -1;

	while( (res=read(fd, buffer + ++i, 1)) > 0 && buffer[i] != '\n' && i < MAX_CHAR_LINE - 1);

	if (res == 0 && i <= 0) return NULL;

	buffer[i] = '\0';

	return buffer;
}

int main(){
	
	char *linha, *args[MAX_ARGS], *token;
	int status, i, fd, num;
	int file = 0, fd_result;
	char* name = (char*) malloc(sizeof(char)*10);
	int fd_tmp[MAX_CHAR_LINE];
	j = 0;

	int p[2];

	fd = open("teste.nb", O_RDONLY);
	fd_result = open("result.txt", O_WRONLY|O_RDONLY|O_CREAT|O_TRUNC,S_IRWXU | S_IRWXG | S_IRWXO);

	if(fd!=-1){
		while((linha = lerLinha(fd))!=NULL){
			
			if (linha[0] == '$' && linha[1] == '|'){	
				
				write(fd_result,linha,strlen(linha));
				write(fd_result,"\n",1);

				for(token = strtok(linha+2, " "), i = 0; token; token = strtok(NULL, " "), i++)
					args[i] = token;
					
				args[i] = NULL;
				write(fd_result,">>>\n",4);

				pipe(p);
				
				if(fork()==0){
					close(p[0]);
					char* name_ant = (char*) malloc(sizeof(char)*10);
					sprintf(name_ant,"%d.txt",j-1);

					file = open(name_ant, O_RDONLY);

					dup2(p[1], 1);
					dup2(file, 0);
					execvp(args[0], args);
					kill(getppid(),SIGUSR1);
					_exit(0);
				}
			
				wait(&status);
				close(p[1]);

				signal(SIGUSR1,handler);
				if(controlo == 0){
					sprintf(name,"%d.txt",j);
				
					fd_tmp[j] = open(name,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);
					if(fd_tmp[j]!=-1){
						
						while((linha = lerLinha(p[0]))){
							write(fd_result, linha, strlen(linha));
							write(fd_tmp[j], linha, strlen(linha));
							write(fd_tmp[j],"\n",1);
							write(fd_result,"\n",1);
						}	

						close(p[0]);
						close(fd_tmp[j]);
						j++;
						write(fd_result,"<<<\n",4);
					}else{
						perror("NAO ABRIU O FICHEIRO");
						_exit(1);
					}
				}else{
					remove("result");
					perror("COMANDO IMPOSSIVEL DE EXECUTAR");
					_exit(1);
				}	
			}else if(linha[0] == '$' && linha[2] == '|'){

				write(fd_result,linha,strlen(linha));
				write(fd_result,"\n",1);

				for(token = strtok(linha+3," "), i = 0; token; token = strtok(NULL, " "), i++){
					args[i] = token;
				}
					
				args[i] = NULL;
				write(fd_result,">>>\n",4);

				num = linha[1] - '0';
				ant = j-num;
				
				pipe(p);
				
				if(fork()==0){
					close(p[0]);
					char* name_ant = (char*) malloc(sizeof(char)*10);
					sprintf(name_ant,"%d.txt",ant);

					file = open(name_ant, O_RDONLY);

					dup2(p[1], 1);
					dup2(file, 0);
					execvp(args[0], args);
					kill(getppid(),SIGUSR1);
					_exit(0);
				}
			
				wait(&status);
				close(p[1]);

				signal(SIGUSR1,handler);
				if(controlo == 0){
					sprintf(name,"%d.txt",j);
				
					fd_tmp[j] = open(name,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);
					if(fd_tmp[j]!=-1){
						
						while((linha = lerLinha(p[0]))){
							write(fd_result, linha, strlen(linha));
							write(fd_tmp[j], linha, strlen(linha));
							write(fd_tmp[j],"\n",1);
							write(fd_result,"\n",1);
						}	

						close(p[0]);
						close(fd_tmp[j]);
						j++;
						write(fd_result,"<<<\n",4);
					}else{
						perror("NAO ABRIU O FICHEIRO");
						_exit(1);
					}
				}else{
					remove("result");
					perror("COMANDO IMPOSSIVEL DE EXECUTAR");
					_exit(1);
				}	
			}else if(linha[0] == '$'){

				write(fd_result,linha,strlen(linha));
				write(fd_result,"\n",1);

				for(token = strtok(linha+1, " "), i = 0; token; token = strtok(NULL, " "), i++){
					args[i] = token;
				}
					
				args[i] = NULL;
				write(fd_result,">>>\n",4);

		  		pipe(p);


				if(fork()==0){
					close(p[0]);
					dup2(p[1], 1);
					execvp(args[0], args);
					kill(getppid(),SIGUSR1);
					_exit(0);
				}

				wait(&status);
				close(p[1]);
				signal(SIGUSR1,handler);

				if(controlo == 0){
					sprintf(name,"%d.txt",j);
				
					fd_tmp[j] = open(name,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);

					if(fd_tmp[j]!=-1){

						while((linha = lerLinha(p[0]))){
							write(fd_result, linha, strlen(linha));
							write(fd_tmp[j], linha, strlen(linha));
							write(fd_tmp[j],"\n",1);
							write(fd_result,"\n",1);
						}	

						close(p[0]);
						close(file);
						close(fd_tmp[j]);
						j++;
						write(fd_result,"<<<\n",4);
					}else{
						perror("NAO ABRIU O FICHEIRO");
						_exit(1);
					}
				}else{
					remove("result");
					perror("COMANDO IMPOSSIVEL DE EXECUTAR");
					_exit(1);
				}
			}
		}
		close(fd_result);
		remove("teste");
		rename("result.txt","teste");
	}else{
		perror("ERRO AO LER O FICHEIRO");
	} 
	return 0;
}

	
	
