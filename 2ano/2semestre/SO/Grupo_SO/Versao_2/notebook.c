#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h> 
#include <ctype.h> 
#include <time.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>

#define Max 1024

int flag = 0;
int j;

void handler(int sinal){
	if(sinal == SIGUSR1)
		flag = 1;
}


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

char* trim (char *s) {
   while(isspace(*s)) s++;
   char *back = s + strlen(s);
   while(isspace(*--back));
   *(back+1) = '\0';
   return s;
}

char* parserCmd(char* cmd){
	char *ptr=NULL;
	char *aux=NULL;
    aux = strtok(cmd,"$");
    ptr = strtok(aux,"|");
    return ptr;
}

char* parserCmdNum(char* cmd, char* num){
	char *ptr=NULL;
	char *aux=NULL;
	char *aux1=NULL;
    aux = strtok(cmd,"$");
    aux1 = strtok(aux,num);
    ptr = strtok(aux1,"|");
    return ptr;
}

int main(int argc, char** argv){
	int n, i=0,k=0, r=0, w=0;
	int fdin,fdout, auxFile;
	int fileTemp[Max];
	int status;
	int nF;
	int anterior;
	char buffer[Max];
    char *aux1, *aux, *ptr, *token, *num;
    char *cmds[Max];
    char *cmdAux[Max];
    char *c[Max];
    char *aux2 = (char*) malloc(64*sizeof(char*));
    char *remv_file = (char*) malloc(64*sizeof(char*));
    j=0;

	int pd[2];

	if(argc > 2){
		fprintf(stderr, "Nº de argumentos inválido%s\n", argv[0] );
		exit(-1);
	}

	else {

		fdin = open(argv[1],O_RDONLY);
		fdout = open("temp.txt",O_CREAT | O_TRUNC | O_WRONLY, S_IWUSR | S_IRUSR );

		if(fdin!=-1){

			while(((n=readln(fdin,buffer,Max)))>0){

				if (buffer[0] == '$' && buffer[1] == '|'){
					write(fdout,buffer,strlen(buffer));

				 	aux1 = strdup(buffer);
				 	aux = trim(parserCmd(buffer));

				 	token = strtok(aux," ");
				 	i=0;
				 	while(token != NULL){
      					cmds[i] = strdup(token);
      					i++;
						token = strtok(NULL," ");
   					}
   	
   					cmds[i] = NULL;

   					write(fdout,">>>\n",4);

   					pipe(pd);
				
					if(fork()==0){
						close(pd[0]);
						char* temp = (char*) malloc(sizeof(char)*10);
						sprintf(temp,"%d.txt",j-1);

						auxFile = open(temp, O_RDONLY);

						dup2(pd[1], 1);
						dup2(auxFile, 0);
						execvp(cmds[0],cmds);
						kill(getppid(),SIGUSR1);
						_exit(-2);
						
					}
			
					wait(&status);
					close(pd[1]);
					signal(SIGUSR1,handler);

					if(flag==0){
						sprintf(aux2,"%d.txt",j);
				
						fileTemp[j] = open(aux2 ,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);
						
						if(fileTemp[j]!=-1){
						
							while(((n=readln(pd[0],buffer,Max)))>0){
								write(fdout, buffer, n);
								write(fileTemp[j], buffer, n);
								write(fileTemp[j],"\n",1);
								write(fdout,"\n",1);
							}	

							close(pd[0]);
							close(fileTemp[j]);
							j++;
							write(fdout,"<<<\n",4);
						}
						else{
							perror("NAO ABRIU O FICHEIRO");
							_exit(-3);
						}
					}
					else{
						remove("temp.txt");
						perror("COMANDO IMPOSSIVEL DE EXECUTAR");
						_exit(-4);
					}	
					
				}
				else if (buffer[0] == '$' && buffer[2] == '|'){
					write(fdout,buffer,strlen(buffer));

					aux1 = strdup(buffer);
				 	num = parserCmd(buffer);
				 	aux = trim(parserCmdNum(aux1,num));

				 	token = strtok(aux," ");
				 	i=0;
				 	while(token != NULL){
      					cmds[i] = strdup(token);
      					i++;
						token = strtok(NULL," ");
   					}
   	
   					cmds[i] = NULL;

   					write(fdout,">>>\n",4);

   					nF = buffer[1] - '0';	
					anterior = j-nF;

   					pipe(pd);

   					if(fork()==0){
   						close(pd[0]);
   						char* temp = (char*) malloc(sizeof(char)*10);
						sprintf(temp,"%d.txt",anterior);

						auxFile = open(temp, O_RDONLY);

						dup2(pd[1], 1);
						dup2(auxFile, 0);
						execvp(cmds[0],cmds);
						kill(getppid(),SIGUSR1);
						_exit(-8);
   					}

   					wait(&status);
					close(pd[1]);
					signal(SIGUSR1,handler);

					if(flag==0){
						sprintf(aux2,"%d.txt",j);
				
						fileTemp[j] = open(aux2 ,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);
						
						if(fileTemp[j]!=-1){
						
							while(((n=readln(pd[0],buffer,Max)))>0){
								write(fdout, buffer, n);
								write(fileTemp[j], buffer, n);
								write(fileTemp[j],"\n",1);
								write(fdout,"\n",1);
							}	

							close(pd[0]);
							close(fileTemp[j]);
							j++;
							write(fdout,"<<<\n",4);
						}
						else{
							perror("NAO ABRIU O FICHEIRO");
							_exit(-3);
						}
					}
					else{
						remove("temp.txt");
						perror("COMANDO IMPOSSIVEL DE EXECUTAR");
						_exit(-4);
					}	
				}
				else if (buffer[0] == '$'){
					write(fdout,buffer,strlen(buffer));

					aux1 = strdup(buffer);
				 	aux = trim(parserCmd(buffer));
				 	ptr = strtok(aux1, " ");

				 	token = strtok(aux," ");
				 	i=0;
				 	while(token != NULL){
      					cmds[i] = strdup(token);
      					i++;
						token = strtok(NULL," ");
   					}

   					cmds[i] = NULL;

   					write(fdout,">>>\n",4);

   					pipe(pd);

   					if(fork()==0){
						close(pd[0]);
						dup2(pd[1], 1);
						execvp(cmds[0], cmds);
						kill(getppid(),SIGUSR1);
						_exit(-3);
					}

					wait(&status);
					close(pd[1]);
					signal(SIGUSR1,handler);

					if(flag==0){
						sprintf(aux2,"%d.txt",j);
				
						fileTemp[j] = open(aux2,O_CREAT | O_RDONLY |O_WRONLY,S_IRWXU | S_IRWXG | S_IRWXO);

						if(fileTemp[j]!=-1){

						while(((n=readln(pd[0],buffer,Max)))>0){
							write(fdout, buffer, n);
							write(fileTemp[j], buffer, n);
							write(fileTemp[j],"\n",1);
							write(fdout,"\n",1);
						}	

						close(pd[0]);
						close(auxFile);
						close(fileTemp[j]);
						j++;
						write(fdout,"<<<\n",4);
						
						}
						else{
							perror("NAO ABRIU O FICHEIRO");
							_exit(-5);
						}
					}
					else{
						remove("temp.txt");
						perror("COMANDO IMPOSSIVEL DE EXECUTAR");
						_exit(-6);
					}
				}

			}
			close(fdout);

			int f=0;
			while(f<j){
				sprintf(remv_file,"%d.txt",f);
				remove(remv_file);
				f++;
			}
			remove("exemplo.txt");
			rename("temp.txt","result.txt");
		}	
		else{
			perror("ERRO AO LER O FICHEIRO");
		} 
	}
	exit(-7);
	return 0;
}