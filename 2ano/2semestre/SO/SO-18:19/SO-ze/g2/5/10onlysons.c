#include <unistd.h>     /*chamadas ao sistema: defs e decls essenciais*/
#include <sys/wait.h>   /*chamadas wait*() e macros relacionadas*/
#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	int pid, i = 0;
	int status;
	while(i < 10){
		if( (pid=fork()) != 0){
			break;
		}
		printf("Filho %d, pid: %d, pai: %d \n",i+1,getpid(),getppid());
		i++;
	}
	while(wait(&status)>0);
	return 0;
}