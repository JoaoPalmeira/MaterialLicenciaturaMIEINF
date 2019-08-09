#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>


/*int c = 4;
int l = 10000; 

int main(int argc, char* argv[]){
	int Mat[c][l];
	int i,j,status;

	for(i = 0; i<c;i++){
		for(j = 0;j<l;j++){
			Mat[i][j] = -1;
		}
	}

	Mat[1][5000] = Mat[2][1000] = Mat[0][9999] = Mat[2][5] = 7;
	int x = atoi(argv[1]);
	pid_t filhos[c];

	for(i = 0; i < c; i++){
		filhos[i] = fork();
		if(filhos[i] == -1){
			perror("Não consegui criar o filho");
		}

		if(filhos[i] == 0){
			for(j = 0; j<l;j++){
				if(Mat[i][j] == x){
					printf("Encontrei na linha %d e coluna %d o numero %d\n", i,j, x);
				}
			}
			_exit(i);
		}
	}

	for(i = 0; i< c;i++){
		waitpid(filhos[i],&status,0);
		if(WIFEXITED(status)){
			printf("O filho acabou com %d\n", WEXITSTATUS(&status));
		}
	}
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
	int pids[rows];

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
			//printf("pid %d pai%d\n",getpid(),i);

			//start searching for the given number in row #1
			for(int j = 0; j < cols; j++){
				if(matrix[i][j]==needle){
					printf("pid %d pai%d\n",getpid(),i);
					_exit(i);
				}
			}
			_exit(-1);
		}
		else{
			pids[i]=pid;
			//printf("pid: %d\n", pids[i]);
		}
	}
}