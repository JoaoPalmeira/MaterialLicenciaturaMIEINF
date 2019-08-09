#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc, char const argv[]) {
	
	if(argc<=0) perror("Invalid N-Args");
	char ch;

	while (read(0,&ch,argc)) // ler char 1 a 1
		write(1,&ch,argc);
	return 0;
}
