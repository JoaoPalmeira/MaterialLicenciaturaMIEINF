#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int pp[2],n;
	char buf[1024];
	pipe(pp);
	if(pipe(pp)<0)
		_exit(-1);
	if(fork()==0)
	{
		close(pp[1]);
		n=read(pp[0],buf,256);
		write(1,buf,n);
		close(pp[0]);
		_exit(1);
	}
	else
	{
		close(pp[0]);
		sleep(5);
		write(pp[1],"xau\n",4);
		close(pp[1]);
		wait(NULL);
	}
	return 0;
}