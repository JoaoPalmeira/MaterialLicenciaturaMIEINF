#include <unistd.h>     /*chamadas ao sistema: defs e decls essenciais*/
#include <sys/wait.h>   /*chamadas wait*() e macros relacionadas*/
#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	int p = fork();
	if(p==0){
		printf("Sou o filho. O pai é %d. Eu sou %d \n", getppid(), getpid());
	} else {
		printf("Sou o pai. O filho é %d. Eu sou %d. O meu pai é %d \n", p, getpid(), getppid() );
	}
	return 0;
}