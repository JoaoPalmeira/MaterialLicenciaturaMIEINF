#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

void handler2(int signum){

}
void handler1(int signum){

}

int main (int argc, char **argv){
	int i;
	signal(SIGALRM,handler2);
	signal(SIGCHLD,handler1);


	
	return 0;
}
