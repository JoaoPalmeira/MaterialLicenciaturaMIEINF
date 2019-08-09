#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>


int main (int argc , char**argv){
 
	int **pipes;
	int i;


//PARSING

	int cmdcount = 1;
	int startcmd[argc];
	int pipeoccur = 1;
	for(i = 0; i<argc ; i++){
		if(pipeoccur){
			startcmd[cmdcount-1] = i;
			pipeoccur = 0;
		}else if(strcmp(argv[i],"|")){
			cmdcount++;
			argv[i] = NULL;
			pipeoccur = 1;
		}
	}

	//VERIFICA SE SO TEM 1 COMANDO PARA CORRER
	if(cmdcount-1 == 0){
		if(!fork()){
			execvp(argv[0],argv);
			exit(EXIT_FAILURE);
		} 
		exit(EXIT_SUCESS);
	}

//____________

//CRIAR PIPES
	pipes = malloc((cmdcount-1)*sizeof(int*));
	for(i=0;i<(cmdcount-1);i++){
		pipes[i] = malloc (2*sizeof(int));
		pipe(pipes[i]);
	}

//___________


//CORE 
	//CORRE O PRIMEIRO COMANDO
	if(!fork()){
		dup2(pipes[0][1],1);
		close(pipes[0][0]);
		execvp(argv[0],argv);
		close(pipes[0][1]);
		exit(EXIT_FAILURE);
	}
	close(pipes[0][1]);
	//CORRE OS INTERMEDIOS
	for(i = 1;i<cmdcount-1;i++){
		if(!fork()){
			dup2(pipes[i-1][0],0);
			dup2(pipes[i][1],1);
			close(pipes[i-1][1]);
			close(pipes[i][0]);
			execvp(argv[startcmd[i]],argv+startcmd[i]);
			close(pipes[i-1][0]);
			close(pipes[i][1]);
			exit(EXIT_FAILURE);
		}
		close(pipes[i-1][0]);
		close(pipes[i][1]);
	}
	//CORRE O ULTIMO

	if(!fork()){
			dup2(pipes[i-1][0],0);
			close(pipes[i-1][1]);
			execvp(argv[startcmd[i]],argv+startcmd[i]);
			close(pipes[i-1][0]);
			exit(EXIT_FAILURE);
	}
	for(i=0;i<commandcount;i++){wait(0);}
	return 1;
}