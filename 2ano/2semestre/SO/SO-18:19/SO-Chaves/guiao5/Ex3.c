#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	int n;
	int pp[2];
	char c;
	if(pipe(pp)<0)
		exit(-1);
	if(fork()==0)
	{
		close(pp[1]);
		dup2(pp[0],0);
		close(pp[0]);
		execlp("wc","wc",NULL);
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