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

char** parser(char* str){
	char *pToken = strtok(str,":");
	int i=0;
	char **colunas = (char **)malloc(bufS*sizeof(char));
	while(pToken != NULL){
		colunas[i]= strdup(pToken);
		pToken = strtok(NULL, ":");
		i++;
	}
	return colunas;
}

void consts(char* value){
	
	int nBytes,fd;
	char buffer[bufS];	

	fd = open("file.txt",O_RDONLY);
	
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
  	char **res;
  	char buffer[bufS];
  	fd = open("file.txt",O_RDONLY);

  	while((nBytes=readln(fd,buffer,bufS))>0){
  	char *aux=strdup(buffer);
  	res = parser(buffer);
    if(strcmp(operador, "=")==0){
     	if(atoi(res[coluna-1])==atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, ">=")==0){
     	if(atoi(res[coluna-1])>=atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "<=")==0){
      	if(atoi(res[coluna-1])<=atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, ">")==0){
      	if(atoi(res[coluna-1])>atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "<")==0){
      	if(atoi(res[coluna-1])<atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    if(strcmp(operador, "!=")==0){
      	if(atoi(res[coluna-1])!=atoi(res[operando-1])) write(1,aux, nBytes+4);
    }
    free(aux);

  }
  printf("\n");
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