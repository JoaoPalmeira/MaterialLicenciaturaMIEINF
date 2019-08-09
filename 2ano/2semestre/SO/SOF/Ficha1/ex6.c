#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int readln(int fd, char* buffer, int max){
	int i=1;
	int n;
	n=read(fd, buffer, 1);
	while(i<max && buffer[i-1]!='\n'){
		n=read(fd,buffer+i,1);
		i++;	
	}
	buffer[i]=0;
	return i-1;	
}

int main(int agrc, char ** argv){
	char buffer[100];
	int i=0;
	int n;
	
        while(1){
              n=readln(0, buffer, 100);
	      if(n==0) return 0;
	      write(1, buffer, n);
	}
	return 0;
}
