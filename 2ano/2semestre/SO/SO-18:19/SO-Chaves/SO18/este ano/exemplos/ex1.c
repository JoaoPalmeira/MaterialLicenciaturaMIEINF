#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
	int nproc=argc-1,i;
	int *pids=(int*)malloc(nproc*sizeof(int));

	for(i=0;i<nproc;i++){
		if(!(pids[i]=fork())){
			printf("sou o filho %d, meu pai e %d, pids[%d]=%d\n",getpid(),getppid(),i,pids[i]);
		}
	}
	wait(NULL);

	return 0;
}
