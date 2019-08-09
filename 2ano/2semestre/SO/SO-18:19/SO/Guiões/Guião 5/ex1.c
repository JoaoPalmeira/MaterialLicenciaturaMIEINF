#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */

// int pipe(pd[2]);

int main(int argc, char* argv[]){
	int pd[2];
	pipe(pd);
	char msg1[20] = "Ola sou eu\n";
	char msg2[20] = "Toma biscoitos\n";  

	pid_t pid = fork();
	if(pid == -1){
		close(pd[1]);
		close(pd[2]);
		perror("Não consegui criar o processo filho\n");
	}
	if(pid == 0){
		// Sou o filho
		int n;
		char buffer[1024];
		close(pd[1]);
		while(1){
			n = read(pd[0],&buffer,sizeof(buffer));
			if(n <= 0){
				close(pd[0]);
				_exit(0);
				
			}			
			write(1,buffer,n);
		}
	}
	close(pd[0]);
	write(pd[1],msg1,strlen(msg1));
	sleep(5);
	write(pd[1],msg2,strlen(msg2));
	close(pd[1]);
	return 0;

}