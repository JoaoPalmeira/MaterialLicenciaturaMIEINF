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
#include "Componentes.c"
#include "nodeS.c"

#define bufS 512
#define PIPE_BUF 20

#define READ 0
#define WRITE 1
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

void node(int argc, char* argv[], char buffer[], int fildes){
	criaN(1);
}






int main(int argc, char * argv[]){
  char buffer[bufS];
  int fildes;

  fildes = open("file.txt", O_RDONLY);

  if (strcmp(argv[1],"node")==0){
  	node(argc,argv,buffer,fildes);
  }
    

  close(fildes);
  return 0;
}