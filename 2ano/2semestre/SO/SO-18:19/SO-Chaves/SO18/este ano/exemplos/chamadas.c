#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int custo,tempo;
int flag;
void handlerS1(int signum){
	//start counting
	printf("COMECOU A CHAMADA\n");
	flag=1;
}
void handlerS2(int signum){
	flag=0;
	printf("TERMINOU A CHAMADA.\nTempo estimado: %d\nCusto: %d\n",tempo,custo);
	exit(0);
}
void handlerAlm(int signum){
	printf("+Preco\n");
	if(tempo<30){
	 	custo+=5;
	}
	else{
		custo+=4;
	}
	tempo+=10;
}

int main(){
	custo=tempo=flag=0;
	signal(SIGUSR1,handlerS1);
	signal(SIGUSR2,handlerS2);
	signal(SIGALRM,handlerAlm);
	while(!flag) pause();
	while(flag){
		alarm(10);
		pause();
	}
	return 0;
}

