#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

ssize_t readln(int fildes, char *buf, size_t nbyte)
{
  int i;
  int n;
  for(i=0;i<nbyte && (n=read(fildes,buf+i,1))>0 && *(buf+i)!='\n';i++);
  if(n<0) 
  	return -1;
  return i;
}

int main(int argc, char const *argv[])
{
	char c;
	while(readln(0,&c,1)>0)
		write(1,&c,1);
	c='\n';
	write(1,&c,1);
	return 0;
}