#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	int pd1[2];
	int pd2[2];
	int pd3[2];
	char c;
	int status;

	char* cmd1[5] = {"grep","-v","^#","/etc/passwd",NULL};
	char* cmd2[4] = {"cut","-f7","-d:",NULL};
	char* cmd3[2] = {"uniq",NULL};
	char* cmd4[3] = {"wc","-l",NULL};

	if(pipe(pd1)<0){
		printf("Deu erro ao criar pipe 1\n");
	}

	if(pipe(pd2)<0){
		printf("Deu erro ao criar pipe 2\n");
	}

	if(pipe(pd3)<0){
		printf("Deu erro ao criar pipe 3\n");
	}


	// 1 comando

	if(fork()==0){
		close(pd1[0]);
		dup2(pd1[1],1);
		execvp(cmd1[0],cmd1);
	}
	else{
		wait(&status);
		close(pd1[1]);
	}

	// 2 comando

	if(fork()==0){
		close(pd1[1]);     // fechar o write do pipe pd1
		close(pd2[0]);     // fechar o read do pipe pd2
		dup2(pd2[1],1);    // escrever no pipe pd2
		dup2(pd1[0],0);    // ler do pipe pd1
		execvp(cmd2[0],cmd2);
	}
	else{
		wait(&status);
		close(pd1[0]);   // fechar o read do pd1 (nao sei vai usar mais)
		close(pd2[1]);
	}

	// 3 comando

	if(fork()==0){
		close(pd2[1]);     // fechar o write do pipe pd2
		close(pd3[0]);
		dup2(pd3[1],1);
		dup2(pd2[0],0);    // ler do pipe pd2
		execvp(cmd3[0],cmd3);
	}
	else{
		wait(&status);
		close(pd2[0]);  // fechar o read do pd2  (que nao se vai usar mais)
		close(pd3[1]);
	}

	// 4 comando

	if(fork()==0){
		close(pd3[1]);
		dup2(pd3[0],0);
		execvp(cmd4[0],cmd4);
	}
	else{
		wait(&status);
		close(pd3[0]);
	}

	return 0;
}











