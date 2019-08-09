#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h> 
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>


int checkSaveds;
char *operation;
char files[1024][1024];
void signalhandler (int signal) {
  if (signal == SIGRTMAX) {
    if ((strstr(operation,"backup")-operation)==0) {
      printf("%s: copiado\n",files[checkSaveds++]);
    }
    else if ((strstr(operation,"restore")-operation)==0) {
      printf("%s: restaurado\n",files[checkSaveds++]);
    }
  }
  else if (signal == SIGALRM) {
    if ((strstr(operation,"backup")-operation)==0) {
      printf("%s: Erro ao copiar\n",files[checkSaveds++]);
    }
    else if ((strstr(operation,"restore")-operation)==0) {
      printf("%s: Erro ao restaurar\n",files[checkSaveds++]);
    }
  }
}

ssize_t readln1(int fildes, char* buf, size_t nbyte){
  char *bp = buf;
  ssize_t n;
  while(bp-buf < nbyte && (n=read(fildes,bp,1))>0) {
    if(*bp++ == '\n') 
      return (bp-buf);
  }
  if(n<=0) return -1;
  if(bp-buf == nbyte) {
    char c;
    while(read(fildes,&c,1)>0 && c!='\n');
  }
  return (bp-buf);
}

int intlen (int x) {
  int i=1;
  while(x>9) {
    x = x/10;
    i++;
  }
  return i;
}

int main(int argc, char const *argv[]) {
  if ((argc>=2) && (strstr(argv[1],"backup")==0 || strstr(argv[1],"restore")==0 || strstr(argv[1],"delete")==0 || strstr(argv[1],"gc")==0)) {
  	int x=0,r,z,y;
  	checkSaveds=0;
    for (r=argc-1;r>1;r--) {
      strcpy(files[r-2],argv[r]);
    }
    operation = (char *) calloc (strlen(argv[1]),sizeof(char));
    strcpy(operation,argv[1]);


    char **buf = (char **) calloc (argc-2,sizeof(char *));
    for (r=argc-1;r>1;r--) {
      x = strlen(argv[r]);
      z = getpid();
      y = intlen(z);
      buf[r-2] = (char *) calloc (3+strlen(argv[1])+strlen(argv[r])+y,sizeof(char));
      strcat(buf[r-2],argv[1]);
      strcat(buf[r-2]," ");
      strcat(buf[r-2],argv[r]);
      strcat(buf[r-2]," ");
      char *pid = (char *) calloc (y+1,sizeof(char));
      sprintf(pid,"%d",z);
      strcat(buf[r-2],pid);
      buf[r-2][strlen(buf[r-2])]='\n';
    }
    int fdout = open("pipe",O_WRONLY,S_IRWXU);
    for (r=0;r<argc-2;r++) {
      write(fdout,buf[r],strlen(buf[r]));
    }



    signal(SIGRTMAX,signalhandler);
    signal(SIGALRM,signalhandler);
    while(1){
      if(checkSaveds==argc-2) kill(getpid(),SIGKILL);
    }
  }
  else (write(1,"Comando Invalido!\n",18));
  return 0;
}
