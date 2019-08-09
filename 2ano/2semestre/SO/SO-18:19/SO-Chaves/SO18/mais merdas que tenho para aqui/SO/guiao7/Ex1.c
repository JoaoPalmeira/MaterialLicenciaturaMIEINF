#include <signal.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int segundos = 0, xsCtrC = 0;

void segs(int s)
{
	segundos ++;
}

void mostrar(int s)
{
	printf("%d:%d:%d\n", segundos/3600, segundos/60, segundos% 60);
	xsCtrC++;
}

void mostrarXsCtrlC(int s)
{
	printf("O Ctrl-C foi carregado %d vezes.", xsCtrC);
}

void main()
{
	char c;
	signal(SIGALRM, segs);
	signal(SIGINT, mostrar);
	signal(SIGQUIT, mostrarXsCtrlC);	
	while(read(0,&c,1))
	{
		alarm(1);
		pause();
	}
}