#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

ssize_t readln(int fildes, char *buf, size_t nbyte)
{
	int i=0;
	int n;
	while(i<nbyte && (n = read(fildes, buf+i, 1))>0 && *(buf+i)!='\n')
	i++;
	*(buf+i)='\n';
	if(n<0) 
		return n;
	return i;
}

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
	char* c=malloc(n);
	int linha;
	for(linha=0;1;linha++)
	{
		printf("%5d\t",linha);
		while(readln(fp,c,n)>0)
			write(1,c,1);
	}
	write(1,c,1);
	close(fp);
	return 0;
}