#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>


int count_ctrl_c = 0;
int tempo;

void handlerCtrl_C(){
    count_ctrl_c++;
    printf("\nPremiu Ctrl-C, passaram: %d segundos...\n",tempo);
}

void exitHandler(){
    printf("\nPremiu Ctrl-C: %d vezes e passaram %d segundos...\n",count_ctrl_c,tempo);
    exit(0);
}

void handlerAlarm(){
    tempo++;
    alarm(1); // Realarmar o alarme para ficar ativo novamente, senão da bosta !!!
}

void signalHandler(int signal){
    switch (signal){
        case SIGINT: handlerCtrl_C();
                     break;
        case SIGQUIT: exitHandler();
                      break;
        case SIGALRM: handlerAlarm();
                        break;
        default: printf("Sinal invalido...\n");
                 exit(-1);
    }
}

int main(int argc, char *argv[])
{
    // SIGINT -> Interrupção a partir do teclado (Ctrl+C)
    // SIGQUIT -> Terminação a partir do teclado (Ctrl+)

     //inicio do tempo
    tempo=0;

    signal(SIGALRM, signalHandler);
    signal(SIGINT, signalHandler);
    signal(SIGQUIT, signalHandler);

    alarm(1);

    printf("O meu pid é: %d...\n", getpid());

    while(1) {
        //alarm(1); //-> Nao pode estar aqui porque fica sempre a espera de um SIGALARM
        pause();
    }

    return 0;
}