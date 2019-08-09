#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>

#define LER 0
#define ESCREVER 1

int *pid;
int **fds;


int readln (int fd, char* buffer, int size){
    int r, i = 0;

    while((r = read(fd, buffer + i, 1)) > 0 && i < size){
        if(buffer[i] == '\n')
            return i+1;

        i++;
    }
    return i;
}

void paginas (int nProc , char **nameProc){
    int i, count = 0, lines, aux;
    int fds[nProc][2], control[nProc];
    char buffer[1024];

    for (i = 0 ; i < nProc ; i++){
        pipe (fds[i]);
        if (!fork()){
            dup2 (fds[i][ESCREVER], 1);
            close (fds[i][LER]);
            close (fds[i][ESCREVER]);

            execlp(nameProc[i], nameProc[i], NULL);
            _exit(-1);
        }
        else{
            close(fds[i][ESCREVER]);
            control[i] = 0;
        }
    }
    for (i = 0 ; i < nProc ; i++)
        wait(0L);

    i = 0;
    while (count < nProc){
        lines = 0;
        while (lines < 10 && control[i] != 1){
            aux = readln(fds[i][LER],buffer,1024);
            if (aux == 0){
                control[i] = 1;
            }
            write(1,buffer,aux);
            lines++;
        }
        if (control[i] == 1)
            count++;
        else
            count = 0;

        i = (i+1) % nProc;
    }
}


int main (int argc, char **argv)
{
    //char *buffer = malloc (sizeof(char) * 256);
    //printf("Size: %d\n", readln(0, buffer, 256));
    //printf("Sentence: %s", buffer);

    paginas(argc-1, argv+1);
}
