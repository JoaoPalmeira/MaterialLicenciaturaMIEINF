#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#define PIPE_BUF 20

int main(int argc, char const *argv[])
{
	if(argc!=4)
	{
		perror("Formato: filter <coluna> <operador> <operando>, por exemplo filter 2 < 4\n");
		_exit(-1);
	}
	char linha[PIPE_BUF];
	char aux[PIPE_BUF];
	char* token;
	int i;
	int n;
	while(n=read(0,linha,PIPE_BUF))
	{
		strncpy(aux,linha,n);
		token=strtok(aux,":");
		for(i=atoi(argv[1])-1;token!=NULL && i>0;i--)
			token=strtok(NULL,":");
		if(!strcmp(argv[2],"=") && atoi(token)==atoi(argv[3]))
			write(1,linha,n);
		if(!strcmp(argv[2],">=") && atoi(token)>=atoi(argv[3]))
			write(1,linha,n);
		if(!strcmp(argv[2],"<=") && atoi(token)<=atoi(argv[3]))
			write(1,linha,n);
		if(!strcmp(argv[2],">") && atoi(token)>atoi(argv[3]))
			write(1,linha,n);
		if(!strcmp(argv[2],"<") && atoi(token)<atoi(argv[3]))
			write(1,linha,n);
		if(!strcmp(argv[2],"!=") && atoi(token)!=atoi(argv[3]))
			write(1,linha,n);
	}
	return 0;
}