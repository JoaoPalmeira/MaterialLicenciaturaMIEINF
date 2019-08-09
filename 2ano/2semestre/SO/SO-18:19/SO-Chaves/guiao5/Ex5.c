#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char** argv)
{
	char cmd[1024];
	char* arg[1024];
	int anterior , seguinte[2], i=0, b=0, status, primeiro=0;
	for(i=1;i < argc-1;i++)
	{
		arg[0] = strdup(argv[i++]);
		for(b=1; argv[i] != NULL && strcmp(argv[i],"|"); b++)
			arg[b] = strdup(argv[i++]);
		arg[b] = NULL;
		if(i!=argc-2)
			pipe(seguinte);
		if(fork()==0)
		{
			if(primeiro==0)
			{
				dup2(seguinte[1],1);
				close(seguinte[1]);
				primeiro++;
			}
			if(i>0)
			{
				dup2(anterior, 0);
				close(anterior);
			}
			if(i>0 && i<argc-2)
			{
				dup2(seguinte[1], 1);
				close(seguinte[1]);
			}
			execvp(arg[0], arg);
			perror(arg[0]);
			_exit(i);
		}
		wait(NULL);
		if(i>0)
			close(anterior);
		anterior = seguinte[0];
		close(seguinte[1]);
	}
	return 0;
}