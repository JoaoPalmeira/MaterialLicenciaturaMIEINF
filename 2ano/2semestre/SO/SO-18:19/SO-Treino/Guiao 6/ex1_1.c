#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>

int main(){
	/* creates a named pipe called fifo */
	if( mkfifo("fifo",0666) == -1)
		perror("Pipe failed");

	return 0;
}