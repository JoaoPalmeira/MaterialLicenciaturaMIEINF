#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int i,status;
	for(i=0;i<10;i++)
	{
		if (fork()==0)
		{
			printf("SOU O FILHO %d!\n", i+1);
			printf("O meu id é: %d\n", getpid());
			printf("O meu pai é: %d\n", getppid());
			_exit(i);
		}
		else
		{
			int idFilho = wait(&status);
			printf("SOU O PAI!\n");
			printf("O meu id é: %d\n", getpid());
			printf("O meu filho nº%d, com id %d, morreu!\n\n", WEXITSTATUS(status)+1, idFilho);
		}
	}
}