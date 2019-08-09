#include<stdio.h>
#include<unistd.h>


int main(){
	int id = getpid();
	int pid = getppid();



	printf("PAI: %d\n",pid);
	printf("ATUAL: %d\n",id);

}
