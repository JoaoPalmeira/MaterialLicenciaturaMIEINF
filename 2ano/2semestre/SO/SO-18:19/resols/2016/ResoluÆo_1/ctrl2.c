#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>

int tempo = 0;

void incrementa () {
    tempo += 3;
    alarm(3);
}

int main (int argc, char* argv[]) {
    int numProcessos = argc-1;
    int pids[numProcessos];
    int fds[numProcessos][2];
    int numKills=0;

    for (int i = 0; i < numProcessos; i++) {
        pipe(fds[i]);
        pids[i] = fork();

        if (pids[i] == 0) {
            close(fds[i][0]);
            dup2(fds[i][1], 1);
            execlp(argv[i+1],argv[i+1], NULL);
            _exit(i);
        }

        close(fds[i][1]);
        dup2(fds[i][0], 0);
    }

    signal(SIGALRM, incrementa);
    alarm(3);

    while (1) {
        pause();

        for(int i=0; i<numProcessos; i++) {
            char ok[3];

            if(pids[i] != 0) {
                read(fds[i][0], ok, 3);
                if (strcmp(ok, "OK") != 0) {
                    kill(pids[i], SIGKILL);
                    pids[i] = 0;
                    numKills++;
                    close(fds[i][0]);
                    printf("Programa %s durou %d\n", argv[i+1], tempo);
                }
            }
        }

        if(numKills == numProcessos) {
            _exit(0);
        }

        _exit(-1);
    }
}
