#include <unistd.h>   
#include <fcntl.h>

//EX.2
// o argv[1] é onde vai ser guardado, pois argv[0] é o nome do executável ( ./ex1 )

int main(int argc, char const *argv[])
{
	int i;
	if(argc!=2) return 1; // Controlo de erros, se escrevermos ./ex1 dá erro, mas se for ./ex1 ficha1 dá certo ( ou seja 2 argumentos)
	int fich = open(argv[1],O_CREAT | O_WRONLY,0640); // O_CREAT -> Serve para criar e um pipe (|) para ligar as flags, com O_WRONLY para write only
	if (fich==-1) return 1; // Outro controlo de erros relativo ao OPEN, se for -1 ir abaixo xau
	for(i=0;i<10000000;i++){ //10000000 == 10MB
		write(fich,"a",1); // Ir ocupando cada bit com o char 'a' para ocupar os 10MB
	}
	close(fich);

	return 0;
}


// No final escrever no terminal ./ex_2 10mb.txt para criar o ficheiro txt