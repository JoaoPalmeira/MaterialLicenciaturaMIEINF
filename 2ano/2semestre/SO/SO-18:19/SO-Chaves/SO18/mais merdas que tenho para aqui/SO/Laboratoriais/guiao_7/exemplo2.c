#include<signal.h>
#include<sys/types.h>
#include <stdio.h>
#include <unistd.h>



int var =0;

void hand1(int s)
{
	var ++;
	if(s == SIGUSR2) var ++;




}


void hand2(int s)
{
	var--;

}


int main(){


	signal(SIGUSR1,hand1);
	signal(SIGUSR2,hand1);
	signal(SIGINT,hand2);
	
	printf("My pid is %d\n",getpid());
	while(1)
	{
		pause();
		printf("%d\n",var);
		}


}
