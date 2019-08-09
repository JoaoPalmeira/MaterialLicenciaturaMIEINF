#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

ssize_t readln(int fildes, void *buf, size_t nbyte){
	
	int i=0;
	int c;
	
	for(i=0 ; i<nbyte && read(fildes,&c,1)>0 ; i++){
		((char*) buf)[i] = c;
		if(c=='\n') break;
	}
	return i;
}

int main(int argc, char const *argv[])
{
	int n;
	char buffer[1024];

	while( (n=readln(0,buffer,1024))>0){
		write(1,buffer,n);
	}

	return 0;
}