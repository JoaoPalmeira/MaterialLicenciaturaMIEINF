/* SO2 - Gestão de Processos */

#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main() {
	//printf("pID: %d\nPpID: %d\n***\n", getpid(),getppid());
	if(fork()==0){
		printf("pID: %d\nPpID: %d\n***\n", getpid(),getppid());
	}
	//wait(NULL);
	printf("pID: %d\nPpID: %d\n***\n", getpid(),getppid());
	return 1;
}
