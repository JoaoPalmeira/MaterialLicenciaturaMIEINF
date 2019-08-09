#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
int main(){
	printf("Vai executar em execlp\n");

	if(fork()==0)
		execlp("ls","ls","-l",NULL);


	wait(0);
	printf("agora ja deve aparecer\n");
}
