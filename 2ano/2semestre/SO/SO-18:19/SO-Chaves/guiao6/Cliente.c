#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
	char buf[30];
	int entradaPipe = open("fifoex2", O_WRONLY);
	while(read(0,buf,1)) 
		write(entradaPipe, buf, 1);
	return 0;
}