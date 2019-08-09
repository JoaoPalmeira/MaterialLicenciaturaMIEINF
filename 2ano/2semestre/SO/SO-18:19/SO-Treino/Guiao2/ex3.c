#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	int pid, i=0;
	int status;
	while(i<10){
		pid = fork();
		if(pid==0){
			printf("Este é o filho: %d\n", i+1);
			_exit(i);
		}
		i++;
	}
	//while(wait(&status)>0);
	return 0;
}