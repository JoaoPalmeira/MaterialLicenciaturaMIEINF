#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int pd[2];
	char c, aux;

	pipe(pd);

	if(pipe(pd)<0){
		printf("Erro ao criar pipe...\n");
		return -1;
	}

	if(fork()){
		
		close(pd[0]);

		//while(read(1,&aux,1)>0){
			//write(pd[1],&aux,1);
		//}

		write(pd[1],"Teste 1\n",8);

		sleep(5);

		write(pd[1],"Teste 2\n",8);

		close(pd[1]);
	}

	close(pd[1]);

	while(read(pd[0],&c,1)>0){
		write(1,&c,1);
	}

	close(pd[0]);


	return 0;
}