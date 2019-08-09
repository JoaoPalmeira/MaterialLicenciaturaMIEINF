#include <stdio.h>
#include <unistd.h>

int main(){
	int p, i;
	
	for(i=0; i<10; i++){	
		p=fork();
		if(p==0){
		printf("Indentificador pai: %d\n", getppid());
		printf("Indentificador filho: %d\n", getpid());
		_exit(i+1);
		}
		else{ wait(NULL);}
	}
}
