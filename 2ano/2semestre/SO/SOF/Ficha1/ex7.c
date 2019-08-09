#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

struct buffer_t{
	char*  outroBuffer;
	int tamanho;
	int max;
	int fd, n;
};

int create_Buffer(struct buffer_t *buffer, int fd, int max){
	buffer->outroBuffer = (char*)malloc(sizeof(char)*max);
	buffer->fd=fd;
	buffer->max=max;
	buffer->tamanho=0;
	buffer->n=0;

	return max;
}

int destroy_buffer(struct buffer_t *buffer){
	free(buffer->outroBuffer);
}

int readln(struct buffer_t *buffer, char *line, int maxLine){
	int i=0, n;
	while(i<maxLine-1){
		if(buffer->tamanho == buffer->n){
			buffer->n = read(buffer->fd, buffer->outroBuffer, buffer->max);
			buffer->tamanho=0;
		}
		if(buffer->n==0) break;
		line[i]=buffer->outroBuffer[buffer->tamanho];
		buffer->tamanho++;
		if(line[i]=='\n') break;
		i++;
	}
	if(i<maxLine) line[i]=0;	
	return i;
		
}

int main(int agrc, char ** argv){
	char line[100];
	int fd, n;
	struct buffer_t *buf;

	fd=open("textoEx5.txt", O_RDONLY);

	create_Buffer(buf, fd, 1024);

	readln(buf, line, 100);
	printf("Linha lida: |%s|\n", line);

	readln(buf, line, 100);
        printf("Linha lida: |%s|\n", line);

	n=destroy_buffer(buf);
	close(fd);
	return 0;
}
