#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>

// int dup (int fd);
// int dup2 (int fd1, int fd2);

int main(int argc,char* argv[]){
	int fd_in,fd_out,fd_error;
	fd_in = open("/etc/passwd",O_RDONLY);
	if(fd_in == -1){
		perror("Não consegui criar /etc/passwd\n");
	}
	dup2(fd_in,0);
	close(fd_in);

	fd_out = open("saida.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
	if(fd_out == -1){
		perror("Não consegui criar saida.txt\n");
	}
	dup2(fd_out,1);
	close(fd_out);

	fd_error = open("erros.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
	if(fd_error == -1){
		perror("Não consegui criar erros.txt\n");
	}
	dup2(fd_error,2);
	close(fd_error);

	pid_t processo = fork();
	if(processo == -1){
		perror("Não consegui criar o processo filho\n");
		_exit(0);
	}
	if(processo == 0){
		int n;
		char c;
		while((n = read(0,&c,1)) == 1){
			write(1,&c,1);
		}
		_exit(0);
	}
	return 0;
}