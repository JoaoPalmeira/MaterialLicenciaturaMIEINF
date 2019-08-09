#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	char aux[2500];
	int openwd = open("/etc/passwd",O_RDONLY, 00400);
	int opensaida = open("./saida.txt", O_CREAT | O_WRONLY | O_TRUNC, 0666);
	int openerros = open("./erros.txt", O_CREAT | O_WRONLY | O_TRUNC, 0666);
	dup2(openwd,0);
	dup2(opensaida,1);		
	dup2(openerros,2);
	if(fork()==0)
	{
		read(0,&aux,2500);
		write(1,&aux,2500);
		write(2,&aux,2500);
		printf("Hello it's me\n");
		write(1,"ola",3);
		close(openwd);
		close(opensaida);
		close(openerros);
		_exit(0);
	}
	close(openwd);
	close(opensaida);
	close(openerros);
	return 0;
}