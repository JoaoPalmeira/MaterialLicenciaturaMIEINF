#include <unistd.h>
#include <fcntl.h>

int main(int argc, char argv[]){
	int fich=open("10mb.dat",O_RDONLY|O_WRONLY|O_CREAT,0777);
	int i;
	for(i=0;i<1024;i++)
		write(fich,"a",1); 
}

