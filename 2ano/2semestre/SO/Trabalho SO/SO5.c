#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>

#define bufS 512

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

void consts(char* value){
	
	int nBytes,fd;
	char buffer[bufS];	

	fd = open("a",O_RDONLY);
	
	while((nBytes=readln(fd,buffer,bufS))>0) {
			
			buffer[strlen(buffer)-1]='\0';
			strcat(buffer,":");
			strcat(buffer,value);
			strcat(buffer,"\n");
			write(1, buffer, nBytes+4);
		}
	
}

void filtro(int coluna, char* operador, int operando){
  	//char *buffer=(char *)malloc(bufS*sizeof(char));
  	int nBytes,fd;
  	char *res;
  	char buffer[bufS];

  	fd = open("a",O_RDONLY);

  	while((nBytes=readln(fd,buffer,bufS))>0){
  	char *aux=strdup(buffer);
  	res = parser(buffer);
    char* colunas = strdup(res);
    if(strcmp(operador, "=")==0){
     	if(atoi(&colunas[coluna-1])==atoi(&colunas[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, ">=")==0){
     	if(atoi(&colunas[coluna])>=atoi(&colunas[operando])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "<=")==0){
      	if(atoi(&colunas[coluna])<=atoi(&colunas[operando])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, ">")==0){
      	if(atoi(&colunas[coluna])>atoi(&colunas[operando])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "<")==0){
      	if(atoi(&colunas[coluna])<atoi(&colunas[operando])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "!=")==0){
      	if(atoi(&colunas[coluna-1])!=atoi(&colunas[operando-1])) write(1,aux, nBytes+4);
    }
    //free(buffer);
    //free(colunas);
    //free(aux);
    //buffer=(char *)malloc(bufS*sizeof(char));
    //colunas=(char *)malloc(bufS*sizeof(char));
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