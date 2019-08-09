#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

typedef void (*sighandler_t)(int);
int secs=0,cc=0;
void handleSig(int sign){
	printf("%d:%d:%d s\n",secs/3600,secs/60,secs%60);
}
void handleInt(int sign){
	cc++;
}
void handleAlrm(int sign){
	secs++;
}
void handleQuit(int sign){
	printf("CTRL C foi usado %d vezes!\n",cc);
	exit(0);
}
int main(int argc, char*argv[]){
	int i=0;
	signal(SIGTERM, handleSig);
	signal(SIGINT, handleInt);
	signal(SIGALRM, handleAlrm);
	signal(SIGQUIT, handleQuit);
	while(1){
		alarm(1);
		pause();
	}

	return 0;
}
