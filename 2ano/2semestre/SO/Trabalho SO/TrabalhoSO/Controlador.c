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

#define bufS 200
#define PIPE_BUF 20
#define NODES 100

#define MORTO 0
#define VIVO 1

#define READ 0
#define WRITE 1
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

///////////////////////////////Estrutura///////////////////////////////////////

typedef struct node{
  int id;
  pid_t pid;
  char *cmd;
  char *args[100];
}*NodeList;

NodeList nodes[bufS];
/////////////////////////////Funções Estrutura/////////////////////////////////
void initNodeList(){
  for(int i=0; i<bufS; i++)
    nodes[i] = NULL;
}

void node(int id, char *cmd, char **args, pid_t pid){
  int j,i,h=0;
  for(j=0; nodes[j] != NULL; j++){}
  NodeList aux = (NodeList) malloc (sizeof(struct node));
  aux->id = id;
  aux->cmd = strdup(cmd);

  for(i=3; i<strlen(*args); i++){
    aux->args[h] = strdup(args[i]);
    h++;
  }
  aux->pid = pid;
  nodes[j]=aux;
}


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

void executaNode(int id, FILE* f){
  printf("5.estou aqui\n");
  printf("------------------4\n");
  if(strcmp(nodes[id]->cmd,"const")==0){
    printf("4.estou aqui\n");
    consts(nodes[id]->args[0],f);
  }
}


void connect(char** argv){
  char buffer[bufS];
  FILE *fildes;

  fildes = fopen("file.txt", "r");
  printf("------------------3\n");
  executaNode(atoi(argv[1]),fildes);
    printf("1.estou aqui\n");

  for(int i = 2; i<strlen(*argv); i++){
    FILE *out;
    printf("3.estou aqui\n");
    out = fopen("output.txt", "r");
    executaNode(atoi(argv[i]),out);
  }
  
}

int comandos(char** args){
  char aux[bufS];

  if(strcmp(args[0],"connect")==0){
    printf("2.estou aqui\n");

    connect(args);

  } else if(strcmp(args[0],"node")==0){

    strcmp(aux,"pipe: ");
    strcat(aux,args[1]);

    //Cria PIPE
    mkfifo(aux, 0666);

    //Cria processo
    int pid = fork();
    if(pid == 0){
      char *cmd = strdup(args[2]);
      node(atoi(args[1]), cmd, args,pid);
      _exit(0);
    }
    else{
      for(int i=0; i<3; i++){
          int estado;
          wait(&estado);
          if (WIFEXITED(estado)){
              printf("\tno %d estado=%d\n", i+1, estado);
          }
      }
    }
  }
  return 0;
}


//////////////////////////////////Main Controlador//////////////////////////////

int main(int argc, char * argv[]){
  char buffer[bufS];
  ssize_t nBytes;
  int fildes;

  initNodeList();

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