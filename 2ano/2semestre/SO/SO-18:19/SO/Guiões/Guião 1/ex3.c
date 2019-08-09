#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */


// int open(const char *path, int oflag [, mode]);
// ssize_t read(int fildes, void *buf, size_t nbyte);
// ssize_t write(int fildes, const void *buf, size_t nbyte);
// int close(int fildes);

int main(int argc, char* argv[]){
	if(argc > 1){
		int tamanho = atoi(argv[1]);
		char buf[tamanho+1];
		int lidos;
		while(lidos = (read(0,&buf,sizeof(buf)))){
			write(1,&buf,lidos);
		}
	}
	else{
		perror("Numero de argumentos invalidos");
		return -1;
	}
	return 0;
}