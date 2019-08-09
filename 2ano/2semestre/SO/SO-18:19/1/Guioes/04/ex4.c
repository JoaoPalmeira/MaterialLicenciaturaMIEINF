#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>


int main(int argc,char** argv){


	int i=1,fabrir=-1,fsaida=-1;

	if(argc<=1){

		printf("Usage: %s [-i fich_entrada] [-o fich_saida] comando arg1 arg2 ...",argv[0]);

		return 1;

	}

	if(strcmp(argv[i],"-i")==0){

			fabrir = open(argv[i+1],O_RDONLY );

			if(dup2(fabrir,0)<0){

				printf("Erro a redirecionar o stdin para %s\n",argv[i+1]);
				return 1;

			}

			i+=2;


		}

		if(strcmp(argv[i],"-o")==0){

			fsaida = open(argv[i+1],O_CREAT | O_TRUNC | O_WRONLY, S_IWUSR | S_IRUSR );

			if(dup2(fsaida,1)<0){

				printf("Erro a redirecionar o stdout para %s\n",argv[i+1]);
				return 1;

			}

			i+=2;


		}

		execvp(argv[i],&argv[i]);



		close(fsaida);
		close(fabrir);

}