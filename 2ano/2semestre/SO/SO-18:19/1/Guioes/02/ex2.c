#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	pid_t filho;
	int status;

	if(fork()==0){
		printf("[PAI] O meu pai é %d\n",getpid());      //Imprimir o identificador do pai
		printf("[PAI] Eu sou o filho %d\n",getppid());  //Imprimir o proprio indentificador
		filho = wait(&status);
		printf("Eu sou o filho: %d\n",filho);
	}
	else{ 
		printf("[FILHO] O meu pai é %d\n",getppid());   //Imprimir o identifacor do pai
		printf("[FILHO] Eu sou o filho %d\n",getpid()); //Imprimir o seu proprio id 
	}

	return 0;
}