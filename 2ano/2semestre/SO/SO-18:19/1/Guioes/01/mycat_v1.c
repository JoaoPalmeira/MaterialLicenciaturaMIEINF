#include <unistd.h>   /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>    /* O_RDONLY, O_WRONLY, O_CREAT, O_* */
#include <stdio.h>

int main(int argc, char const *argv[])
{
	ssize_t n;
	char c;

	while( (n=read(0,&c,1))>0){
		write(0,&c,1);
	}

	if(n==-1){
		write(0,"Erro ao ler do stdin\n",22);
	}

	return 0;
}