#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
#include<stdio.h>




int main(int argc, char* argv[]){
	int status=0;
	if(argc>1){
		int pid = fork();
		if(!pid)
		execvp(argv[1],&argv[1]);
		_exit(1);}
		
	else wait(&status);
	
	_exit(status);


}



