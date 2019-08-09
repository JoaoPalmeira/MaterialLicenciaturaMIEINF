#include <unistd.h>
#include <fcntl.h>

int main(int argc, char const argv[]) {
	char ch;

	while (read(0,&ch,1)) // ler char 1 a 1
		write(1,&ch,1);
	return 0;
}

