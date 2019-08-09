#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/wait.h>
int main(){
	int pd[2],n;
	char buf[256];
	pipe(pd);
	if(pipe(pd)<0) exit(-1);
	if(fork()==0){
		close(pd[1]);
		while(read(pd[0],&buf,1))
			write(1,&buf,1);
		close(pd[0]);
		exit(1);
	}
	else{
		close(pd[0]);
		while(read(0,&buf,1))
			write(pd[1],&buf,1);
		close(pd[1]);
	}
	return 0;
}
