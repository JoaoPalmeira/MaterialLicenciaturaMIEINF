#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char const *argv[])
{
	int n, N = atoi(argv[1]); 
	int i = 2;
	char* buf = (char*) malloc(sizeof(char) * N);
	int fich;
	char c = '\n';
	while(argv[i]){
		fich = open(argv[i], O_RDONLY);
		while(( n=read(fich,buf,N) ) > 0  ){
			write(1,buf,n);
		}	
		write(1,&c,1);
		i++;
	}
}