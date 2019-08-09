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
#include <sys/stat.h>

//sort executa sem mais argumentos
//ordena o conteudo recebido no STDIN e emite no STDOUT
//criar ficheiro  "nome.txt" e escrever o nome do mesmo no pipe com nome "ordenar"
//"echo nome.txt > ordenar"
//ler repetidamente desse pipe e produzir ficheiros ordenados com extensão adicional "sorted" (O SORT FAZ ISTO, pelo menos assumimos...)

int main(int argc, char const *argv[]){
	int n;
	char* buf = (char*) malloc(sizeof(char));
	int fifo = mkfifo("ordenar", 0777);
	int fd = open("ordenar", O_RDONLY, 0666);

	for(int i = 1; i < argc; i++ ){
		if(fork()==0){
			dup2(fd, 1);
			execlp("echo", "echo", argv[i], ">", "ordenar", NULL);
			_exit(i);
		}
	}

	while((n=read(fd,buf,sizeof(buf)))>0){
		write(1,buf,n);
	}

	execlp("sort","sort", NULL);
	return 0;
}