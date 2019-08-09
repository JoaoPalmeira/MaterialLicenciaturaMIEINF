#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[]){
	
	int pd[2];
	char c;
	char aux;
	int n;
	int status;

	pipe(pd);

	if(pipe(pd)<0){
		printf("Erro ao criar pipe...\n");
		return -1;
	}

	if(fork()==0){
		while((n=read(pd[0],&c,1))>0){
			write(1,&c,n);
		}
	}

	//while((n=read(1,&aux,1)>0)){
		//write(pd[1],&aux,n);     // codigo que envia informacao do pai para o filho
	//}

	write(pd[1],"Teste 1\n",8);

	sleep(5);

	write(pd[1],"Teste 2\n",8);

	/*if(fork()==0){
		write(pd[1],"Teste 1\n",8);
		exit(0);
	}

	else{

		while((n=read(pd[0],&c,1))>0){. // O pai fica sempre a espera do filho
			                            // quando a escrita é feita no filho
			                            // NAO DA DESTA FORMA , PRECISA DOS CLOSES!!!!!!!
			write(1,&c,1);
		}
	}*/

	return 0;
}