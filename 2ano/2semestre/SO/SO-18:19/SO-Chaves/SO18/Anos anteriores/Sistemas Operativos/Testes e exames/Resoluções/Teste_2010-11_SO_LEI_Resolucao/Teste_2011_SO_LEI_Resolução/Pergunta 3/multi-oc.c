#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>



int main (int argc , char**argv){
	int i;
	for(i=2;i<argc;i++){
		if(!fork()){
			int ficheiro = open(argv[i],RDONLY);
			int pd[2];
			pipe(pd);
			if(!fork()){
				dup2(ficheiro,0);
				dup2(pd[1],1);
				close(pd[0]);
				execlp("oc","oc",argv[1],NULL);
				close(ficheiro);
				close(pd[1]);
				exit(EXIT_FAILURE);
			}
			else{
				close(ficheiro);
				close(pd[1]);
				char buf[20];
				read(pd[0],buf,20);
				wait(0);
				printf("%s: %s", argv[i],buf);
				close(pd[0]);
				exit(EXIT_SUCCESS);
			}
		}
	}
	return 1;
}
