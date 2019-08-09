#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>



int main(){

	int fd[2];

	pipe(fd);


	
	
	if(fork() !=0) // pai
	{
	  close(fd[1]);
	  dup2(fd[0],0);
	  execlp("wc","wc",NULL);

	}
	else //filho 
	{
		int fd2[2];
		pipe(fd2);
		if(fork()!=0) //filho
		{
		  
		  close(fd2[1]);
		  dup2(fd2[0],0);
		
		  close(fd[0]);
		  dup2(fd[1],1);

		  execlp("sort","sort",NULL);
		}
		else // neto 
		{
		close(fd2[0]);  
		dup2(fd2[1],1);
		execlp("ps","ps",NULL);  
		
		}
	}



	




}
