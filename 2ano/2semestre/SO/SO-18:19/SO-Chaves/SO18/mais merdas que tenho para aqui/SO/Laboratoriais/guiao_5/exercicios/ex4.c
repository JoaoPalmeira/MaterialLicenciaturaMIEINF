#include<unistd.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>



int main(){

	int fd[2];


	pipe(fd);


	if(fork() == 0 ) // filho
	{
		close(fd[0]);
		dup2(fd[1],1);
		execlp("ls","ls","/etc",NULL);
		
	}
	else // pai
	{
	
		close(fd[1]);
		dup2(fd[0],0);
		execlp("wc","wc","-l",NULL);
	
	}


	






}
