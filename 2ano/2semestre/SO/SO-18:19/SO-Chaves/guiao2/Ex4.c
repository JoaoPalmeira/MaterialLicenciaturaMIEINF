#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    int i,status;
    for(i=0;i<10;i++)
    {
      if(fork()==0)
      {
        printf("Comecei. Processo %d. Pai %d. Nº - %d.\n",getpid(), getppid(),i);
        _exit(i);
      }
    }
    for(i=0;i<10;i++)
    {
      int idfilho=wait(&status);
      printf("Morreu o filho %d com nº %d\n", WEXITSTATUS(status)+1,idfilho);
    }
}