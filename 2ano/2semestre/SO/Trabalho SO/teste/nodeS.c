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

//-----------------------------ESTRUTURA----------------------------------

struct node{
  int id;
  int pid;
  char** args;
};

//-----------------------------FUNÇÕES------------------------------------

Node criaNode(char* argv[], int pid) {
  
  node new = (node) malloc(sizeof(struct node));
  new->id = atoi(argv[0]);
  new->pid = pid;
  strcpy(*node.args,*argv);

  return new;

  /*while(1){ 
    if (strcmp(argv[0],"const")==0) {
      execpv(argv[0],argv+1);
    }

      if (strcmp(argv[0],"filter")==0) {
        printf("Node:%d Função:%s\n", id,argv[0]);
        coluna=atoi(argv[1]);
        operacao=argv[2];
        operando=atoi(argv[3]);
        filtro(coluna,operacao,operando);
    }

      if (strcmp(argv[0],"window")==0){
        printf("Node:%d Função:%s\n", id,argv[0]);
        coluna=atoi(argv[1]);
        operacao=argv[2];
        operando=atoi(argv[3]);
        window(coluna,operacao,operando);
    }
    
      if (strcmp(argv[0],"spawn")==0){
        printf("Node:%d Função:%s\n", id,argv[0]);
        spawn(argv);
    }
  }*/
}

//------------------------------------------------------------------

/*void display()
{
struct node *new_node;
 printf("The Linked List : n");
 new_node=start;
 while(new_node!=NULL)
   {
   printf("%d--->",new_node->data);
   new_node=new_node->next;
   }
  printf("NULL");
}*/