#include <stdio.h>
#include <unistd.h>

int main(){
	int p;
	
	p=fork();
	if(p==0){
	printf("Indentificador pai: %d\n", getppid());
	printf("Indentificador: %d\n", getpid());
	}
	else {
		printf("Indentificador pai: %d\n", getppid());
       		printf("Indentificador: %d\n", getpid());
		wait(NULL);
	}
	return 0;
}
