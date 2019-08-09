#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>


int main(int argc, char* argv[]){
	char* cmd[3] = {"ls","-la",NULL};
	char* cmd1[4] = {"cut","-c","20-30",NULL};
	char* cmd2[4] = {"grep","-v","echelinho",NULL};
	int status;

	int pd1[2], pd2[2];
	pipe(pd1);
	pipe(pd2);

	if(fork()==0){
		close(pd1[0]);
		dup2(pd1[1],1);
		execvp(cmd[0],cmd);
		_exit(0);
	}
	wait(&status);
	close(pd1[1]);

	if(fork()==0){
		close(pd1[1]);
		close(pd2[0]);
		dup2(pd1[0],0);
		dup2(pd2[1],1);
		execvp(cmd1[0],cmd1);
		_exit(0);
	} 

	wait(&status);
	close(pd1[0]);
	close(pd2[1]);

	if(fork()==0){
		close(pd2[1]);
		dup2(pd2[0],0);
		execvp(cmd2[0],cmd2);
		_exit(0);
	}
	wait(&status);
	close(pd2[0]);

	return 0;
}