#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	char** args=(char**)malloc(sizeof(argc));
	int i;
	for(i=0;i<argc;i++)
	{
		args[i]=(char*)malloc(strlen(argv[i]));
		args[i]=strcpy(args[i],argv[i]);
	}
	args[i]=NULL;
	execvp(args[0], args);
}