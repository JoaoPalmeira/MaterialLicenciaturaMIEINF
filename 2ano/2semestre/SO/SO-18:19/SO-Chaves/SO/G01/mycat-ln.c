#include <unistd.h>
#include <fcntl.h>
ssize_t readln(int fildes, void *buf, size_t nbyte) {
	int i,n;
	for(i=0;i<nbyte && (n=read(fildes,buf+i,1))>0 && (buf+i)!='\n';i++);
	if(n<0) return -1;
	return i;
}

int main(int argc, char const argv[]) {
	char ch;

	while (readln(0,&ch,1)>0) // ler char 1 a 1
		write(1,&ch,1);
	ch='\n';
	write(1,&ch,1);
	return 0;
}
