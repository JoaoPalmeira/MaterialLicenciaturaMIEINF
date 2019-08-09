#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

/*int encontra(int linha[], int N, int x){
	int i;

	for(i=0 ;i<N;i++){
		if(linha[i]==x){
			return 1;
		}
	}
	return 0;
}

void ex6(int valor){

	int N=10;
	int M=1000;
	int i,j;
	int status;
	int m [N][M];
	int filho;

	for(i=0;i<N;i++){
		for(j=0;j<M;j++){
			m[i][j] = rand()%15;
		}
	}

	for(i=0;i<N;i++){

		if(fork()==0){
			int res = encontra(m[i],M,valor);
			_exit(res);
		}
	}

	int flag = 1;

	for(i=0;i<N && flag;i++){
		
		filho = wait(&status);

		if(WEXITSTATUS(status)){
			printf("O filho %d encontrou o valor %d\n",filho,valor);
			flag=0;
		}
	}
}

int main(int argc, char const *argv[])
{
	int valor = atoi(argv[1]);
	ex6(valor);
	return 0;
}*/



int main(int argc, char const *argv[]){
	if(argc<0){
		printf("No program arguments\n");
		exit(-1);
	}
	pid_t pid;
	int needle = atoi(argv[1]);
	int rows = 10;
	int cols = 10000;
	int rand_max = 10000;
	int status;
	int **matrix;

	//Alocate and populate matrix with random numbers.
	printf("alocating numbers from 0 to %d\n", rand_max);
	matrix = (int **) malloc(sizeof(int *) * rows);

	for(int i = 0; i < rows; i++){
		matrix[i] = (int*) malloc(sizeof(int) * cols);
		for(int j = 0; j < cols; j++){
			matrix[i][j] = rand() % rand_max;
		}
	}
	for(int i = 0; i < rows; i++){
		if((pid = fork())==0){
			printf("pid %d pai%d\n",getpid(),i);

			//start searching for the given number in row #1
			for(int j = 0; j < cols; j++){
				if(matrix[i][j]==needle){
					_exit(i);
				}
			}
			_exit(-1);
		}
	}
	for(int i = 0; i < rows; i++){
		pid_t terminated_pid= wait(&status);
		if(WIFEXITED(status)){
			if(WIFEXITED(status)<255){
				printf("[pai] process %d exited. exit code: %d\n",terminated_pid, WEXITSTATUS(status));
			}
			else{
				printf("[pai] process %d exited.\n", terminated_pid);
			}
		}
		else{
			printf("[pai] process %d exited. something goes wrong\n", terminated_pid);
		}
	}
	return 0;

}
