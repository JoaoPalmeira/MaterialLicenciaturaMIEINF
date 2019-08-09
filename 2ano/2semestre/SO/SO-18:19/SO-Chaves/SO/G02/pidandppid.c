/* SO2 - Gestão de Processos EX1 */

#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>


int main() {
	
	printf("pID: %d\nPpID: %d\n***\n", getpid(),getppid());
	return 1;
}