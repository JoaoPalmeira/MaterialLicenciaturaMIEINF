#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>



int main(){

	int i = 1;
	int id,sid;	
	int status;
	
	while(i<10)
	  {
		id= fork();
		if(id!=0){
			printf("Eu sou o %d, e tive um filho que é o %d\n",getpid(),id);
			sid = wait(&status);
			printf("O meu %d morreu!!!!!!\n",sid);
			_exit(0);
				  }	
		i++;
	  }	
	_exit(0);
}
