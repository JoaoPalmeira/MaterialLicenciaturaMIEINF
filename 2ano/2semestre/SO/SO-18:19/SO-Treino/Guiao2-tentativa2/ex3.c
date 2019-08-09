#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	pid_t pid;
	int i;
	int status;

	for(i=1; i<=10; i++){
		if((pid=fork())==0){
			printf("Processo: %d, pid %d\n", i, getpid() );
			_exit(i);
		}
		else{
			pid_t terminated_pid = wait(&status);
			printf("[pai] process %d exited. exit code: %d\n", terminated_pid, WEXITSTATUS(status));
		}
		//sleep(0);
	}
	return 0;
}