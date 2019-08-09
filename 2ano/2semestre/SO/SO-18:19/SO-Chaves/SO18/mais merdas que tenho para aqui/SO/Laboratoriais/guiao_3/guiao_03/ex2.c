#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>

int main(){
	int p;

	p = fork();
	if(!p) execlp("ls","ls","-l",NULL); 
	int status;	
	wait(&status);
	if(p!=0) // despropositado, fiz apenas para ter garantia que é o pai só
		printf("Sou o pai e o processo já morreu\n");		
_exit(1);
}
