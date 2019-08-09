#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[]){
	int fd;
	fd = open("log", O_WRONLY);
	
	if(argc!=2){
               printf("falta argumento!\n");
               return 1;
        }
	else {write(fd ,argv[1], strlen(argv[1]));}

	close(fd);
}
