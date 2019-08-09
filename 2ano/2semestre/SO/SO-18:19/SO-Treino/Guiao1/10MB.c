#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

#define SIZE_TO_WRITE 10*1024*1024

int main(int argc, char* argv[]){
	int fd;
	int i = 0;
	//Criar ficheiro
	if(argc>1){
		fd = open(argv[1], O_CREAT | O_TRUNC | O_WRONLY, 0777);

		//Controlo de erros
		if(fd == -1){
			perror("Não abriu o ficheiro.");
			return -1;
		}
		//Escrever
		char * buf = "a";

		for(i=0; i < SIZE_TO_WRITE; i++){
			write(fd, buf, 1);
		}
		//Fechamento
		close(fd);

		return 0;
	}
	else{
		printf("ERRO\n");
		return -1;
	}

}