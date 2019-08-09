#include <stdio.h>
#include <unistd.h>

int main(){
	int p, i;
	
	for(i=0; i<10; i++){	
		p=fork();
		if(p==0){
		sleep(1);
		printf("Indentificador pai: %d\n", getppid());
		printf("Indentificador filho: %d\n", getpid());
		_exit(0);
		}

	}
	for(i=0; i<10; i++)
		wait(NULL);
}
