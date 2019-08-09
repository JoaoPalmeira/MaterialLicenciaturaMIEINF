#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int verifica(int *mat, int valor)
{
  int c=0,j;
  for(j=0;j<100;j++)
    if(mat[j] == valor) 
    {
      c=1;
      break;
    }
  return c;
}

int main()
{
  int mat[10][100],i,j,fpid;
  int valor= 8,status;
  for(i=0;i<10;i++)
    for(j=0;j<100;j++)
      mat[i][j]= rand()%11;
  for(i=0;i<10;i++)
  {
    fpid = fork();
    if(fpid == 0)
    {
      int retorna = verifica(mat[i], valor);
      _exit(retorna);
    }
  }
  for(i=0;i!=10;i++)
  {
    printf("PAI \n");
    int filhovalor=wait(&status);
    printf("O meu filho %d encontrou com %d\n", filhovalor, WEXITSTATUS(status));
  }
  return 0;
}