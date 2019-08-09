#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char * argv[]){
	char byte;
	int N = atoi(argv[1]);
	int nbt;
	if(argc>1){

		while((nbt=read(0,&byte, N)) != 0){
			write(1,&byte, nbt);
		}
		return 0;
	}



}