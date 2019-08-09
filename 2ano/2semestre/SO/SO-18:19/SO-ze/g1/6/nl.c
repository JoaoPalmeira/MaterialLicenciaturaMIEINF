#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <unistd.h>
#include <fcntl.h>

int println(int fd){
	int i=-1;
	char c;
	while( read(fd,&c,1) > 0 & c !='\n'){
		write(1,&c,1);
		i++;
	}
	return i;
}

int main(int argc, char const *argv[])
{
	int n = 0;
	int fd=0;
	int line=1;

	char* nl = (char*) malloc(sizeof(char) * 10);
	char* buf= (char*) malloc(sizeof(char) * 1024);
	
	fd = open(argv[1],O_CREAT,O_RDONLY);
	
	while(n >= 0){
		snprintf(nl,10,"\n %d",line);
		strcat(nl,"  ");	
		write(1,nl, 5);
		n = println(fd);
		line++;
	}

}