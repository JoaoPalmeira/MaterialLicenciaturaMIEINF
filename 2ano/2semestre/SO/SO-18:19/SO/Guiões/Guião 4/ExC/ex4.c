#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>
#include <assert.h>
#include <string.h>

// int dup (int fd);
// int dup2 (int fd1, int fd2);

int main(int argc,char* argv[]){
	int fd;

	assert(argc >= 6 && strcmp(argv[1],"-i") == 0 && strcmp(argv[3],"-o") == 0 );

	fd = open(argv[2],O_RDONLY);
	dup2(fd,0);
	close(fd);

	fd = open(argv[4],O_CREAT | O_WRONLY | O_TRUNC,0666);
	dup2(fd,1);
	close(fd);

	execvp(argv[5],&argv[5]);

	return 0;
}