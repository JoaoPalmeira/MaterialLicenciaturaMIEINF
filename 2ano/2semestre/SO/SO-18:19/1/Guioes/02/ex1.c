#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	pid_t son = fork();

	if(son==0){
		printf("O pid do filho é %d\n",getpid());
	}
	else{
		printf("O pid do pai é %d\n",getppid());
	}

	return 0;
}
