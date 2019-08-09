#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
typedef void (*sighandler_t)(int);


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
