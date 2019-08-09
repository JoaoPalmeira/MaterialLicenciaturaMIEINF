#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>


int main(int argc, char** argv){
	
	char* buf;
	int N;
	int n;
	if(argc!=2){
		printf("Preciso mais um!\n");
		return 1;
}
	N=atoi(argv[1]);
	buf=malloc(N);

	while(1){
		//0=stdin
		n=read(0, buf, N);
		if(n<=0) return 0;
 		//1=stdout
		write(1, buf, n);
	}
}
