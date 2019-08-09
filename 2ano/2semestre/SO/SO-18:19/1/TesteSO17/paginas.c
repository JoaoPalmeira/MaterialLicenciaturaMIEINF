#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>

int main(int argc, char* argv[])
{
	int r=1,w=0,k;
	int i=0;
	char* cmds[1024];
	int pd[2];
	int status;
	int n;
	char c;

	while(r<argc){
		cmds[w] = strdup(argv[r]);
		w++;
		r++;
	}

	cmds[w] = NULL;

	for(k=0 ; k<10; k++){
		
		i=0;

		while(cmds[i] != NULL){
			write(1,">>>\n",4);
			
			pipe(pd);
	
			if(fork()==0){
				close(pd[0]);
				dup2(pd[1],1);
				execlp(cmds[i],cmds[i],NULL);
				perror("Deu erro no comando...");
				exit(1);
			}
			

			wait(&status);
			close(pd[1]);

			while((n=read(pd[0],&c,1))>0){
				write(1,&c,n);
			}
			write(1,"<<<\n",4);
			close(pd[0]);
			i++;
		}
	}

	return 0;
}
