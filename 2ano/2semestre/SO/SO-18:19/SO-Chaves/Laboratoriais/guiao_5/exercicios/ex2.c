#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
// não estava a aparecer á espera porque estava a correr o filho , ver processos ativos ps

int main(){


	int fd[2];
	
	pipe(fd);
		
	if(fork()==0) // filho
	{
//		close(fd[0]);
		write(fd[1],"apanha",10);
	//	sleep(5);		
//		close(fd[1]);
	}
	else{ // pai
		char ch;
		close(fd[1]); // o processo só termina quando esta linha está no código
		while(read(fd[0],&ch,1) > 0) write(1,&ch,1); 


	    } 	


}
