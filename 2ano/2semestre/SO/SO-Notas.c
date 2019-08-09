#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

int open(const char *path, int oflag [, mode]);

	// const char *path --> caminho / nome do ficheiro 
	// int oflag --> flag com as permissões de escrita , leitura , criação 
	// flags {O_RDONLY, O_WRONLY,O_RDWRONLY,O_APPEND,O_TRUNC,O_CREATE ==> implica o 3º argumento,O_EXCL}

ssize_t read(int fildes, void *buf, size_t nbyte);

	//int fildes --> descritor de ficheiro que vem do open
	//void *buff --> apontador 
	//size_t nbyte --> numero de bytes que quero ler

ssize_t write(int fildes, const void *buf, size_t nbyte);

	//int fildes --> descritor de ficheiro que vem do open
	//void *buff --> apontador 
	//size_t nbyte --> numero de bytes que quero ler

int close(int fildes);

	//int fildes --> descritor de ficheiro que vem do open 

// Para escrever em C em octal por um 0 antes dos valores das permissões

// U(Utilizador) { R = 4 W = 2  X = 1} | G(Grupo) { R W X}  | O(Outros) { R W X} 

//   4 + 2 = 6-Escrita/leitura    			4-Leitura   		0-Nao pode fazer nada


// ler - 0 -> standard input (stdin) , 1 - "" output (stdout) , 2- stadard error (stderror)



// Exercicio 1 - ler do stdin e escrever no stdout

int main() {
	char c;
	while(read(0, &c, 1)==1) write(1, &c, 1);
	return 0;
}

// Exercicio 2 

int main(int argc, char* argv[]){
	int fd = open(argv[1]. O_CREAT|O_TRUNC|O_WRONLY. 0666). i;
	char zero = 0;
	if (fd == -1){
		perror(argv[1]);
		return 1;
	}
	for(int i = 0; i != 10*1024*1024; i++)
		write(fd, &zero, sizeof(zero));
	close(fd);
	return 0;
}

// Exercicio 3

int main(int argc, char* argv []){
	char c[atoi(argv[1])];
	int n;
	while ((n = read(0, c, sizeof(c)))>0){
		write(1, c, n);
	}
	return 0;
}

// Exercicio 5

ssize_t readln(int fd, void* buf, size_t bsize){
	int n = 0, r;
	char* p = (char*)buf;
	while(n<bsize && (r=read(fd, p+n, 1)) == 1 && p[n] != '\n')
		n++;
	return r == -1 ? -1 : n;
}

















