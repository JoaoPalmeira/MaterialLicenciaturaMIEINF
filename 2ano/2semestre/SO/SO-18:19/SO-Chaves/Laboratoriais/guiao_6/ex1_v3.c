#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>

int main(){

	int fd= open("fifo",O_RDONLY);
	
	int r;
	char buf[32];
	while((r = read(fd,&buf,32)) != 0) write(1,buf,32); 

	exit(1);


}
