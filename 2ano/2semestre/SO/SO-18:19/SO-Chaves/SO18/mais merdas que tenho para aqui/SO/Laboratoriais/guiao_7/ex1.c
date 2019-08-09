#include <signal.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>


int seg =0;
int v=0;
void hand (int s)
{
 seg++;

}
void termina(int s){


	printf("carregou %d vezes\n",v);
	_exit(1);

}
void show(int s)
{

	printf("%d:%d:%d\n",seg/3600,seg/69,seg % 60);
	v++;
}

int main(){


	signal(SIGALRM,hand);
	signal(SIGINT,show);
	signal(SIGQUIT,termina);
	
	while(1) { alarm(1); pause();}

}
