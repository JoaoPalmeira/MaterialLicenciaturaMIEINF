#include <unistd.h>   
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{

	size_t n = atoi(argv[1]); // Serve para o tamanho, guardar a shit no argv[1], como no exercicio 2

	char buffer[n]; // Criar uma string, para não se ler de caratere em caratere
	if(argc!=2) return 1;

	size_t a;
	while(a=read(0,&buffer,1)>0){

		write(1,&buffer,a); // O 'a' está no fim, porque vai ser o numero de vezes que vamos ler
}


	return 0;
}