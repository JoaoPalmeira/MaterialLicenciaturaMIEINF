#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int pd[2];
	char c;
	int n;

	pipe(pd);

	if(pipe(pd)<0){
		printf("Erro ao criar pipe...\n");
		return -1;
	}

	// codigo do filho

	if(fork()==0){

		close(pd[1]);
		dup2(pd[0],0);
		execvp("wc",NULL);
		close(pd[0]);
	}

	// codigo do pai


	close(pd[0]);

	write(1,">>",2);

	while((n=read(1,&c,1024)>0) && c!='\\'){
		write(pd[1],&c,n);     // codigo que envia informacao do pai para o filho
	}
	

	close(pd[1]);




	return 0;
}