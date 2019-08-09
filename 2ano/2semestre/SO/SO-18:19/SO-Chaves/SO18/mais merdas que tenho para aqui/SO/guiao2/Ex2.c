#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int x=fork();
	if(x==0)
		printf("Eu sou o pai %d, e este é o meu filho %d\n",getppid(),getpid());	
	printf("Filho %d--- Pai %d\n",getpid(),getppid());
	return 0;
}