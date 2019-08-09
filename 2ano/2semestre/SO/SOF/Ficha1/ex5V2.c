#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int readln(int fd, char* buffer, int max){
	int i=0;
	int n;
	while(i<max-1){
		n=read(fd,&buffer[i],1);
		if(n<=0) break;
		if(buffer[i]=='\n') break;
		i++;
	}

	if(i<max)
		buffer[i]=0;
	return i;
}

int main(int agrc, char ** argv){
	char buffer[100];
	int fd;
	fd=open("textoEx5.txt", O_RDONLY);

	readln(fd, buffer, 100);
	printf("Linha lida: |%s|\n", buffer);
	close(fd);
	return 0;
}
