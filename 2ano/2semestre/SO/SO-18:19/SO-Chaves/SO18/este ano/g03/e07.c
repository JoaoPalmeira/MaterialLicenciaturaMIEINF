#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <string.h>
int main(int argc, char *argv[]){
	int n=6;
	char *c=malloc(n);
	while(read(1,c,n)>0){
		printf("Comando: %s\n");
		if(fork()==0){
			char *arg[3];
			int narg=0;
			char *token;
			while((token=strsep(&c, " "))){
				arg[narg]=token;
				narg++;
			}
			arg[narg]='\0';
			int i=0;
			for(i=0;i<narg;i++) printf("arg[%d]: %s\n",i,arg[i]);
			execv(arg[0],&arg[0]);
			exit(0);
		}
		else 
			wait(0);
	}
	return 0;
}
