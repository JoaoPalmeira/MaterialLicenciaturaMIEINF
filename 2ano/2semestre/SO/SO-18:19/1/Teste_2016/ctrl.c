#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

volatile sig_atomic_t print_flag = false;

void handle_alarm( int sig ) {
    print_flag = true;
}

int main(int argc , char *argv[]) {

    int r=1,w=0;
	int i=0;
	int status;
	char *cmd[1024];
	int son;

	while(r<argc){
		cmd[w] = strdup(argv[r]);
		w++;
		r++;
	}

	cmd[w] = NULL;

	
}