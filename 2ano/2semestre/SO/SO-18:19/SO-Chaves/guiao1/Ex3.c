#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int n=1;
	int fp=0;
	if(argc==3)
	{
		n=atoi(argv[1]);
		fp=open(argv[2], O_RDONLY);
	}
	if(fp==-1)
    {
        perror("Não abriu o ficheiro");
        return 0;
    }
	void* c=malloc(n);
	while(read(fp,c,n)>0)
		write(1,c,n);
	close(fp);
	return 0;
}