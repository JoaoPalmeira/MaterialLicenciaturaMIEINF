#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char** argv){
	int fd, i;
	char a = 'a';

	if(argc!=2){
		printf("Preciso mais um!\n");
		return 1;
	}

	fd = open(argv[1], O_CREAT | O_TRUNC | O_WRONLY, 0640);
	printf("Chego aqui!\n");
	for(i=0; i<10000000; i++){
		write(fd, &a, 1);
	}

	close(fd);
}
