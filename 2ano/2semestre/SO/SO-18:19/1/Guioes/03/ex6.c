#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>


void mySystem(char cmd[]){
	int i=0;
	int status;
	char* token;
	char* cmds[1024];

	token = strtok(cmd," ");

	while(token!=NULL){
		cmds[i] = strdup(token);
		i++;
		token = strtok(NULL," ");
	}

	cmds[i] = NULL;

	if(fork()==0){
		execvp(cmds[0],cmds);
		perror("Deu erro no execvp...");
		exit(-1);
	}

	wait(&status);

}



int main(int argc, char const *argv[])
{
	
	char cmd[] = "/bin/ls -l -a -h";
	mySystem(cmd);
	return 0;
}