#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
ssize_t readln(int fildes,char *buf, size_t nbyte){
	int i=0;
	int n;
	while(i<nbyte && (n=read(fildes,buf+i,1))>0 && *(buf+i)!='\n') i++;
	*(buf+i)='\n';
	if(n<0) return n;
	return i;

}
int main(int argc, char const *argv[]){
	if(argc<3) printf("Erro, falta de argumentos!\n");
	else{
		int n=atoi(argv[1]);
		int fich=open(argv[2],O_RDONLY|O_WRONLY);
		char *c=malloc(n);
		while(readln(fich,c,n)>0){
			write(1,c,1);
		}
		close(fich);

	}
	return 0;
}

