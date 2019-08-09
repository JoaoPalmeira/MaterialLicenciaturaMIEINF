#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int N = atoi(argv[1]);
	int n;

	char *buffer = (char *) malloc(N*sizeof(char *));

	while((n=read(0,buffer,N))>0){
		write(1,buffer,n);
	}

	return 0;
}