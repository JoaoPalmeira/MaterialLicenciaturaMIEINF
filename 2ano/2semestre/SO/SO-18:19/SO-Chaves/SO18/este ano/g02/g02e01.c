#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
int main(int argc, const char *argv[]){
	printf("Filho: %d\nPai: %d\n",getpid(),getppid());
	return 0;
}
