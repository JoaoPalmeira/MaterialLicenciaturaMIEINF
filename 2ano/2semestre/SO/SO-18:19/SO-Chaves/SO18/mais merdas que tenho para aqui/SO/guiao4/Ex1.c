#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	char aux[250];
	int openwd = open("/etc/passwd",O_RDONLY, 00400);
	int opensaida = open("./saida.txt", O_CREAT | O_WRONLY | O_TRUNC, 0666);
	int openerros = open("./erros.txt", O_CREAT | O_WRONLY | O_TRUNC, 0666);
	dup2(openwd,0);
	dup2(opensaida,1);		
	dup2(openerros,2);
	read(0,&aux,250);
	write(1,&aux,250);
	write(2,&aux,250);
	printf("Fuck off\n");
	close(openwd);
	close(opensaida);
	close(openerros);
	return 0;
}