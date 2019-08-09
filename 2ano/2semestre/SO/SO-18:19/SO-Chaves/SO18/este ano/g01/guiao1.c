#include <unistd.h>
#include <fcntl.h>
/*
ssize_t read(int fildes, void* buf, size_t nbyte);
ssize_t write(int fildes, const void *buf, size_t nbyte)
*/
int main(int argc, char argv[]){
	char c;
	while(read(0,&c,1))		// stdin - 0 
		write(1,&c,1);		// stdout - 1 
}