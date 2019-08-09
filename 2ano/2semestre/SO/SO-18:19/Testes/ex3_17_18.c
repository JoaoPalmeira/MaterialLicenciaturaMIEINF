#include <unistd.h>     /* chamadas ao sistema: defs e decls essenciais */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>

ssize_t readln(int fd, char* buf, int size){
	int n, i = 0;
	while((n = read(fd, buf+i, size))){
		if(buf[i] == '\n'){
			return i+1;
		}
		i++;
	}
	return i;
}

int main(int argc, char const *argv[]){
	int p = atoi(argv[1]);
	int c = atoi(argv[2]);
	char buffer[512];
	char valor;
	int n, d;
	int fc[c][2];
	int fp[2];
	pipe(fp);

	for(int i = 0; i < p; i++){ //criar produtores
		if(!fork()){
			close(fp[0]);
	  		dup2(fp[1], 1);
	  		close(fp[1]);
	  		execlp("produtor", "produtor", NULL);
	  		_exit(1);
		}
	}

	for(int i = 0; i<c; i++){ //criar consumidores
		pipe(fc[i]);
		if(!fork()){
			close(fc[i][0]);
	  		dup2(fc[i][0], 0);
	  		close(fc[i][1]);
	  		execlp("consumidor", "consumidor", NULL);
	  		_exit(1);
		}
	}

	while((n = readln(fp[0], buffer, sizeof(buffer))) > 0){
		valor = buffer[0]; 
		d = atoi(&valor);
		if(d > 0 && d < c){
	  		write(fc[d][1], buffer+1, n-1);
		}
	}
	return 0;
}



