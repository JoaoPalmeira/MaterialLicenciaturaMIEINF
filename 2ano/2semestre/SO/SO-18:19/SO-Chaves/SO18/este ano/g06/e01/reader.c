#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
int main(){
	int f=open("/tmp/tempfifo",O_RDONLY);
	if(f<0){
		perror("error reading from fifo\n");
		exit(-1);
	}
	char c[8];
	while(read(f,&c,8)){
		printf("Received: %s\n",c);
	}
	exit(0);
}
