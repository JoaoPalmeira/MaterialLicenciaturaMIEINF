#include <unistd.h>
#include <string.h>
#include <stdio.h>
#define PIPE_BUF 20

int main(int argc, char const *argv[])
{
	if(argc!=2)
	{
		perror("Formato: ./const <constante>, por exemplo ./const 10\n");
		_exit(-1);
	}
	char linha[PIPE_BUF];
	int n;
	while(n=read(0,linha,PIPE_BUF))
	{
		write(1,linha,n-1);
		write(1,":",1);
		write(1,argv[1],strlen(argv[1]));
	}
	return 0;
}