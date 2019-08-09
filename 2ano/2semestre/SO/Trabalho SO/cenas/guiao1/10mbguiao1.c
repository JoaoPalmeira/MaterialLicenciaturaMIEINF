#define DEZMEGA 1024*1024
#include <unistd.h>
#include <fcntl.h>

int main (int argc, char *argv[]){
	char ch = 'a';
	int c = 0;
	int fd;

	fd = open(argv[1], O_CREAT | O_WRONLY, 0744);   // sem O_WRONLY não iria escrever no ficheiro
	if(fd == -1){
		perror("Could not open file");
		exit(-1);
	}

    while (i = 0; i<DEZMEGA, i++){
    	...
    }

	exit(0);
}