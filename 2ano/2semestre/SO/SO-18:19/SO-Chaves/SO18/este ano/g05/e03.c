#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/wait.h>
int main(){
	int pd[2],n,status;
	char buf;
	if(pipe(pd)<0) exit(-1);
	if(fork()==0){
		close(pd[1]);
		dup2(pd[0],0);
		close(pd[0]);
		execlp("wc","wc",NULL);
//		exit(1);
	}
	else{
		close(pd[0]);
		while(read(0,&buf,1) && buf!='0')
			write(pd[1],&buf,1);
		close(pd[1]);
	}
	wait(&status);
	return 0;
}
