#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <stdio.h>
int main(){
	int f=mkfifo("/tmp/tempfifo",0666);
	if(f<0){
		perror("error creatinhg fifo\n");
		exit(-1);
	}
	exit(0);
}
