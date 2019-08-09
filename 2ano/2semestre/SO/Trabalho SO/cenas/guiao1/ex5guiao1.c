#include <unistd.h>   
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100

ssize_t readln(int fildes, char *buf, size_t nbyte){
	int i,n,r;
	i=0;
	
	while(i<nbyte && (n=read(fildes,buf+i,1))>0 && buf[i]!='\n')i++;
	

	if (n==-1) r = -1;
	if (n==0) r = 0;
	else r = i;
	
	((char*)buf)[i] = '\0';

	return r;

}

int main (int argc, char **argv)
{
      char buffer[MAXSIZE];
      int fd;

      fd = open("texto.txt", O_RDONLY);

      readln(fd, buffer, MAXSIZE);
      printf("linha -> %s\n", buffer);

      return 0;
}