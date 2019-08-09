#include<stdio.h>
#include<unistd.h>



int main(){
	int id = getpid();
	int c = 0;	
	int f;	
	int aux;

	for(int i=10; i>0 ; i--)
			if(id == getpid()){ f=fork(); 
				
			if(f != 0){ waitpid(&aux)  }
			else{	
			printf("O meu pai é %d, eu sou %d\n",getpid(),getppid());
			
			_exit(++c);}

			}



			



}
