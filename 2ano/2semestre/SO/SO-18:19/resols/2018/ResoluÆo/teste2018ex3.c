#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>

int readln(int fd, char* buf, int size){
  int n, i = 0;
  while((n = read(fd, buf+i, size))){
    if(buf[i] == '\n')
      return i+1;
    i++;
  }
  return i;
}

int main(int argc, char const *argv[]) {
  int p = atoi(argv[1]);
  int c = atoi(argv[2]);
  char buffer[512];
  char valor[2];
  int n, d;
  int fc[c][2];
  int fp[2];
  pipe(fp);
  for(int i = 0; i < p; i++){ //criar produtores
    if(!fork()){
      dup2(fp[1], 1);
      close(fp[0]);
      close(fp[1]);
      execlp("produtor", "produtor", NULL);
      _exit(1);
    }
  }

  for(int i = 0; i<c; i++){ //criar consumidores
    pipe(fc[i]);
    if(!fork()){
      dup2(fc[i][0], 0);
      close(fc[i][0]);
      close(fc[i][1]);
      execlp("consumidor", "consumidor", NULL);
      _exit(1);
    }
  }

  while((n = readln(fp[0], buffer, sizeof(buffer))) > 0){
    valor[0] = buffer[0]; valor[1] = '\0';
    d = atoi(&valor[0]);
    if(d > 0 && d < c){
      write(fc[d][1], buffer+1, n-1);
    }
  }
  return 0;
}
