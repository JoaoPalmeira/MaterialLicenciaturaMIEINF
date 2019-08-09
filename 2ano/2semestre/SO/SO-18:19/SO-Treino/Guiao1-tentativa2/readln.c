#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */


// int open(const char *path, int oflag [, mode]);
// ssize_t read(int fildes, void *buf, size_t nbyte);
// ssize_t write(int fildes, const void *buf, size_t nbyte);
// int close(int fildes);

ssize_t readln(int fildes,void *buf, size_t nbyte){
	char c;
	int n;
	int t = 0;
	while((n = read(fildes,&c,1))>0 && c != '\n'){
		t++;
	}
	return t;
}

int main(int argc, char* argv[]){
	char* buf;
	int fd = open(argv[1], O_RDONLY);
	int n = readln(fd,buf,1);
	printf("%d\n", n);
	return 0;
}