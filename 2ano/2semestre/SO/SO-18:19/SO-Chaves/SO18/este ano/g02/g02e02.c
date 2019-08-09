#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
int main(int argc, const char *argv[]){
	int status;
	pid_t i=fork();
	if(i==0){
		printf("Processo Filho: %d\nMeu Pai: %d\n",getpid(),getppid());
	}
	else{
		wait(&status);
		printf("Processo Pai: %d\nBASH: %d\n",getpid(),getppid());
	}
	return 0;
}
