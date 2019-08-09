#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/stat.h>
#include<sys/types.h>


int main(){


	int fd = open("fifo",O_WRONLY);
	int r;
	char buf[32];
        while((r = read(1,&buf,32)) != 0) write(fd,&buf,r);
	exit(1);	


}
