#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
int main(int argc, char *argv[]){
	int i;
	for(i=1;i<argc;i++)
		if(fork()==0){
			printf("Processo %d -> %s\n",i,argv[i]);
			execlp(argv[i],argv[i],NULL);
			exit(-1);
		}

	for(i=1;i<argc;i++) wait(0);

	return 0;
}
