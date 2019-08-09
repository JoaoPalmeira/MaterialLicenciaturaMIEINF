#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h> 
#include <signal.h> // sigaction(), sigsuspend(), sig*()
#include <unistd.h> // alarm()
#include <time.h>

int tempo = 0;
int ctrl = 0;

void ha() {
	tempo++;
	alarm(1); //FAZER SEMPRE REARME DO ALARME DENTRO DO ALARME!!!! - problema de rearmar alarme.
	}

void puff() {
		ctrl++;
		printf("Passaram-se %i segundos e premiu-se %i o ctrl+c\n",tempo,ctrl);

	}

void sair() { printf(" -------------- SAIDA!! %i segundos e premiu-se %i o ctrl+c\n",tempo,ctrl); exit(0); }

int main(int argc, char const *argv[]){
	
	signal(SIGALRM, ha);
	signal(SIGINT, puff);
	signal(SIGQUIT, sair);

	alarm(1);
	while(1) {
		//alarm(1); //so se pode fazer se tiver recebido o sinal de alarme antes, caso contrário adia sempre 1s e nunca chega a ele.
		pause();
	}


}