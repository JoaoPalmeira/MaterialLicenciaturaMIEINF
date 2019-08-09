#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
#include<inttypes.h>
#include<stdio.h>



int main(int argc,char * argv[])
	{
		long r,s=1024;

		if(argc >= 2) r = strtol(argv[1],0,10); // 0 10 é a base que se vai usar
	    				                // neste caso o strtol é usado para converter a string em um valor do tipo long, isto porque o argumento é sempre do tipo char* e nós precisamos de o transformar em um inteiro.

		char buff[s];

		while((r = read(0,buff,s)) > 0)
			write(1,buff,r);


	}	
