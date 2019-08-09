#include<unistd.h>

int main()
{

	int fd[2];
	char buf[32];
	
	pipe(fd);

	
	if(fork()==0)
		{
			write(fd[1],"abc",3);
			

		}
	else
		{
			read(fd[0],buf,3); // neste caso, se ele não tivesse o que ler ia servir como sincronização, ia fazer parar o read.
			write(1,buf,3);

		}

}
