#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

void main(){
    char buf;
    int fd=open("pipe",O_WRONLY);
    while(read(0,&buf,1)>0)
        write(fd,&buf,1);
}