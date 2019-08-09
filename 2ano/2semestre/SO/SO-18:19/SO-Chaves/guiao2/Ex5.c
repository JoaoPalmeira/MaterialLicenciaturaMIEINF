#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int i,p;
	for(i=0;i<10;i++)
	{
		if(fork()!=0)
		{
			printf("Comecei. Processo %d. Pai %d. Nº - %d.\n",getpid(), getppid(),i+1);
			sleep(0.5);
			if(i!=9)
				wait(NULL);
			printf("Acabei. Processo %d\n",i+1);
			_exit(i);
		}
	}
}