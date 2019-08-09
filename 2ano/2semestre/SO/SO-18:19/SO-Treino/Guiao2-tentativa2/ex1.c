#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{	
	pid_t pid = fork();
	if(pid==0){
		printf("Este é o pid do filho: %d\n", getpid());
	}
	else{
		printf("Este é o pid do pai: %d\n", getppid());
	}
	return 0;
}