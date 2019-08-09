#include<unistd.h>
#include<stdio.h>

int main(int argc,char * argv[])
{
	int i;
	printf("%d\n",argc);
	for(i=0;i<argc;i++)
		printf("argv[%d] = %s\n",i,argv[i]);
	if(argc > 1)
	execvp(argv[1],&argv[1]);



}
