#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#define PIPE_BUF 20

int main(int argc, char * const argv[])
{
	if(argc<3)
	{
		perror("Formato: spawn <cmd> <args...>, por exemplo spawn mailx -s $3 x@y.com\n");
		_exit(-1);
	}
	char linha[PIPE_BUF];
	char aux[PIPE_BUF];
	char* token;
	char* comando="$";
	int n,i,status;
	while(n=read(0,linha,PIPE_BUF))
	{
		for(i=0;i<argc;i++)
		{
			if(argv[i][0]=='$')
			{
				strncpy(aux,linha,n);
				token=strtok(aux,":");
				for(i=atoi(strtok(argv[i],"$"))-1;token!=NULL && i>0;i--)
					token=strtok(NULL,":");
				strcat(comando,token);
				strcpy(argv[i],comando);
			}
		}
		if(fork()==0)
		{
			_exit(0);
			execvp(argv[0],argv);
		}
		write(1,linha,n-1);
		write(1,":",1);
		wait(&status);
		printf("%d\n", WEXITSTATUS(status));
		//write(1,WEXITSTATUS(status),sizeof(int));
	}
	return 0;
}