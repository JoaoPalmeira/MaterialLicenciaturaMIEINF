#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>

#define bufS 512

char* parser(char* str){
	char *sSeparador = ":";
	char *pToken = strtok(str,sSeparador);
	int i=0;
	char *colunas = (char *)malloc(bufS*sizeof(char));
	while(1){
		if (pToken == NULL) break;
		colunas[i++]= *pToken;
		pToken = strtok(NULL, sSeparador);
	}
	return colunas;
}

void consts(char* value, char* s){
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
  	while((bytes=read(0, buffer, bufS))>0){
  	char *aux=strdup(buffer);
  	res = parser(buffer);
    char* colunas = strdup(res);
    if(strcmp(operador, "=")==0){
     	if(atoi(&colunas[coluna-1])==atoi(&colunas[operando-1])) write(1,aux, bytes);
    }
    if(strcmp(operador, ">=")==0){
     	if(atoi(&colunas[coluna])>=atoi(&colunas[operando])) write(1,aux, bytes);
    }
    if(strcmp(operador, "<=")==0){
      	if(atoi(&colunas[coluna])<=atoi(&colunas[operando])) write(1,aux, bytes);
    }
    if(strcmp(operador, ">")==0){
      	if(atoi(&colunas[coluna])>atoi(&colunas[operando])) write(1,aux, bytes);
    }
    if(strcmp(operador, "<")==0){
      	if(atoi(&colunas[coluna])<atoi(&colunas[operando])) write(1,aux, bytes);
    }
    if(strcmp(operador, "!=")==0){
      	if(atoi(&colunas[coluna])!=atoi(&colunas[operando])) write(1,aux, bytes);
    }
    free(buffer);
    free(colunas);
    free(aux);
    buffer=(char *)malloc(bufS*sizeof(char));
    colunas=(char *)malloc(bufS*sizeof(char));
  }
}

ssize_t readln(int fildes, char *buf, size_t nbyte) 
{
	int i = 0;

	while(i<nbyte-1 && 
		  read(fildes, buf+i, 1) > 0 &&
		  buf[i] != '\n') {

			i++;
	}

	if(i>=nbyte)
	buf[i] = 0;
	else
	buf[i+1] = 0;

	return i;
}

int main(int argc,char *argv[]){
	int coluna;
	char* operacao;
	int operando;
	
	int n,fd,i=1,j;
	char buffer[bufS];
	char str[bufS];

	fd = open("a",O_RDONLY);

	while((n=readln(fd,buffer,bufS))>0) {

		//puts(str);
		
		for(j = 0;j<10;j++) {
			
			strcpy(str,buffer);

			printf("%s\n",str);

		}

		str[j] = '\0';
	}
/*
	if (strcmp(argv[1],"const")==0) {


			consts(argv[2],buffer);
		}
				if (strcmp(argv[1],"filter")==0) {
			coluna=atoi(argv[2]);
			operacao=argv[3];
			operando=atoi(argv[4]);
			filtro(coluna,operacao,operando);
		}*/

	return 0;
}