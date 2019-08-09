#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	char* cmds[100];
	char* token;
	char buffer[100];
	int i=0;
	int status;

	printf("Introduza comandos:\n");

	do{
		i=0;
		printf(">>");
		scanf("%s",buffer);

		if(strcmp(buffer,"exit")){

			token = strtok(buffer," ");
			while(token!=NULL){
				cmds[i] = strdup(token);
				i++;
				token = strtok(NULL," ");
			}
			cmds[i] = NULL;

			if(strcmp(cmds[i-1],"&")==0){

				if(fork()==0){
					execvp(cmds[0],cmds);
				}
			}
			else{

				if(fork()==0){
					execvp(cmds[0],cmds);
				}
				wait(&status);
			}
		}
	}while(strcmp(buffer,"exit"));
	
	return 0;
}