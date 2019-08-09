#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

/*int main(int argc, char const *argv[])
{
	pid_t pid;
	int i=0;

	if((pid=fork())==0){
		//caso seja orfão
		i++;
		printf("Filho: %d\n", i);
		printf("Pid do filho: %d\n", getpid());
		printf("Pid do pai: %d\n", getppid());
		_exit(i);
	}
	else{
		printf("Pid do pai: %d\n", getpid());
		printf("Pid do pai do pai: %d\n", getppid());
		printf("Pid do filho: %d\n", pid);
		i--;
		printf("Pai: %d\n", i);
	}
}*/

int main(){
	pid_t  pid;
	pid = fork();
	if(pid == 0){
		printf("Sou o filho: pid = %d, ppid = %d\n", getpid(),getppid());	
		return -1;
	}
	if(pid == -1){
		perror("fork");
		return -1;
	}
	else{
		printf("Sou o Pai: pid = %d, ppid = %d, filho = %d\n", getpid(),getppid(),pid);
	}
	return 0;
}