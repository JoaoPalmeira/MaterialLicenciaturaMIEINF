#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <unistd.h>
#include <fcntl.h>
int main(int argc, char const *argv[])
{
	 
	char buf;
	while(read(0,&buf,1) > 0 && buf !='\n'){
		printf("Onde anda o barra n \n" );
		//write(1,&buf,1);
	}
}