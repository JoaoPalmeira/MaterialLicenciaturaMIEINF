#include <unistd.h>
#include <fcntl.h>

int main(int argc, char const *argv[])
{
	char c;
	while(read(0,&c,1)) // lê do stdin (0)
		write(1,&c,1); // escreve no stdout (1)
	return 0;
}