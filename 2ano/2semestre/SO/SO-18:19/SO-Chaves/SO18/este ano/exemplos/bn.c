#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>


int main(int argc, char *argv[]){


	if(argc<2) exit(-1);
	int fd=open(argv[1],O_RDWR);
	char c;
	int n,i=0;
	while((n=read(fd,&c,1))>0){
		if(c=='\n') i++;
	}
	printf("Num ENTER :%d\n",i);


	return 0;
}

