#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

#define NUMERO 1000000000




void encerracontadores(int signum){
	kill(-getpid(),SIGUSR1);
}


int main (int argc , char **argv){
	int menor = 100;
	int n = atoi(argv[1]);
	int i;
	int per;
	int *array = malloc (n*sizeof(int));
	signal (SIGCHLD,encerracontadores);
	signal (SIGUSR1,SIG_IGN);
	for(i=0 ; i<n ; i++){
		if(!fork()){
			execlp("contador","contador",NULL);
			exit(0);
		}
	}
	for (i=0 ; i<n ;i++){
		wait(&per);
		array[i] = WEXITSTATUS(per);
	}

	for(i=0;i<n;i++){
		if(array[i]<menor) 
			menor = array[i]; 
	}
	printf("MENOR : %d \n",menor);


	return 1;

}