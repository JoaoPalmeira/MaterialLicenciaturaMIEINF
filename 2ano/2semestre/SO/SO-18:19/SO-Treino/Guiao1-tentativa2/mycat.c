#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int n; 
	char* buf = (char*) malloc(sizeof(char));
	while(( n=read(0,buf,1) ) > 0){
		write(1,buf,1);
	}
}