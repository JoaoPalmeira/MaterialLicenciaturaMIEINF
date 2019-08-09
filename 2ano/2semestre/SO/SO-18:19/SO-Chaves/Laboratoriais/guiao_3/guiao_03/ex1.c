#include<stdio.h>
#include<unistd.h>


int main(){
	printf("Antes\n");
	execlp("ls","ls","-l",NULL);
	printf("Deu algum problema!!!!\n");

_exit(0);

}
