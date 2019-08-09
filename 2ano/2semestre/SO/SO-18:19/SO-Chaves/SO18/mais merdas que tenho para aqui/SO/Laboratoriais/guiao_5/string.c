#include<unistd.h>
#include<sys/types.h>
#include<sys/uio.h>

// Este só funciona sem closes porque o 0 faz sentinela


int main(){

	int fd[2];
	char ch;

	pipe(fd);

	if(fork()==0)
		write(fd[1],"abc",4);
								// experimentar ler 3 ao invés de 4
	else							// retirar condição ch!=0
								// não vai terminar, isto acontece pois o filho escreveu e terminou, fez close dos escritores todos
									
	{
		while( read(fd[0],&ch,1) > 0 && ch!=0)
			write(1,&ch,1);				// isto nao termina pois o pai ainda tem acesso ao escritor de escrita, o anterior fecha por causa da sentinela

	}

}
