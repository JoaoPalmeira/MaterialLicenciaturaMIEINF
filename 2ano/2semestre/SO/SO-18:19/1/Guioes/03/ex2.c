#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	int status;

	if(fork()==0){
		execlp("/bin/ls","ls","-l",NULL);
		exit(-1);
	}
	wait(&status);
	return 0;
}