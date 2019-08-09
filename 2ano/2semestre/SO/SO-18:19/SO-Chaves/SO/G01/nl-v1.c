#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

ssize_t readln(int fildes, void *buf, size_t nbyte) {
	int i,n;
	for(i=0;i<nbyte && (n=read(fildes,buf+i,1))>0 && (buf+i)!='\n';i++) ;
	if(n<0) return -1;
	return i;
}

int main(int argc, char *argv[]) {
	if(argc<1) perror("Invalid N-Args");
	char ch;
	int count=0;
	int fp= open(argv[1], O_RDONLY,0666);
	while (readln(fp,&ch,1)>0) {
		write(1,&ch,1);
		printf("linha %d",count++);
	}

	ch='\n';
	write(1,&ch,1);
	return 0;
}
