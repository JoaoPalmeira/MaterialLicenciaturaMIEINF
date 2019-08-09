#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <signal.h>


int main(int argc, char *argv[])
{
	int fdTemp[Max];
	int char* cmds[1024];
	int i=1;

	while(i<argc){
		cmds[i] = strdup(argv[i]);
		printf("%s\n",cmds[i]);
		i++;
	}



	return 0;
}