#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	printf("Filho %d--- Pai %d\n",getpid(),getppid());
	return 0;
}