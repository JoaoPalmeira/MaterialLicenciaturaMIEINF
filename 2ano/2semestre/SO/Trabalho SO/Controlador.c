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
//#define PIPE_BUF 20
#define NODES 100

#define MORTO 0
#define VIVO 1

#define READ 0
#define WRITE 1
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

///////////////////////////////Estrutura///////////////////////////////////////

#define PIPE_BUF 50   /* Maximum length of event */
#define N_EVENTS 200  /* Maximum number of nodes in the struct */

/* Struct to save data regarding the nodes */
typedef struct node {
  int id;         /* id/number of the node */
  char *cmd;      /* the name of the command to execute in this node */
  char *args[20]; /* the arguments of the command, with 10 being the max number of arguments */
  int nargs;      /* number of arguments in args */
  pid_t pid;      /* pid of the process */
  int conR[20];
  int nconR;
  int conW[20];
  int nconW;
} *NodeList;

/* declaration of the global structure for nodes */
NodeList nodes[N_EVENTS];


/////////////////////////////Funções Estrutura/////////////////////////////////
/* This function creates a new list of nodes */
void initNodeList(){
  for(int i=0; i<N_EVENTS; i++)
    nodes[i] = NULL;
}

/* Create a new node with id, cmd, args, n and pid and append it to our array of nodes*/
void newNodeList(int id, char *cmd, char **args, int n, pid_t pid){
  int j,i;
  for(j=0; nodes[j] != NULL; j++){}
  NodeList aux = (NodeList) malloc (sizeof(struct node));
  aux->id = id;
  aux->cmd = strdup(cmd);
  for(i=0; i<n; i++)
    aux->args[i] = strdup(args[i]);
  aux->nargs = n;
  aux->pid = pid;
  for(i=0; i<20; i++) {
    aux->conR[i] = 0;
    aux->conW[i] = 0;
  }
  aux->nconR = 0;
  aux->nconW = 0;
  nodes[j] = aux;
  int length = snprintf( NULL, 0, "%d", id );
  char *strtmp = malloc (length + 1);
  snprintf (strtmp, length + 1, "%d", id);
  mkfifo(strtmp, 0666);
}


void node(int argc, char **argv) {
  char *arg[argc], *cmd, *buf=NULL;
  int r, id, i, f;
  if (argc<2)
    printf("Error! Not enough arguments\n"); /* argc must be >= 2 */
  else {
    id = atoi(argv[0]);
    cmd = (char *) malloc (sizeof(char)*32);
    cmd = strdup(argv[1]);
    for(i=0;i<argc-2;i++)
      arg[i] = strdup(argv[i+2]);
    pid_t p = getpid();
    newNodeList(id,cmd,arg,i,p);
    int length = snprintf( NULL, 0, "%d", id );
    char *strtmp = malloc (length + 1);
    snprintf (strtmp, length + 1, "%d", id);
    f = open(strtmp, O_RDONLY);
    int fd[2];
    while ((r=(readln(f, buf, 128)))) {
      pipe(fd);
      if(!fork()){
        dup2(fd[0],0);
        dup2(fd[1],1);
        close(fd[0]);
        close(fd[1]);
        const(arg[1]);
      }
      else {
        dup2(fd[1],1);
        write(1, buf, r);
        close(fd[1]);
        dup2(fd[0],0);
        close(fd[0]);
      }
      readln(fd[0], buf, 128);
      printf("%s\n", buf);
      //printf("DENTRO DO PIPE\n");
      //writeNode(1,buf,r);
    }
  }
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
      node(atoi(args[1]), args);
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