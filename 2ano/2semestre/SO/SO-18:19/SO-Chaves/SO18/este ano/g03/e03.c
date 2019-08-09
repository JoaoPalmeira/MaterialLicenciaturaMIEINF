#include <unistd.h>
#include <stdio.h>

int main(int argc, const char *argv[]){
	int i;
	argv[0]="x";
	for(i=0;i<argc;i++){
		printf("argv[%d]=%s\n",i,argv[i]);
	}
	return 0;
}
