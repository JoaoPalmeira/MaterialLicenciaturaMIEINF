#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{	
	int p = fork();
	if(p==0){
		printf("Este mano é o filheco: %d\n", getpid());
	}
	else{
		printf("Este mano é o paizeco: %d e o filheco é:%d\n", getpid(), p);
		printf("Processo que invocou o main: %d\n", getppid());
	}
}