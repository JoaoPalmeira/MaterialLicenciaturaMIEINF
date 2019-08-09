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

int main(int argc, char *argv[]) {
  char buf[512], bufsorted[1024];
  int n;
  int fd1;
  mkfifo("ordenar", 0666);
  while(1){
    int fd = open("ordenar", O_RDONLY);
    if((n = readln(fd, buf, sizeof(buf)))<=0)
      break;
    if(!fork()){
      buf[n-1] = '\0';
      sprintf(bufsorted, %s.sorted, buf);
      fd = open(buf, O_RDONLY, 0666);
      dup2(fd, 0);
      close(fd);
      fd1 = open(bufsorted, O_CREAT | O_WRONLY, 0666);
      dup2(fd1, 1);
      close(fd1);
      execlp("sort", "sort", NULL);
      _exit(1);
    }
    close(fd);
    close(fd1);
  }
  return 0;
}
