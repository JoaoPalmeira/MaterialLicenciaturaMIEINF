#include<sys/signal.h>
#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>



void hand(int s) {} // o s diz que sinal foi enviado 


int main(){

	printf("%d\n",getpid());
	signal(SIGUSR1,hand); // quando ele receber o sinal SIGUSR1 vai executar hand, ou seja não faz nada
	pause(); // fica á espera de um sinal, quando o receber continua 
	while(1){ printf("bip\n"); sleep(5);}



}
