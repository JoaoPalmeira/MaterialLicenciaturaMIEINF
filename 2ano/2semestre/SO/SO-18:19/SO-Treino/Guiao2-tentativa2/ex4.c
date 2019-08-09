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

	for( int i =1; i <=nproc; i++){
		if((pid = fork())==0){
			printf("proc %d pid: %d\n", i, getpid());
			_exit(i);
		}
	}
	for( int i =0; i <=nproc; i++){
		pid_t terminated_pid= wait(&status);
		//se filho returnou entao WIFEXITED retorna True
		//se filho retornou -1 então WIFEXITED return True e WEXITSTATUS retorna 
		//255 (a reposta so tem 8 bits)
		//-1 é representado como 255
		if(WIFEXITED(status)){
			printf("[pai] process %d exited. exit code: %d\n",terminated_pid, WEXITSTATUS(status));
		}
		else{
			printf("[pai] process %d exited.\n", terminated_pid);
		}
	}
	return 0;
}