#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
int main(){
	int f=open("/tmp/tempfifo",O_WRONLY);
	if(f<0){
		perror("can't write to fifo\n");
		exit(-1);
	}
	char *msgs[] = {"Hello", "my", "name", "is", "slim", "shady"};
	int i=0;
	for(;i<6;i++){
		int len=strlen(msgs[i]);
		write(f,&msgs[i],len);
	}
	return 0;
}
