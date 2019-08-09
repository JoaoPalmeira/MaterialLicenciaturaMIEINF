#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(){
	pid_t pid;
	int nproc = 10;
	int status;

	for( int i =0; i <=nproc; i++){
		if((pid = fork())==0){
			printf("proc %d pid: %d\n", i, getpid());
		}
		else{
			pid_t terminated_pid= wait(&status);
			if(WIFEXITED(status)){
				printf("[pai] process %d exited. exit code: %d\n",terminated_pid, WEXITSTATUS(status));
				printf("[1 - Pid do Pai] = %d\n", getppid());
			}
			else{
				printf("[pai] process %d exited.\n", terminated_pid);
				printf("[2 - Pid do Pai] = %d\n", getppid());
			}
			_exit(0);
		}
	}
	printf("sai ciclo pid%d\n", getpid());
	_exit(0);
	return 0;
}