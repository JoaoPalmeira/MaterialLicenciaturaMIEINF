#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc,char *argv[]){
	if(argc<2){
		perror("Falta de argumentos");
		exit(-1);
	}
	int i;
	for(i=1;i<argc;i++){
		if(!fork()){
			execlp(argv[i],argv[i],NULL);
		}
	}

	wait(NULL);
	return 0;
}
