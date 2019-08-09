#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
int main(){


	int fd = open("/etc/password",O_RDONLY);
	dup2(fd,0);
	close(fd); // não vou usar diretamente o escritor fd por isso é má prática deixa lo aberto, o redirecionamento já está feito 

	int fd2 = open("ex3_res.txt",O_WRONLY | O_CREAT |O_TRUNC ,0666); // o 0666 são as premissões
	dup2(fd2,1); // o stdoutput vai agora para o ex3_res
	close(fd2);
	
	int status;
	if(fork() == 0 )
	 // processo filho
	{				
		execlp("wc","wc",NULL); // não percebo porque 
		
	
	}
	else {
	//processo pai
	wait(&status);
	printf("Funcionou ou não???\n");
	_exit(0);
			
	}
			
	
	





}
