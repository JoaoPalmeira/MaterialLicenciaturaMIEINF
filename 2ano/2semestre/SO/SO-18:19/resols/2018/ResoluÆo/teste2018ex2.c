#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <signal.h>


int pid[1024];
int pidc = 0;
void sig_h(int sig){
  for(int i = 0; i < pidc; i++){
    kill(pid[i], SIGKILL);
    _exit(1);
  }
}

int main(int argc, char* argv[]){
  int contador = 0;
  int fim = 0;
  int p;
  int status;
  signal(SIGALRM, sig_h);
  pidc = argc-1;

  while(!fim){
    alarm(contador);
    for(int i = 0; i<pidc; i++){
      if((pid[i] = fork()) == 0){
        execlp(argv[i+1], argv[i+1], NULL);
        _exit(1);
      }
    }
    for(int i = 0; i < pidc; i++){
      wait(&status);
      if(WIFEXITED(status)){
        if(WEXITSTATUS(status) == 0)
          fim = 0; //assim espero que todos os processos terminem. Para não esperar: {fim = 0; for(int i = 0; i<pidc; i++) kill(pid[i], SIGKILL);}
        else
          contador += WEXITSTATUS(status);
      }
    }
  }
  return 0;
}
