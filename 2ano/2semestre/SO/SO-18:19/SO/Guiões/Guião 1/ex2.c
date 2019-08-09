#include <stdio.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */


// int open(const char *path, int oflag [, mode]);
// ssize_t read(int fildes, void *buf, size_t nbyte);
// ssize_t write(int fildes, const void *buf, size_t nbyte);
// int close(int fildes);

int main(int argc,char* argv[]){
	char c;
	int i; 
	if(argc > 1){
		int fd = open(argv[1],O_CREAT | O_WRONLY | O_TRUNC, 0666);
		if(fd == -1){
			perror("Não consegui abrir o ficheiro");
			return -1;
		}
		int tamanho = 10*1024*1024;
		for(i=0;i<tamanho;i++)
			write(fd,"Ok",2);
		close(fd);
	}
	else{
		printf("ERRO\n");
	}
	return 0;
}