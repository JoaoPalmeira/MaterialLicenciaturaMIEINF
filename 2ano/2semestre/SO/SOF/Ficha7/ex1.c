#include <signal.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int segundos = 0;
int contador = 0;
void tempo() {
	segundos = segundos+1;
		alarm(1);
}

void imprimeT(){
	contador ++;
	printf("Já decorreram %d segundos.\n",segundos);
}

void imprimeC(){
	printf("Ctrl C pressionado %d vezes.\n", contador);
	_exit(0);
}
int main() {
	signal(SIGALRM, tempo);
	signal(SIGINT,imprimeT);
	signal(SIGQUIT,imprimeC);
	alarm(1);

	while(1){
		pause();
	}

	
}