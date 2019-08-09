#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>


int main(int agrc, char ** argv){
        char buffer[100];
        int fd;
        fd=open("textoEx5.txt", O_RDONLY);

        readln(fd, buffer, 100);
        printf("Linha lida: %s", buffer);
        close(fd);
        return 0;
}

