#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
int main(){
	int infile=open("/etc/passwd",O_RDONLY,00400);
	int outfile=open("saida.txt",O_RDWR|O_CREAT,0666);
	int errfile=open("errors.txt",O_RDWR|O_CREAT,0666);
	int saveout=dup(1);
	dup2(infile,0);
	dup2(outfile,1);
	dup2(errfile,2);
	char rd[250];
//	read(0,&rd,250);
//	write(1,&rd,250);
//	write(2,"errroeroeorer",15);
	if(fork()==0)
		execlp("wc","wc",NULL);
	else{
		close(infile);
		close(outfile);
		close(errfile);
		dup2(saveout,1);
		printf("CLOSES\n");
	}

	return 0;
}
