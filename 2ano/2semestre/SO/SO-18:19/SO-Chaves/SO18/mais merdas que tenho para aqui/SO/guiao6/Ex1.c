#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

void ex1()
{
	mkfifo("fifo",0777);
}

void ex2()
{
	char c;
	int saidaPipeNome = open("fifo",O_RDONLY);
	while(read(0,&c,1))
		write(saidaPipeNome,&c,1);
}

void ex3()
{
	char c;
	int saidaPipeNome = open("fifo",O_RDONLY);
	while(read(saidaPipeNome,&c,1))
		write(1,&c,1);
}

int main()
{
	ex1();
	ex2();
	ex3();
}