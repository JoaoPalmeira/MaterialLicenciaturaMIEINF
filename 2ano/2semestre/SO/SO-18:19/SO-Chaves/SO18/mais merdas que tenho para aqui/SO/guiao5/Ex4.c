#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main()
{
	int pp[2];
	pipe(pp);
	if(pipe(pp)==-1)
		exit(-1);
	if(fork()==0)
	{
		close(pp[0]);
		dup2(pp[1], 1);
		close(pp[1]);
		execlp("ls", "ls", "/etc", NULL);
	}
	else
	{
		close(pp[1]);
		dup2(pp[0], 0);
		close(pp[0]);
		execlp("wc", "wc", "-l", NULL);
	}
	return 0;
}