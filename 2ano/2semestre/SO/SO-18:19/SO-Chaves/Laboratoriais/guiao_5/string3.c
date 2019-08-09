#include<unistd.h>
#include<sys/types.h>
#include<sys/uio.h>
#include<unistd.h>


int main(){

	int fd[2];
	char ch;



	pipe(fd);


	if(fork==0)
	{
		close(fd[0]);
		// fazer tudo que fiz no outro


		


	}
	else 
		close(fd[1]); // ou seja fechar tudo que não vou usar
}
