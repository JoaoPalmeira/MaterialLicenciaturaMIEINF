#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>


int tempo = 0;

void incrementaTempo() {
	printf("\nola %d\n", getpid());
	alarm(5);

}

int main () {
	int i;


	
}
