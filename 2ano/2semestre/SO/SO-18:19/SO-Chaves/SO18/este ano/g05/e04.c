#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/wait.h>
int main(){
	int pd[2],n,status;
	char buf;
	pipe(pd);
	if(pipe(pd)<0) exit(-1);
	if(fork()==0){
		close(pd[1]);
		dup2(pd[0],0);
		close(pd[0]);
		execlp("wc","wc","-l",NULL);
//		exit(1);
	}
	else{
		close(pd[0]);
		dup2(pd[1],1);
		close(pd[1]);
		execlp("ls","ls","/etc",NULL);
	}
	wait(&status);
	return 0;
}
