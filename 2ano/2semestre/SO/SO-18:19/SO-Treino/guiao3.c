#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */

//GUIAO 3
//EX 1
int main(int argc, char* argv[]){
	execlp("ls","ls","-l",NULL);
	return 0;
}

//EX 2
int int main(int argc, char const *argv[]){
	pid_t pid = fork();
	if(pid==0){
		execlp("ls","ls","-l",NULL);
	}
	else{
		perror("Erro no fork!");
	}

	wait(NULL);
	printf("LS feito\n");
	return 0;
}

//EX 3
int main(int argc, char const *argv[]){
	for(int i = 0; i < argc; i++){
		printf("Comando: %s\n",argv[i]);
	}
	return 0;
}

//EX 4
int main(int argc, char const *argv[]){
	execv(argv[1],&argv[1]);
	return 0;
}

//EX 5
int main(int argc, char const *argv[]){
	int i = 1;
	while(i!=argc){
		if(fork()==0){
			execv(argv[1],&argv[1]);
		}
		_exit(0);
		i++;
	}
	return 0;
}

//EX 6
#define MAX 100

int my_system(char* comando){
		
	char* token = NULL;
	char* argv[MAX];
	int i,status;
	pid_t pid;
	char* copia = strdup(comando);
	for(i = 0; i != MAX-1 && (token = strsep(&copia," ")) != NULL; i++)	
		argv[i] = strdup(token);
	argv[i] = NULL;
	if((pid = fork()) == 0){
		execvp(argv[0],argv);
		_exit(1);
	}
	waitpid(pid,&status,0);
	free(copia);
	return WIFEXITED(status) ? WEXITSTATUS(status) : -1;
}

int main(int argc,char* argv[]){
	my_system("ls -l ex5.c");
	return 0;
}





















