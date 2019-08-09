
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>



int alarmeligado = 0;

void ligar(int coco){
	alarmeligado = 1;
	alarm(1);
}

void desligar(int coco){
	alarmeligado = 0;
}

void alarme(int coco){
	if(alarmeligado!=0){
		write(1,"PASSOU MAIS UM SEGUNDO\n",22);
		alarm(1);
	}
}

int main (){

	char buf[800];
	int len = 500;
	alarmeligado = 0;
	signal(SIGUSR1,ligar);
	signal(SIGUSR2,desligar);
	signal(SIGALRM,alarme);

	while(1){
		read(1,buf,800);
		write(1,buf,strlen(buf));
	}

}