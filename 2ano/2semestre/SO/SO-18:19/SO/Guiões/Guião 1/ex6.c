#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */


// int open(const char *path, int oflag [, mode]);
// ssize_t read(int fildes, void *buf, size_t nbyte);
// ssize_t write(int fildes, const void *buf, size_t nbyte);
// int close(int fildes);

ssize_t readln(int fildes,void *buf, size_t nbyte){
	int n;
	int t = 0;
	int i = 1;
	char *s = NULL;
	while((n = read(fildes,&buf,nbyte))){
		
		write(1,&buf,n);
		i++;
		t += n;
	}
	return t;
}

int main(int argc,char* argv[]){
	char buf[1024];
	int n = readln(0,&buf,sizeof(buf));
	return 0;
}
