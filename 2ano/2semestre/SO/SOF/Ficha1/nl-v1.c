#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

#define BUFFSIZE 128

ssize_t readln(int fildes, void *buf, size_t nbyte){
	int i=0,aux;
	for(i=0;i<nbyte;i++){
		aux=read(fildes,buf+i,1);
		if(((char*)buf)[i]=='\n') break;
		if(aux<=0) return aux;
	}
	return i+1;
}

void main(int argc, char *argv[]){
	char buffer[BUFFSIZE],linha[8];
	int fd=0,aux,i=1;
	if(argc>1) fd=open(argv[1],O_RDONLY);
	while( aux==readln(fd,&buffer,BUFFSIZE) ){
		if(aux){
			sprintf(linha,"    %d   ",i);
			write(1,linha,8);
			write(1,buffer,aux);
			i++;
		}
	}
	if(fd!=0) close(fd);
	
}