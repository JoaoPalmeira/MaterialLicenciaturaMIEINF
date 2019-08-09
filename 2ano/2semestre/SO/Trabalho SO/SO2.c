#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>

#define bufS 512

char** parser(char* str){
  //char* campos[4];
  int i=0;
  char* pch = strtok (str," : ");
    while (pch != NULL)
    {
      &campos[i]=strdup(&pch[i]);
      i++;

      pch = strtok (NULL, " : ");

    }
    return campos;
}

void consts(char* value){
	char *buffer=(char *)malloc(bufS*sizeof(char));
	size_t bytes;
	while((bytes=read(0, buffer, bufS))>0){
			buffer[strlen(buffer)-1]='\0';
			strcat(buffer,":");
			strcat(buffer,value);
			strcat(buffer,"\n");
			write(1, buffer, bytes+3);
			free(buffer);
			buffer=(char *)malloc(bufS*sizeof(char));
	}
}

void filtro(int coluna, char* operador, int operando){
  	char *buffer=(char *)malloc(bufS*sizeof(char));
  	size_t bytes;
  	char *res;
  	printf("operandor: %s\n", operador);
  	while((bytes=read(0, buffer, bufS))>0){
  	res = parser(buffer);
    char* colunas = strdup(res);
    if(strcmp(operador, "=")==0){
     	if(atoi(&colunas[coluna-1])==atoi(&colunas[operando-1])) write(1,buffer, bytes);
      	printf("merda2\n");
    }
    if(strcmp(operador, ">=")==0){
    	printf("merda3\n");
     	if(atoi(&colunas[coluna])>=atoi(&colunas[operando])) write(1,buffer, bytes);
    }
    if(strcmp(operador, "<=")==0){
    	printf("merda4\n");
      	if(atoi(&colunas[coluna])<=atoi(&colunas[operando])) write(1,buffer, bytes);
    }
    if(strcmp(operador, ">")==0){
    	printf("merda5\n");
      	if(atoi(&colunas[coluna])>atoi(&colunas[operando])) write(1,buffer, bytes);
    }
    if(strcmp(operador, "<")==0){
    	printf("merda6\n");
      	if(atoi(&colunas[coluna])<atoi(&colunas[operando])) write(1,buffer, bytes);
    }
    if(strcmp(operador, "!=")==0){
    	printf("merda7\n");
      	if(atoi(&colunas[coluna])!=atoi(&colunas[operando])) write(1,buffer, bytes);
    }
    free(buffer);
    free(colunas);
    buffer=(char *)malloc(bufS*sizeof(char));
    colunas=(char *)malloc(bufS*sizeof(char));
  }
}

int main(int argc,char *argv[]){
	int coluna;
	char* operacao;
	int operando;
	if (strcmp(argv[1],"const")==0) {
		consts(argv[2]);
	}

	if (strcmp(argv[1],"filter")==0) {
		coluna=atoi(argv[2]);
		operacao=argv[3];
		operando=atoi(argv[4]);
		filtro(coluna,operacao,operando);
	}

	return 0;
}