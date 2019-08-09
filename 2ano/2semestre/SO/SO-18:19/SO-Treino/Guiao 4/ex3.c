#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>

// int dup (int fd);
// int dup2 (int fd1, int fd2);

int main(int argc, char* argv[]){

	int fd = open("/etc/passwd",O_RDONLY );
	dup2(fd,0);
	close(fd);

	fd = open("saida.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
	dup2(fd,1);
	close(fd);

	fd = open("erros.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
	dup2(fd,2);
	close(fd);	
	

	execlp("wc","wc",NULL);	

	return 0;
}