#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char const *argv[]){
	void* buf=malloc(1);
	int n=atoi(argv[1]),m=0;
	int fildes[2];
	pipe(fildes);
	int fildes2[2];
	pipe(fildes2);
	for(int i=0;i<n;i++){
		if(fork()==0){
			if(fork()==0){
				close(fildes[1]);
				close(fildes[0]);
				close(fildes2[1]);
				dup2(fildes2[0],0);
				close(fildes2[0]);
				execlp("wc","wc",NULL);
			}else{
				close(fildes[1]);
				dup2(fildes[0],0);
				close(fildes[0]);
				close(fildes2[0]);
				dup2(fildes2[1],1);
				close(fildes2[1]);
				execlp("uniq","uniq",NULL);
			}
		}
	}
	close(fildes2[1]);
	close(fildes2[0]);
	close(fildes[0]);
	while((m=read(0,buf,1024))){
		write(fildes[1],buf,m);
	}
	close(fildes[1]);
	int status=-1;
	for(int i=0;i<n;i++){
		wait(&status);
		if(status==1){
			kill(-1,SIGKILL);
			//podia-se fazer um handler normal, mas sigkill a 0 termina
			//todos os processos que podem ser terminados 
			//(versão mais preguiçosa, não fazer assim no teste)
		}
	}
	return 0;
}