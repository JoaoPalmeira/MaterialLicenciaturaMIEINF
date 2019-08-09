#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h> 
#include <signal.h> // sigaction(), sigsuspend(), sig*()
#include <unistd.h> // alarm()
#include <time.h>

/*
typedef void (*sighandler_t)(int);
sighandler_t signal(int signum, sighandler_t handler);
int kill(pid_t pid, int sig);
unsigned int alarm(unsigned int seconds);
int pause(void);

Using SIGINT, SIGQUIT, and SIGALRM, write a program that counts the time in seconds since it started
and prints the time elapsed whenever the user presses Ctrl-C. If the user presses Ctrl- your program
should indicate how many times the user has pressed Ctr-C and exit. */

//SIGINT - ctrl+c
//SIGALRM - alarme
//SIGQUIT - ctrl+\

//sempre que enunciado tiver tempo, garantir que antes de 5s do x, SIGALRM.
//pause() bloqueia processo até receber um sinal qualquer.
// kill(pid, sinal).

//multiplos alarmes só conta o último.




int main(int argc, char const *argv[]){

	int n=0;
	time_t start_t, end_t;
	double diff_t;

   //inicio timer
   time(&start_t);

	void maisum() {
		time(&end_t);
   		diff_t = difftime(end_t, start_t);
		n++; 
		printf("Premiu CTRL+C %d vezes! Passaram %f segundos",n,diff_t);
	}

	void alarme() {
		printf("PASSARAM 4 SEGUNDOS\n");
	}

	void handle_signal(int signal) {
		switch (signal) {
        	case SIGALRM:
         	  alarme();
          	  break;
      	  case SIGINT:
				maisum();
				break;
		  case SIGQUIT:
		  		maisum();
		  		exit(0);
		  		break;
      	  default:
            fprintf(stderr, "Caught wrong signal: %d\n", signal);
            return;
   		}
	//if(signal == SIGINT) maisum();
	}

	signal(SIGINT, handle_signal);
	signal(SIGALRM, handle_signal);
	signal(SIGQUIT, handle_signal);

	alarm(2);
	pause();

	printf("My pid is: %d\n", getpid());

	while(1) {	}

	return 0;

}