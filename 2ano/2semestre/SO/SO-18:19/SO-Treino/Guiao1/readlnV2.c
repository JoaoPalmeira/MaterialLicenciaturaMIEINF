#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <strings.h>

ssize_t readln(int fildes, void *buf, size_t nbyte){
	int i = 0;
	ssize_t res = 0;

	while(i<=nbyte && (res=read(fildes, &buf[i], 1))>0){
		if(((char*) buf)[i] == '\n'){break;}
		i+=res;
	}
	if(i>=nbyte)
		((char*) buf)[i] = '\n';
	else
		((char*) buf)[i+1] = '\n';

	return i;
}

/*ssize_t readln(int fildes, char *buf, size_t nbyte) {
	int i = 0;

	while(i<nbyte-1 && 
     	read(fildes, buf+i, 1) > 0 &&
     	buf[i] != '\n') {
		i++;
	}

	if(i>=nbyte)
		buf[i] = 0;
	else
		buf[i+1] = 0;

	return i;
}*/


int main(int argc, char *argv[]){
	int nbytes = 0;
	char buf[1024];

	int fd = -1;

	fd = open(argv[1], O_RDONLY);

	if(fd!=-1){

		while((nbytes=readln(fd,buf,1024))>0){
			write(1,buf,nbytes);
			write(1, "\n", 1);
		}
		close(fd);
	}
	else{
		perror("Deu bosta");
	}
	return 0;
}