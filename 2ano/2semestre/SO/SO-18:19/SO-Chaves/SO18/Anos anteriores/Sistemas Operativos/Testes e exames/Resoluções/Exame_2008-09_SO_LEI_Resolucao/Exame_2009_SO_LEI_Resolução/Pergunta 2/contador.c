#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

#define NUMERO 1000000000

long long int contador;
int pd[2];


void handler_escreve(int signum){
	int percentagem  = (contador*100/NUMERO);
	write(pd[1],&percentagem,sizeof(int));
	exit(0);
}
int main (int argc , char**argv){

	int menor = 100;
	int nprocessos = atoi(argv[1]);
	int actual;
	int i;
	pipe(pd);

	signal(SIGUSR1,SIG_IGN);
	for(i=0;i<nprocessos;i++){
		if(!(fork())){
			signal(SIGUSR1,&handler_escreve);
			for(contador=0;contador<NUMERO;contador++);
			kill(getpgid(0),SIGUSR1);
			pause();
			exit(0);
		}
	}

	for(i=0;i<nprocessos;i++){
		read(pd[0],&actual,sizeof(actual));
		if(menor>actual) menor = actual;
	}
	printf("%d %%\n",menor);

}