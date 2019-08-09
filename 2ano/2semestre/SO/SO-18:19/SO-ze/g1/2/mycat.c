#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <unistd.h>
#include <fcntl.h>
int main(int argc, char const *argv[])
{
	int n, N = atoi(argv[1]); 
	char* buf = (char*) malloc(sizeof(char) * N);
	while(( n=read(0,buf,N) ) > 0){
		write(1,buf,n);
	}
}