#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>


//GUIAO 2
//EX1
int main(int argc, char const *argv[]){
	pid_t pid;
	if((pid=fork())==0){
		printf("FILHINHO: %d\n",getpid());
		_exit(0);
	}
	else{
		printf("PAIZAO: %d\n", getppid());
	}
	return 0;
}

//EX2
int main(int argc, char const *argv[]){
	pid_t pid = fork();
	if (pid == 0){
		printf("SOU O FILHO COM O PID %d DO PAI %d\n", getpid(), getppid());
		return -1;
	}
	else if(pid == -1){
		perror("fork");
		return -1;
	}
	else{
		printf("SOU O PAI COM O PID %d COM O PAI %d E COM O FILHO %d\n", getpid(), getppid(), pid);
	}
	return 0;
}

//EX3
int main(int argc, char const *argv[]){
	pid_t pid;
	int status;
	int i;
	for(i=0; i<=10; i++){
		if((pid=fork())==0){
			printf("Processo: %d, pid: \n",i,getpid());
			_exit(i);
		}
		else{
			pid_t terminated_pid = wait(&status);
			printf("[PAI] process %d exited. Exit code: %d\n", terminated_pid, WEXITSTATUS(status));
		}
	}	
	return 0;
}

//EX4
int main(int argc, char const *argv[]){
	int pid;
	int status;
	for (int i = 0; i <=10 ; i++){
		if ((pid=fork())==0){
			printf("Processo: %d, pid: \n",i,getpid());
			_exit(i);
		}
	}
	for (int i = 0; i <=10 ; i++){
		pid_t terminated_pid = wait(&status);
		if(WIFEXITED(status)){
			printf("[PAI] process %d exited. Exit code: %d\n", terminated_pid, WEXITSTATUS(status));
		}
		else{
			printf("[PAI] process %d exited.\n", terminated_pid);
		}
	}
	return 0;
}

//EX5
int main(int argc, char const *argv[]){
	int i, status;
	pid_t pid;
	for (int i = 0; i <=10 ; i++){
		if ((pid=fork())==0){
			printf("Processo: %d, pid: \n",i,getpid());
		}
		else{
			pid_t terminated_pid = wait(&status);
			if(WIFEXITED(status)){
			printf("[PAI] process %d exited. Exit code: %d\n", terminated_pid, WEXITSTATUS(status));
			printf("[1 - Pid do Pai] = %d\n", getppid());
			}
			else{
			printf("[PAI] process %d exited.\n", terminated_pid);
			printf("[2 - Pid do Pai] = %d\n", getppid());
			}
			_exit(0);
		}
	}
	printf("sai ciclo pid%d\n", getpid());
	_exit(0);
	return 0;
}

//EX6
int main(int argc, const char *argv[]){

	int res=atoi(argv[1]);
	int matrix[10000][10000];
	srand(time(NULL));
	int i,o,status;
	for(i=0;i<10000;i++)
		for(o=0;o<10000;o++)
			matrix[i][o]=rand()%10000;

	for(i=0;i<10000;i++){
		if(fork()==0){
		// search this column
			for(o=0;o<10000;o++)
				if(matrix[i][o]==res){
					printf("Encontrei o num %d na linha %d, coluna %d!\n",res,i,o);
					printf("Processo pid %d\n",getpid());
				}
			exit(i);
		}
		else{
			wait(&status);
			printf("Terminou a pesquisa na linha %d.\n",WEXITSTATUS(status));
		}
	}
	return 0;
}


































