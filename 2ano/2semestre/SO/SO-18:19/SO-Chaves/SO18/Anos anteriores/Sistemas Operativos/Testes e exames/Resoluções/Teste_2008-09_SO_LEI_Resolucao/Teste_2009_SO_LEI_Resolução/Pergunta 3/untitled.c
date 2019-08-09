
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>


void ligar(int coco){
	alarmeligado = 1;
	alarm(1);
}

void desligar(int coco){
	alarmeligado = 0;
}

void alarme(int coco){
	if(alarmligado!=0){
		write(1,"PASSOU MAIS UM SEGUNDO",22);
		alarm(1);
	}
}

int alarmeligado;

int main (){

	char *buf = NULL;
	int len = 500;
	alarmeligado = 0;
	signal(SIGUSR1,ligar);
	signal(SIGUSR2,desligar);
	signal(ALRM,alarme);

	while(1){
		getline(&buf,&500,stdin);
		write(1,buf,strlen(buf));
	}

}