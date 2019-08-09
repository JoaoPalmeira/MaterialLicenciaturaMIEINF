#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	execlp("/bin/ls","ls","-l",NULL);
	_exit(-1);
	return 0;	
}