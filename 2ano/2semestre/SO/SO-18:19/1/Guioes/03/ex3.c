#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
	int i;

	for(i=0; i<argc-1 ;i++){
		printf("argv[%d] <-> %s\n",i,argv[i+1]);
	}
	return 0;
}