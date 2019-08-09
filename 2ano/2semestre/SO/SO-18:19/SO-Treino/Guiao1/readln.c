#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <strings.h>

ssize_t readln(int fildes, void *buf, size_t nbyte){
	int i = 0;
	ssize_t res = 0;

	while(i<=nbyte && (res=read(fildes, &buf[i], 1))>0){
		if(((char*) buf)[i] == '\n'){return i;}
		i+=res;
	}
}

int main(int argc, char const *argv[]){
	int nbytes = 0;
	char buf[1024];
	while((nbytes=readln(0,buf,1024))>0){
		write(1,buf,nbytes);
		write(1, "\n", 1);
	}
	return 0;
}