#ifndef _GNU_SOURCE
#define _GNU SOURCE 1
#endif
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>


int main(int argc, char *argv[]){
	char c[16];
	int n;
	while((n=read(0,&c,16))){
		//c[0]?
		int temp=atoi(&c[0]);
		if(temp%2==0){//if pair
			
		}
		else{

		}
		char* filename=malloc(5);
		asprintf(&filename,"%d.txt",temp);
		int fd=open(filename,O_RDWR|O_CREAT|O_TRUNC,0666);
		if(fd==-1){
			perror("Error opening file!\n");
			exit(fd);
		}
		write(fd,&c,n);
		printf("noice\n");
	}
	return 0;
}
