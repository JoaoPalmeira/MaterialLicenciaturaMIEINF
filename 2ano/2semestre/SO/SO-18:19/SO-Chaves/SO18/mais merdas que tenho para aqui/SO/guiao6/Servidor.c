#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
	char buf[30];
	mkfifo("fifoex2",0777);
	int saidaPipeNome=open("fifoex2", O_RDONLY);
	int fpLog =  open("./log", O_CREAT | O_WRONLY | O_TRUNC, 0666);
	while(read(saidaPipeNome, buf, 1)) 
		write(fpLog, buf, 1);
	return 0;
}