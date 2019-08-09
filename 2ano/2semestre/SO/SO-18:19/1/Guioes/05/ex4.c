#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int pd[2];

	if(pipe(pd)<0){
		_exit(-1);
	}

	// codigo filho

	if(fork()==0){
		close(pd[1]);
		dup2(pd[0],0);
		execlp("wc","wc","-l",NULL);  // Pipe de leitura para informaçao ao filho
		close(pd[0]);
	}

	// codigo pai

	close(pd[0]);
	dup2(pd[1],1);
	execlp("ls","ls","/etc",NULL); // Pai escreve resultado no execlp no pipe
	close(pd[1]);                  // de escrita


	return 0;
}