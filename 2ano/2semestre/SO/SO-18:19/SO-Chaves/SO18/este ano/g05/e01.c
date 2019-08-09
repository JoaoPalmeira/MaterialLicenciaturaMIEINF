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
		sleep(5);
		n=read(pd[0],buf,256);
		write(1,buf,n);
		close(pd[0]);
		exit(1);
	}
	else{
		close(pd[0]);
		write(pd[1],"oi manos\n",10);
		close(pd[1]);
		wait(NULL);
	}
	return 0;
}
