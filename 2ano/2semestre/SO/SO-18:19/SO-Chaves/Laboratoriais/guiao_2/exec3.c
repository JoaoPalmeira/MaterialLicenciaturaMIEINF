#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>





int main(int argc,char* argv[])
{


	int c=1;
	int pid;
	int status;
	while(c<11){
	     pid=fork();
			if(!pid){
			  printf("\nO meu pid é %d, e o pid do meu PAI é %d\n\n",getpid(),getppid());	
			 _exit(c);	
			}
			c++;	
			

		   }

	
	printf("O PAPA vai a caminho!!!!\n\n\n");

	int r;
	c=1;
	while(c<11){
	r=wait(&status);
	printf("PAI: O %d filho a morrer foi o filhoID:%d\n",c,r); 
	c++;
	}	


	_exit(0);
}

