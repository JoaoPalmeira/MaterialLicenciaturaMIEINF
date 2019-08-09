#include<unistd.h>
#include<fcntl.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>



int main(){


int fd[2];
	pipe(fd);
	int status;

	if(fork()!=0) //processo pai
	{
		// aqui vou fazer read até receber um EoF ==0 ou NULL
		int r;
		char buf[32];
		
		close (fd[0]);
		while((r=read(0,buf,32)) > 0) // possivel problema para contar strings 
			write(fd[1],buf,r);

		close (fd[1]);	
		
		wait(&status);
		_exit(0);

	}
	else // processo filho
	{
	 	// vou redirecionar o stdinput para a leitura do pipe
		close(fd[1]);
		dup2(fd[0],0);
		
		// ele recebe até receber um EoF
		execlp("wc","wc",NULL);	


		// executo o wc que vai receber cenas do stdinput, ou seja, neste caso do que está a vir do pipe
	
	}



}
