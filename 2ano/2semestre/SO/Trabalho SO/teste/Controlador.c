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
#include <sys/wait.h>
#include "Componentes.h"
#include "nodeS.h"

#define bufS 200
#define PIPE_BUF 20
#define NODES 100

#define MORTO 0
#define VIVO 1

#define READ 0
#define WRITE 1
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

struct node nodes[20];

///////////////////////////////Funções Auxiliares//////////////////////////////
char** separaA(char* args){
  char *pToken = strtok(args," ");
  int i=0;
  char **res = (char **)malloc(bufS*sizeof(char));
  while(pToken != NULL){
    res[i]= strdup(pToken);
    pToken = strtok(NULL, " ");
    i++;
  }
  return res;
}

int comandos(char** args){
	char aux[bufS];

	if(strcmp(args[0],"node")==0){

		strcmp(aux,"pipe: ");
		strcat(aux,args[1]);

		//Cria PIPE
		mkfifo(aux, 0666);

		//Cria processo
		int pid = fork();
		if(pid == 0){
			nodes[1]=criaNode(args,pid);
			_exit(0);
		}
	}
	return 0;
}


//////////////////////////////////Main Controlador//////////////////////////////

int main(int argc, char * argv[]){
  char buffer[bufS];
  ssize_t nBytes;
  int fildes;

  fildes = open("comandos.txt", O_RDONLY);


  for(int i=0; (nBytes = readln(fildes,buffer,bufS)) > 0; i++){
  	char aux[nBytes];
  	char **argumentos;

  	memcpy(aux,buffer,nBytes);
  	aux[nBytes]='\0';

  	argumentos = separaA(aux);

  	
  	comandos(argumentos);


	for(int i=0; i<3; i++){
	    int estado;
	    wait(&estado);
	    if (WIFEXITED(estado)){
	      printf("\tno %d estado=%d\n", i+1, estado);
	    }
	}
  }

  return 0;
}