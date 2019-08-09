#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h> 
#include <signal.h> // sigaction(), sigsuspend(), sig*()
#include <unistd.h> // alarm()

/*Using SIGCONT, SIGSTOP, and SIGCHLD, write a program that accepts through argv[] a list of
commands (none of them has arguments) and runs the commands in a round-robin style, alternating
commands n 1-second intervals.*/

//SIGKILL mata sempre.
//signal manda int, se o inteiro for x faz y, ou z faz w.

int status;
int total = argc,i;
int rrobin[total-1];
int activos = total-1; 

void ha() { kill(rrobin[i],SIGSTOP); }
void filhoterminou() {
	int i,j;
	j = wait(NULL);
	for(i=0;i<total;i++) {
		if(rrobin[i] == j) rrobin[i] = 0;
	}
	activos--;
}

int main(int argc, char const *argv[]){

	signal(SIGCHLD, filhoterminou);
	signal(SIGALRM, ha);

	//ciclo para correr todos os comandos
	for(i=1;i<total;i++) {
		//fazer fork para cada comando e guardar pid
		rrobin[i] = fork();
		//printf("estou aqui");
		if (rrobin[i] == 0) { execlp("sh","sh","-c",argv[i],NULL) ; }
		kill(rrobin[i],SIGSTOP);
		//waitpid(rrobin[i],&status,0); 
		//if(WIFEXITED(status)) rrobin[i] = -1;
				//printf("Terminou o filho com pid %i com o status: %i\n",proc[i],WEXITSTATUS(status)); 
	
	}

	//vai gastar 1s mesmo nos que terminou.

	
	while(activos > 0) { //fazer round robin, sleep 1s, mudar de processo e recomeçar
		for(i=1;i<total;i++) {
			if(rrobin[i] != 0) {
			kill(rrobin[i],SIGCONT); //mete o processo a correr
			//sleep(1); //correr durante 1 segundo
			alarm(1);
			pause();
			//kill(rrobin[i],SIGSTOP); //mete bloqueado
			}
		}
	}

}