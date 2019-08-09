#include<stdio.h>
#include<unistd.h>

int main(int argc,char * argv[])
{
	printf("me\n");
	//printf("jaime\n");
	execvp(argv[0],argv);


}
