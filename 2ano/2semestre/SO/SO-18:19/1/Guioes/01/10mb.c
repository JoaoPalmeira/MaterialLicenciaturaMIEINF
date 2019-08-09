#include <unistd.h>   /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>    /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

int main(int argc, char const *argv[])
{
	int fdout;
	int i;
	char c = 'a';

	fdout = open(argv[1], O_CREAT | O_WRONLY , 0644);

	for(i=0;i<10*1024*1024;i++){
		write(fdout,&c,1);
	}

	close(fdout);

	return 0;
}