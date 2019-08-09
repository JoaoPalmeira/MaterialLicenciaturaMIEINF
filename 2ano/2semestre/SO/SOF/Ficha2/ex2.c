#include <stdio.h>
#include <unistd.h>

int main(){
	int p;
	
	p=fork();
	if(p==0){
	printf("Indentificador pai: %d\n", getppid());
	printf("Indentificador: %d\n", getpid());
	}
	else{
		sleep(10);
		printf("Indentificador filho: %d\n", p);
	}
}
