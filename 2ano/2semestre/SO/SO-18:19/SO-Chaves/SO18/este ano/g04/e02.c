#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
int main(){
	int infile=open("/etc/passwd",O_RDONLY,00400);
	int outfile=open("saida.txt",O_RDWR|O_CREAT,0666);
	int errfile=open("errors.txt",O_RDWR|O_CREAT,0666);
	dup2(infile,0);
	dup2(outfile,1);
	dup2(errfile,2);


	if(fork()==0){
		char rd[250];
		read(0,&rd,250);
		printf("antoniooooooooo\n");
//	write(1,&rd,250);
//	write(2,"errroeroeorer",15);
	}
	close(infile);
	close(outfile);
	close(errfile);

	return 0;
}
