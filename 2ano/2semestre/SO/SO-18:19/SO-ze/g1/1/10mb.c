#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <unistd.h>
#include <fcntl.h>
int main(int argc, char const *argv[])
{
	int fd = open(argv[1],O_CREAT | O_WRONLY, 0777);
	int i = 0;
	char* buf = "aaaaaaaa";
	while(i<1250) {write(fd,buf,8); i++;}
	return 0;
}