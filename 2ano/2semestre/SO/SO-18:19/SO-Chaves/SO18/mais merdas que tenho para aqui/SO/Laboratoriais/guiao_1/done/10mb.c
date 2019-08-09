#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>


int main(int argc,char* argv[])
	{	
		char buffer[1024];
		int i,newFile;
		
		if(argc>1)
		newFile = open(argv[1],O_WRONLY | O_CREAT | O_TRUNC,0666); // este 0666 dá premissão rw-rw-rw em octal
		else 
		newFile = open("resultado.txt",O_WRONLY | O_CREAT | O_TRUNC,0666); // o Trunc o que faz é contrário do APPEND, começa a colar desde o inicio

	
		for(i=0;i<1024;i++)
		buffer[i] = 'a';

		
		for(i=0;i<1024;i++)
		write(newFile,buffer,1024);
	

	return 1;
	}
