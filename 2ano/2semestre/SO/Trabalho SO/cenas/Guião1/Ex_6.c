#include <unistd.h>   
#include <fcntl.h>
#include <stdio.h>

#define MAX_SIZE 1024

ssize_t readln (int fildes, char *buf, size_t nbyte) {
	int c,i=0,r;
	//while (i<nbyte && (c=read(fildes,&buf[i],1))>0 && buf[i]!='\n') i++; 
	while (i<nbyte && (c=read(fildes,buf+i,1))>0 && buf[i]!='\n') i++;
	((char *) buf)[i]='\0'; // "fecha" o que vai ser imprimido

	if (c>0) r=i;
	else r=c;
	return r;
}

int main(int argc, char const *argv[])
{
	char buffer[MAX_SIZE];
    int fd,j;

    fd = open("texto.txt", O_RDONLY);

    for(j=1;(read(fd,buffer+j,1))!=0;j++){
    	readln(fd, buffer, MAX_SIZE);
       	printf("%d %s\n", j, buffer);
	}

	return 0;
}