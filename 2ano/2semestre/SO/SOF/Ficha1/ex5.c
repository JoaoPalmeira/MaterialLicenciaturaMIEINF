#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

ssize_t readln(int fildes, char *buf, size_t nbyte) 
{
	int i = 0;

	while(i<nbyte-1 && 
		  read(fildes, buf+i, 1) > 0 &&
		  buf[i] != '\n') {

			i++;
	}

	if(i>=nbyte)
	buf[i] = 0;
	else
	buf[i+1] = 0;

	return i;
}


int main(int argc, char** argv) {

	int n,fd,i=1;

	char buffer[1000];
	char str[1000];

	fd = open("a", O_RDONLY);

	while( (n = readln(fd,buffer,1000)) > 0  ) {

	//readln(fd,buffer,1000);

	sprintf(str,"%5d  %s ",i++,buffer);

	puts(str);

	printf("1\n");
	}

	return 0;

}