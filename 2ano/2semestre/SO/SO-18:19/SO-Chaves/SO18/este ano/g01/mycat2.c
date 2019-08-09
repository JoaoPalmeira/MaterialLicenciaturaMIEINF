#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char const *argv[]){
	if(argc<2) printf("Erro, falta de argumentos!\n");
	else{
		int n=atoi(argv[1]);
		void* c=malloc(n);
		while(read(0,c,n)>0){
			write(1,c,n);
			free(c);
			c=malloc(n);
		}

	}
	return 0;
}

