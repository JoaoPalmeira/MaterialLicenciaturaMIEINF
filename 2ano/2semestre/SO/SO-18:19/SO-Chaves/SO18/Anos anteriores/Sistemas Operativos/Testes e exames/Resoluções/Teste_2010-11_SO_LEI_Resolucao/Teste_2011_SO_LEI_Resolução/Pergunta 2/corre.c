#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>

int *pids;
int *pidstime;
int counter = 0;
int n;
int tempo;


void handler1(int coco){
	for(int i = 0 ; i<n ; i++){
		pidstime[i]++;
		if((pidstime[i]==tempo){
			kill(pids[i],SIGTERM);
			pids[i] = -1;
		}
	}
	alarm(1);
}

int main (int argc , char**argv){
	int i;
	n = atoi(argv[1]);
	tempo = atoi(argv[2]);

	pids = malloc((argc-3)*sizeof(int));
	pidstime = malloc((argc-3)*sizeof(int));

	for(i=0;i<argc-3;i++){pidstime[i]=0;}
	
	signal(SIGALRM,handler1);
	alarm(1);

	

	for(i=3;i<argv;i++){
		counter ++;
		if((pids[i-3] = fork())==0){
			execlp(argv[i],argv[i],NULL);
		}
		if(counter>=n){
			wait(0);
			counter--;
		}
	}
	while(counter>0){wait(0);counter--;}

}