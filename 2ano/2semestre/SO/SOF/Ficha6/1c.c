#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#define MAXBUFF 32

void main(){
    char buf[MAXBUFF];
    int n;
    int fd=open("pipe",O_RDONLY);
    while((n=read(fd,buf,MAXBUFF))>0)
        write(1,buf,n);
}