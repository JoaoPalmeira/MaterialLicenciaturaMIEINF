#include<stdio.h>
#include<unistd.h>




int main(){
	int res;	
	if((res = fork()) != 0)
		printf("O filho criado foi %d\n",res);
	
	printf("Sou o %d, e o meu pai é %d\n",getpid(),getppid());


	
_exit(0);
}
