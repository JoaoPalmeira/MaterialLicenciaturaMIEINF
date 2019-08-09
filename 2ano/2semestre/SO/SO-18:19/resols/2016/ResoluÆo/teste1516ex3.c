#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>

void create_captcha_file(const char* palavra){
  char nome[512], buf[16384];
  int n;

  int fd = open("servidor", O_WRONLY);
  sprintf(buf, "%s %d\n", palavra, getpid());
  write(fd, buf, strlen(buf));
  close(fd);

  sprintf(nome, "/tmp/resposta%d", getpid());
  mkfifo(nome, 0666);

  fd = open(nome, O_RDONLY);
  n = read(fd, buf, sizeof(buf));
  close(fd);

  sprintf(nome, "%s.png", palavra);
  fd = open(nome, O_CREAT | O_TRUNC, O_WRONLY, 0666);
  write(fd, buf, n);
  close(fd);
}

// processo servidor (totalmente independente do cliente; comunicação
// processos por pipes com nome: "servidor" para submissão de pedidos e
// "respostaXXXX" para recepção de resposta por parte de cada cliente

int main(){
  int fd, n;
  char buf[16384];
  char nome[512];
  char palavra[7];
  int pid;
  mkfifo("servidor", 0666);
  while(1){
    fd = open("servidor", O_RDONLY);
    n = readln(fd, buf, sizeof(buf));
    if(n == 0) break;
    buf[n] = '\0';
    sscanf(buf, "%s %d", palavra, &pid);
    if(!fork()){
      n = captcha(palavra, buf);
      sprintf(nome, "/tmp/resposta%d", pid);
      fd = open(nome, O_WRONLY);
      write(fd, buffer, n);
      close(fd);
      _exit(0);
    }
    close(fd);
  }
  return 0;
}
