#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	if(argc!=2)
		perror("numero de argumentos invalidos, ./Ex2 [nome do ficheiro]\n");
	int fp=open(argv[1], O_RDONLY | O_WRONLY | O_CREAT,0666);
	if(fp==-1)
	{
		perror("Não abriu o ficheiro");
		return 0;
	}
	char ch='a';
	long bytes=1000*10*1000;
	for(;bytes;bytes--)
		if(write(fp,&ch,1)==0)
			perror("erro no read\n");
	close(fp);
	return 0;
}