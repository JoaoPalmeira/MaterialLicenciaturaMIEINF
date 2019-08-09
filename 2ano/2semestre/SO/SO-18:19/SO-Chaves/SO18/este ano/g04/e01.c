#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
int main(){
	int infile=open("/etc/passwd",O_RDONLY,00400);
	int outfile=open("saida.txt",O_RDWR|O_CREAT,0666);
	int errfile=open("errors.txt",O_RDWR|O_CREAT,0666);
	int saveinput=dup(0);
	int saveoutput=dup(1);
	int saveerr=dup(2);
	dup2(infile,0);
	dup2(outfile,1);
	dup2(errfile,2);
	close(infile);
	close(outfile);
	close(errfile);
	char rd[250];
	read(0,&rd,250);
	printf("antoniooooooooo\n");
	write(1,&rd,250);
	write(2,"errroeroeorer",15);
/*
	dup2(saveinput,0);
	dup2(saveoutput,1);
	dup2(saveerr,2);
	close(saveinput);
	close(saveoutput);
	close(saveerr);
*/
	return 0;
}
