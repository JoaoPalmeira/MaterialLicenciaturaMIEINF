#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>


/* Com este codigo so consigo executar um pipe */

char* trim (char *s) {
   while(isspace(*s)) s++;
   char *back = s + strlen(s);
   while(isspace(*--back));
   *(back+1) = '\0';
   return s;
}

void mySystem(char* cmd[]){
	int i=0,j=0;
	int flag=1;
	int status;
	int pd[2];
	char* token, *aux;
	char* cmds[1024];

	while(cmd[i] != NULL){
		aux = strdup(cmd[i]);
		j=0;

		token = strtok(aux," ");
		while(token!=NULL){
			cmds[j] = strdup(token);
			j++;
			token = strtok(NULL," ");
		}
		cmds[j] = NULL;



		if(flag==1){

			pipe(pd);

			if(fork()==0){
				close(pd[0]);
				dup2(pd[1],1);
				execvp(cmds[0],cmds);
			}

			flag = 0;
			wait(&status);
			close(pd[1]);

		}
		else if(flag==0){

			write(1,">>>\n",4);

			if(fork()==0){
				close(pd[1]);
				dup2(pd[0],0);
				execvp(cmds[0],cmds);
			}

			flag = 1;
			wait(&status);
			close(pd[0]);

			write(1,"<<<\n",4);

		}

		i++;
		
	}

	
	
}



int main(int argc, char const *argv[])
{	
	int i=0;
	char cmd[] = "ls -l | head -4"; // so da duas instruçoes	
	char *cmds[1024];
	char *token;

	token = strtok(cmd,"|");
	while(token!=NULL){
		cmds[i] = trim(strdup(token));
		i++;
		token = strtok(NULL,"|");

	}
	cmds[i] = NULL;

	mySystem(cmds);
	
	return 0;
}