#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>

#define bufS 512

void consts(char* value){
	char *buffer=(char *)malloc(bufS*sizeof(char));
	size_t bytes;
	char* aux=NULL;
	int i;
	//value[strlen(value)-1]='\0';
	while((bytes=read(0, buffer, bufS))>0){
			int t=strlen(buffer);
			buffer[strlen(buffer)-1]='\0';
			strcat(buffer,":");
			strcat(buffer,value);
			strcat(buffer,"\n");
			//printf("%d %s\n",t,buffer);
			write(1, buffer, bytes+3);
			free(buffer);
			buffer=(char *)malloc(bufS*sizeof(char));
	}
}

int main(int argc,char *argv[]){
	if(strcmp(argv[1],"consts")==0){
		consts(argv[2]);
	}
	return 0;
}