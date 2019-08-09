#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, const char *argv[]){
	int i,status,ord=0;

	while(ord<10){
		pid_t f=fork();
		if(f==0){

			exit(ord);
		}
		else{
			int id=wait(&status);
			printf("PROCESSO %d, PPID, %d, FILHO %d  VAR F %d\n",getpid(),getppid(),id,f);
		}
		ord++;
	}



	return 0;
}
