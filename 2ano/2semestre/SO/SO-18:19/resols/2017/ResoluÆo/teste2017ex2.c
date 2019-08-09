#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

int readln(int fd, char *buffer, int size){
  int n;
  int i = 0;
  while((n = read(fd, buffer +i, 1)) && i < size){
    if(buffer[i] == '\n')
      return i+1;
    i++;
  }
  return i;
}

int main(int argc, char * argvs[]){
  int control[argc-1];
  int contador = 0;
  int linhas, n, i;
  char buffer2[1024];
  int fd[argc-1][2];
  for(i = 0; i < argc-1; i++){
    pipe(fd[i]);
    if(!fork()){
      dup2(fd[i][1], 1);
      close(fd[i][1]);
      close(fd[i][0]);
      execlp(argvs[i+1], argvs[i+1], NULL);
    }
    else{
      close(fd[i][1]);
      control[i] = 0;
    }
  }
  for(i = 0; i < argc-1; i++)
    wait(0L);

  i = 0;
  while(contador < argc-1){
    linhas = 0;
    while(linhas < 10 && control[i] != 1){
      n = readln(fd[i][0], buffer2, 1024);
      if(n == 0){
        control[i] = 1;
      }
      write(1, buffer2, n);
      linhas++;
    }
    if (control[i] == 1) contador++;
    else contador = 0;
    i = (i+1) % (argc-1);
  }
  return 0;
}
