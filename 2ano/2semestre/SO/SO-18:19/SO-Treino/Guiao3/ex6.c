#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */


#define MAX 100

// int execl(const char *path, const char *arg0, ..., NULL);
// int execlp(const char *file, const char *arg0, ..., NULL);
// int execv(const char *path, char *const argv[]);
// int execvp(const char *file, char *const argv[]);

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

/*
int my_system(char* comando){
	char* token = NULL;
	char* argv[100];
	int i,status;
	pid_t pid;
	char* copia = strdup(comando);
	for(i=0; i<100 && (token = strsep(&copia," "))!= NULL; i++ ){
		argv[i] = strdup(token);
	}
	argv[i] = NULL;
	pid = fork();
	if(pid == 0){
		execvp(argv[0],argv);
		_exit(1);
	}
	waitpid(pid,&status,0);
	free(copia);
	if(WIFEXITED(status)){
		return WEXITSTATUS(status);
	}
	return -1;
}

int main(int argc,char *argv[]){
	my_system("ls -l");
	return 0;
}*/