#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
	int i;
	int status;
	char* cmd[argc-1];

	for(i=0;i<argc-1;i++){

		cmd[i] = argv[i+1];	 // neste ciclo temos de remover o argumento
		                     // do ficheiro executal e nunca esquecer de 
		                     // fechar com NULL;	
	}

	cmd[i] = NULL;

	if(fork()==0){
		execvp(cmd[0],cmd);
		_exit(-1);
	}

	wait(&status);

	return 0;
}