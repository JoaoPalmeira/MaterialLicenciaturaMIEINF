#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>

int main(int argc, char *argv[])
{
	int r=1,w=0;
	int i,j;
	char *cmd[1024];
	int pd[2];
	int status;
	char buf;

	while(r<argc){
		cmd[w] = strdup(argv[r]);
		w++;
		r++;
	}

	cmd[w] = NULL;

	for(i=0; i<10 ; i++){

		 j=0;

		 while(cmd[j]!=NULL){

		 	pipe(pd);

		 	if(fork()==0){
		 		close(pd[0]);
		 		dup2(pd[1],1);
		 		execlp(cmd[j],cmd[j],NULL);
		 		_exit(1);
		 	}

		 	wait(&status);
		 	close(pd[1]);

		 	while(read(pd[0],&buf,1)>0){
		 		write(1,&buf,1);
		 	}

		 	j++;
		 }
	}
	return 0;
}