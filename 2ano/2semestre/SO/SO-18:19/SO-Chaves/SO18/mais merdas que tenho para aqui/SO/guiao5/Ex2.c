#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int pp[2];
	char c;
	pipe(pp);
	if(pipe(pp)==-1)
		exit(-1);
	if(fork()==0)
	{
		close(pp[1]);
		while(read(pp[0],&c,1))
			write(1,&c,1);
		close(pp[0]);
		_exit(1);
	}
	else
	{
		close(pp[0]);
		while(read(0,&c,1))
			write(pp[1],&c,1);
		close(pp[1]);
		wait(NULL);
	}
	return 0;
}