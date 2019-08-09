#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main (int argc, char *argv[]) {
	if (argc<1) perror("invalid nargs");

	int fp = open (argv[1],O_RDONLY);
	char ch;
	while(read(fp,&ch,1))
		write(1,&ch,1);

	close(fp);
	return 0;
}