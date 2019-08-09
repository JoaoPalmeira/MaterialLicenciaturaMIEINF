#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(){
	int i,status;
	int pids[10];
	for(i=0;i<10;i++){
		if(!(pids[i]=fork())){
			printf("Sou o filho %d, com o ID %d e PPID %d\n",i+1,getpid(),getppid());
			exit(1);
		}
		else{
			wait(NULL);
		}
	}

	return 0;
}

