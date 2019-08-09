#include<stdlib.h>
#include<unistd.h>
#include<stdio.h>


int main(int argc,char* argv[]){
	int p;
	int status;
	if(argc > 1){

		for(int i=argc ; i!=0 ; i--) // a questão de começar pelo fim vem do facto de a chamada ao sistema execvp executar o comando que está no endereço tendo em conta que o array termina com um null
		{

				p = fork();						

				if(!p) {
				          execvp(argv[i],&argv[i]); 	
					 
				       }
				else argv[i] = NULL; // temos a memória duplicada, ou seja, o filho ficou com o array próprio dele

		} 	

		for(int i=argc-1 ; i!=0 ; i--) //este wait é só para os apanhar e não estragar a ordem dos resultados.			
		wait(0);

		
	

	}



	_exit(0);
}
