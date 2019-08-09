#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>




int main(){
	int fd= open("temp.txt",O_RDWR|O_CREAT,0666);
	dup2(fd,1);

	write(1,"ola bois",9);
	close(fd);
	return 0;
}
